package com.example.companieskafka.m2m;

import com.example.companieskafka.models.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "M2MClient", url = "http://localhost:8090")
public interface M2MClient {

    @GetMapping("/users/find/{userId}")
    Users getUser (@PathVariable Long userId);

    @GetMapping("/users/hello")
    String getHello ();

    @PostMapping("/users/signin")
    ResponseEntity<?> signin(@RequestBody Users user);

    @GetMapping("/users/{userId}")
    Boolean findById (@PathVariable Integer userId);

    @GetMapping("/users/getOne/{userId}")
    String getOne (@PathVariable Integer userId);
}