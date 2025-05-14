package com.example.myApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    private final Speaker speaker;

    @Autowired
    public MusicPlayer(Speaker speaker) {
        this.speaker = speaker;
    }

    public void play(){
        speaker.playSound();
        System.out.println("뮤직 플레이어가 실행됩니다.");
    }

}
