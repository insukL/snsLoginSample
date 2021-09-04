package org.tikim.boot.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tikim.boot.domain.KakaoAuth;
import org.tikim.boot.domain.UserTest;
import org.tikim.boot.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

@RestController
public class UserController {
    @Resource(name = "userServiceImpl")
    private UserService userService;

    @GetMapping("/users")
    public ResponseEntity getUser() {
        return new ResponseEntity(userService.selectUser(), HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity inserUser(){
        userService.insertUser();
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/login/google/callback")
    public void googleSignCallBack(HttpServletRequest request, HttpServletResponse response){
        try{
            userService.signUpCallBack(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @GetMapping("/login/kakao/callback")
    public UserTest kakaoSginCallBack(HttpServletRequest request, HttpServletResponse response){
        try {
            System.out.println(request);
            return userService.kakaoCallBack(request, response);
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
