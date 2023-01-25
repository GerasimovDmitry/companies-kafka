package com.example.companieskafka.controllers;

import com.example.companieskafka.config.KafkaController;
import com.example.companieskafka.entity.Company;
import com.example.companieskafka.entity.User;
import com.example.companieskafka.entity.UserCompany;
import com.example.companieskafka.m2m.M2MController;
import com.example.companieskafka.repository.CompanyRepository;
import com.example.companieskafka.service.CompanyService;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RequestMapping(value = "/companies")
@RestController
public class CompanyController {
    CompanyService companyService;

    CompanyRepository repository;

    M2MController m2mController;
    KafkaController kafkaController;

    public CompanyController(CompanyService companyService,
                             M2MController m2mController,
                             CompanyRepository repository,
                             KafkaController kafkaController) {
        this.companyService = companyService;
        this.m2mController = m2mController;
        this.repository = repository;
        this.kafkaController = kafkaController;
    }

    @GetMapping(path = "/all")
    public List<Company> getAll() {
        return companyService.getAll();
    }

    @GetMapping(path = "/all/users-company")
    public List<UserCompany> getAllUsersCompany() {
        List<User> users = m2mController.findAllUsers();
        List<Company> companies = this.companyService.getAll();
        List<UserCompany> userCompanies = new ArrayList<>();
        UserCompany userCompany;
        Company company;
        for (User user: users) {
            if (user.getCompany_id() != null) {
                userCompany = new UserCompany();
                userCompany.setCompany_id(user.getCompany_id());
                userCompany.setUser_id(user.getId());
                UserCompany finalUserCompany = userCompany;

                company = companies.stream()
                        .filter(c -> finalUserCompany.getCompany_id().equals(c.getId()))
                        .findAny()
                        .orElse(null);
                assert company != null;
                userCompany.setCompanyName(company.getName());
                userCompany.setIsOwner(userCompany.getUser_id().equals(company.getOwner_id()));
                userCompany.setUsername(user.getUserName());
                userCompany.setName(user.getName());
                userCompany.setKeycloak_id(user.getKeycloak_id());
                userCompanies.add(userCompany);
            }
        }
        return userCompanies;
    }


    @GetMapping(path = "/findById")
    public Company findById(@RequestParam("companyId") Integer companyId) throws ResourceNotFoundException {
        Company company = companyService.findById(companyId);
        System.out.println(company);
        if (company == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        return companyService.findById(companyId);
    }

    @GetMapping(path = "/findById/owner")
    public ResponseEntity<?> findByIdWithOwner(@RequestParam("companyId") Integer companyId) throws ResourceNotFoundException {
        Company company = companyService.findById(companyId);
        if (company == null) {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }
        User owner = m2mController.findById(company.getOwner_id());
        if (owner == null) {
            return new ResponseEntity<Object>(
                    "User is not found", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        } else {
            UserCompany userCompany = new UserCompany();
            userCompany.setCompany_id(company.getId());
            userCompany.setUser_id(owner.getId());
            userCompany.setKeycloak_id(owner.getKeycloak_id());
            userCompany.setIsOwner(true);
            userCompany.setUsername(owner.getUserName());
            userCompany.setName(owner.getName());
            userCompany.setCompanyName(company.getName());
            return ResponseEntity.ok(userCompany);
        }
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        Company companyNew = new Company();
        companyNew.setName(company.getName());

        if (m2mController.findById(company.getOwner_id()) != null) {
            companyNew.setOwner_id(company.getOwner_id());
        } else {
            return new ResponseEntity<Object>(
                    "User is not found", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        companyService.save(company);
        return ResponseEntity.ok(company);
    }
    @DeleteMapping("/remove")
    public String removeCompany(@RequestParam("companyId") Integer companyId) {
        this.companyService.remove(companyId);
        this.kafkaController.producer.sendMessage(companyId.toString());
        return "company with id: " + companyId.toString();
    }
}
