package com.example;

import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
@RequestMapping("/users")
public class MainController {

    private Map<Long, String> users = new HashMap<>();

    @PostMapping("/{id}")
    public String createUser(@PathVariable Long id, @RequestBody String name) {
        users.put(id, name);
        return "User Created";
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable Long id) {
        return users.getOrDefault(id, "User Not Found");
    }
}
