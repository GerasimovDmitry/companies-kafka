package com.example.companieskafka.service;

import com.example.companieskafka.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    CompanyRepository companyRepository;
    @Autowired
    public CompanyService(CompanyRepository repository) {
        this.companyRepository = repository;
    }
/*
    public Optional<Company> findByCompanyId(long id) {
        return companyRepository.findById(id);
    }

    public Company findCompanyId(long id) {
        return companyRepository.getOne(id);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public List<Company> getAll() {
        return companyRepository.findAll();
    }

    public Company getByCompanyId(long id) {
        return companyRepository.getOne(id);
    }

    public void updateCompanyDeleted(Integer id) {
        companyRepository.updateCompanyDeleted(id);
    }*/
}
