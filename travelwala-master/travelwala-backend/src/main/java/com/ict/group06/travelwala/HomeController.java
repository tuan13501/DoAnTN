package com.ict.group06.travelwala;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@CrossOrigin("*")
public class HomeController {
    @GetMapping()
    ResponseEntity<?> helloWorld() {
        return ResponseEntity.ok().body("Hello World from HT");
    }
}