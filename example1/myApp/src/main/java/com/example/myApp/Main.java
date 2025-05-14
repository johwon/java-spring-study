package com.example.myApp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        // IoC 컨테이너 초기화
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.myApp");

        // Spring container가 관리하는 객체 가져오기
        MusicPlayer musicPlayer = context.getBean(MusicPlayer.class);
        musicPlayer.play();
    }
}
