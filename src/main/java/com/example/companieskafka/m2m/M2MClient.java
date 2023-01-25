package com.example.companieskafka.m2m;

import com.example.companieskafka.entity.User;
import com.example.companieskafka.models.Users;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "M2MClient", url = "http://localhost:8080")
public interface M2MClient {

    @GetMapping("/users/find/{userId}")
    Users getUser (@PathVariable Long userId);

    @GetMapping("/users/hello")
    String getHello ();

    @PostMapping("/users/signin")
    ResponseEntity<?> signin(@RequestBody Users user);

    @RequestMapping(method = RequestMethod.GET, value="m2m/users/findById",consumes = "application/json", produces = "application/json")
    User findById (@RequestParam Integer userId);

    @GetMapping("/users/getOne/{userId}")
    String getOne (@PathVariable Integer userId);

    @RequestMapping(method = RequestMethod.GET, value="m2m/users",consumes = "application/json", produces = "application/json")
    List<User> findAllUsers ();
}