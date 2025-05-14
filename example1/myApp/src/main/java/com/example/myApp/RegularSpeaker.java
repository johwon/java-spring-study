package com.example.myApp;

import org.springframework.stereotype.Component;

//@Component
public class RegularSpeaker implements Speaker {
    @Override
    public void playSound() {
        System.out.println("일반 스피커 : 음악 재생 중..");
    }
}
