package com.example.myApp;

import org.springframework.stereotype.Component;

@Component
public class BluetoothSpeaker implements Speaker {
    @Override
    public void playSound() {
        System.out.println("블루투스 스피커 : 음악 재생 중..");
    }
}
