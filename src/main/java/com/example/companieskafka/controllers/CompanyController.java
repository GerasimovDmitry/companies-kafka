package com.example.companieskafka.controllers;

import com.example.companieskafka.m2m.M2MController;
import com.example.companieskafka.repository.CompanyRepository;
import com.example.companieskafka.service.CompanyService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/companies")
@RestController
public class CompanyController {
    CompanyService companyService;

    CompanyRepository repository;

    M2MController m2mController;

    public CompanyController(CompanyService companyService, M2MController m2mController, CompanyRepository repository) {
        this.companyService = companyService;
        this.m2mController = m2mController;
        this.repository = repository;
    }

/*    @GetMapping(path = "/{companyId}")
    public boolean findById(@PathVariable("companyId") long companyId) {
        return companyService.findByCompanyId(companyId).isPresent();
    }

    @GetMapping(path = "/all")
    public List<String> getAll() {
        List<String> companyDTOs = new ArrayList<>();
        List<Company> companies = companyService.getAll();
        for (Company company : companies) {
            CompanyDTO companyDTO = new CompanyDTO();
            companyDTO.setCompanyId(company.getCompanyId());
            companyDTO.setName(company.getName());
            companyDTO.setShortName(company.getShortName());
            companyDTO.setOgrn(companyDTO.getOgrn());
            if (company.getAddress() != null) {
                companyDTO.setAddressId(company.getAddress().getAddressId());
                companyDTO.setIndex(company.getAddress().getIndex());
                companyDTO.setArea(company.getAddress().getArea());
                companyDTO.setCity(company.getAddress().getCity());
                companyDTO.setStreet(company.getAddress().getStreet());
                companyDTO.setHomeNumber(company.getAddress().getHomeNumber());
                companyDTO.setOfficeNumber(company.getAddress().getOfficeNumber());
            }
            if (company.getUserId() != null) {
                String user = m2mController.getOne(company.getUserId());
                JSONObject jsonObject = new JSONObject(user);
                String id = String.valueOf(jsonObject.get("id"));
                companyDTO.setId(id);
                companyDTO.setFirstname(String.valueOf(jsonObject.get("firstname")));
                companyDTO.setLastname(String.valueOf(jsonObject.get("lastname")));
                companyDTO.setEmail(String.valueOf(jsonObject.get("email")));
                companyDTO.setKeycloak_id(String.valueOf(jsonObject.get("keycloak_id")));
                companyDTO.setUsername(String.valueOf(jsonObject.get("username")));
                companyDTO.setPassword(String.valueOf(jsonObject.get("password")));
                companyDTO.setRole(String.valueOf(jsonObject.get("role")));
            }
            companyDTOs.add(companyDTO.toString());
        }
        return companyDTOs;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<?> createCompany(@RequestBody Company company) {
        Company companyNew = new Company();
        companyNew.setName(company.getName());
        companyNew.setOgrn(company.getOgrn());
        companyNew.setShortName(company.getShortName());
        companyNew.setAddress(addressService.findByAddressId(company.getAddress().getAddressId()));
        if (m2mController.findById(company.getUserId())) {
            companyNew.setUserId(company.getUserId());
        } else {
            return new ResponseEntity<Object>(
                    "Такого пользователя не существует!", new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
        companyService.save(company);
        return ResponseEntity.ok(company);
    }

    @GetMapping(path = "getOne/{companyId}")
    public String getOne(@PathVariable("companyId") long companyId) throws JsonProcessingException {
        Company company = companyService.getByCompanyId(companyId);
        Company c = new Company();
        if (company.getDeleted() != true) {
            c.setCompanyId(company.getCompanyId());
            c.setName(company.getName());
            c.setShortName(company.getShortName());
            c.setOgrn(company.getOgrn());
            c.setUserId(company.getUserId());

            if (company.getAddress() != null) {
                Address address = new Address();
                address.setAddressId(company.getAddress().getAddressId());
                address.setArea(company.getAddress().getArea());
                address.setCity(company.getAddress().getCity());
                address.setStreet(company.getAddress().getStreet());
                address.setIndex(company.getAddress().getIndex());
                address.setHomeNumber(company.getAddress().getHomeNumber());
                address.setOfficeNumber(company.getAddress().getOfficeNumber());
                c.setAddress(address);
            }
        }
        else {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Компания не актуальна!");
        }

        String json = new ObjectMapper().writeValueAsString(c);

        Object userPrettyJson = new ObjectMapper().readValue(
                json, Company.class);

        String obj = new ObjectMapper().writerWithDefaultPrettyPrinter()
                .writeValueAsString(userPrettyJson);

        return obj;
    }*/
}
