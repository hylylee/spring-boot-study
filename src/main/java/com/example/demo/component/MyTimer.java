package com.example.demo.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class MyTimer {
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    @Scheduled(initialDelay=1000, fixedDelay=3000)
    public void timerRate() {
        System.out.println(sdf.format(new Date()));
    }
}
