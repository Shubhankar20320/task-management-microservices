package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.*;

@RestController
@RequestMapping("/tasks")
public class MainController {

    private Map<Long, String> tasks = new HashMap<>();

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/{userId}/{taskId}")
    public String createTask(@PathVariable Long userId,
                             @PathVariable Long taskId,
                             @RequestBody String taskName) {

        String userUrl = "http://localhost:8081/users/" + userId;

        String response = restTemplate.getForObject(userUrl, String.class);

        if (response.equals("User Not Found")) {
            return "Cannot create task. User does not exist.";
        }

        tasks.put(taskId, taskName);
        return "Task Created for User: " + response;
    }

    @GetMapping("/{taskId}")
    public String getTask(@PathVariable Long taskId) {
        return tasks.getOrDefault(taskId, "Task Not Found");
    }
}
