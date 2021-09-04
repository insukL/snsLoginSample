package org.tikim.boot.service;

import org.tikim.boot.domain.KakaoAuth;
import org.tikim.boot.domain.User;
import org.tikim.boot.domain.UserTest;
import org.tikim.boot.domain.kakaoUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

public interface UserService {
    List<User> selectUser();
    void insertUser();
    void signUpCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception;
    void getUserEmail(String Token) throws Exception;
    UserTest kakaoCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception;
    kakaoUser getKakaoUserInfo(String Token) throws Exception;
}
