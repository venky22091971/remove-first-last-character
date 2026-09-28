package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    @GetMapping("/remove")
    public ResponseEntity<String> remove(
            @RequestParam String original) {

        if (original.length() < 2) {
            return ResponseEntity.badRequest().build();
        }

        String result =
                original.substring(1, original.length() - 1);

        return ResponseEntity.ok(result);
    }
}