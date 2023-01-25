package com.example.companieskafka.config;

import com.example.companieskafka.service.CompanyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {
    @Getter
    public final Producer producer;

    private CompanyService companyService;

    @Autowired
    public void setCompanyService(CompanyService companyService) {
        this.companyService = companyService;
    }

    @Autowired
    KafkaController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping(value = "/delete")
    public ResponseEntity<Object> deleteCompany(@RequestParam("companyId") String companyId) {
        this.producer.sendMessage(companyId);
        return new ResponseEntity<Object>(
                "Company id was sent", new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}