package com.example.companieskafka.service;

import com.example.companieskafka.entity.Company;
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

    public List<Company> getAll() {
        return this.companyRepository.findAll();
    }

    public Company findById(Integer id) {
        Company company = companyRepository.findById(id).orElse(null);
        assert company != null;
        return company;
    }

    public void save(Company company) {
        this.companyRepository.save(company);
    }

    public void remove(Integer id) {
        this.companyRepository.deleteById(id);
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
