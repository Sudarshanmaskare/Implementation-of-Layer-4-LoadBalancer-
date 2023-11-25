package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {

    @GetMapping(value = "/hello")
    public String getHello() throws IOException {
        URL url = new URL("https://checkip.amazonaws.com/");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        String ip = br.readLine();

        return "Hello Sudarshan the response is from ip address " + ip + " at time " + LocalDateTime.now();
    }
}
