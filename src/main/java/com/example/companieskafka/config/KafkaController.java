package com.example.companieskafka.config;

import com.example.companieskafka.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/kafka")
public class KafkaController {

    private final Producer producer;

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
/*        if (companyService.findByCompanyId(Long.parseLong(companyId)).isPresent()) {
            this.producer.sendMessage(companyId);
            companyService.updateCompanyDeleted(Integer.valueOf(companyId));
        } else {
            return new ResponseEntity<Object>(
                    "Такой компании не существует!", new HttpHeaders(), HttpStatus.BAD_REQUEST);

        }
        return new ResponseEntity<Object>(
                "Сообщение отправлено!", new HttpHeaders(), HttpStatus.ACCEPTED);*/


        this.producer.sendMessage(companyId);
        return new ResponseEntity<Object>(
                "Сообщение отправлено!", new HttpHeaders(), HttpStatus.ACCEPTED);
    }
}