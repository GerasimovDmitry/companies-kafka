package com.example.companieskafka.m2m;

import com.example.companieskafka.models.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class M2MController {

    private final M2MClient client;

    @GetMapping("/users/find/{userId}")
    public Users getUser(@PathVariable Long userId) {
        return client.getUser(userId);
    }

    @GetMapping("/users/hello")
    public String getHello() {
        return client.getHello();
    }

    @PostMapping("/users/signin")
    ResponseEntity<?> signin(@RequestBody Users userDTO) {
        return client.signin(userDTO);
    }

    @GetMapping("/users/{userId}")
    public Boolean findById(@PathVariable Integer userId) {
        return client.findById(userId);
    }

    @GetMapping("/users/getOne/{userId}")
    public String getOne(@PathVariable Integer userId) {
        return client.getOne(userId);
    }
}