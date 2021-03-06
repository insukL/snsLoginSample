package org.tikim.boot.serviceImpl;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.tikim.boot.domain.*;
import org.tikim.boot.mapper.UserMapper;
import org.tikim.boot.repository.UserRepository;
import org.tikim.boot.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UserMapper userMapper;

    @Override
    public void insertUser() {
    }

    @Override
    public List<User> selectUser() {
        List<User> userList = userRepository.findAll();
        return userList;
    }

    @Override
    public void signUpCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String code = request.getParameter("code");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("code", code);
        parameters.add("client_id", "420224900119-t1l2f25ldqvp89jio4c85irajp14d2mf.apps.googleusercontent.com");
        parameters.add("client_secret", "yVtOaLf-u7MUfDdtXKyCasHf");
        parameters.add("redirect_uri", "http://localhost/login/google/callback");
        parameters.add("grant_type", "authorization_code");

        HttpEntity<MultiValueMap<String,String>> restRequest = new HttpEntity<>(parameters,headers);
        String url = "https://www.googleapis.com/oauth2/v4/token";
        ResponseEntity<GoogleAuth> restResponse = restTemplate.exchange(url, HttpMethod.POST, restRequest, GoogleAuth.class);
        String token = restResponse.getBody().getAccess_token();
        getUserEmail(token);

        response.sendRedirect("http://localhost/board");
    }

    public UserTest kakaoCallBack(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // ??????????????? ?????????(redirect?????? ??????????????? ??????)
        String code = request.getParameter("code");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        System.out.println("???????????? : " + code);

        // ?????? ????????? ?????? ????????? ????????? Map??? ??????
        MultiValueMap<String, String> paramters = new LinkedMultiValueMap<>();
        paramters.add("grant_type", "authorization_code");
        paramters.add("client_id", "8ceb5f0ed04f5199648a83ecdd267953");
        paramters.add("redirect_uri", "http://localhost/login/kakao/callback");
        paramters.add("code", code);

        // restTemplate??? ????????? ?????? ??????
        HttpEntity<MultiValueMap<String, String>> restRequest = new HttpEntity<>(paramters, headers);
        ResponseEntity<KakaoAuth> restResponse = restTemplate.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, restRequest, KakaoAuth.class);
        System.out.println("?????? : " + restResponse.getBody().getAccess_token());

        // ?????? ????????? ?????????
        KakaoAuth result = restResponse.getBody();
        kakaoUser kakaoUser = getKakaoUserInfo(result.getAccess_token());
        UserCode userCode = userMapper.getKakaoId(kakaoUser.getId());
        if(userCode == null){
            // ?????? ????????????
            UserTest temp = new UserTest();
            temp.setNickname("?????????");
            int id = userMapper.join(temp);

            // ????????? ????????? ?????? ??????
            userCode = new UserCode();
            userCode.setId(id);
            userCode.setKakao_id(kakaoUser.getId());
            userMapper.insertKakaoId(userCode);

            return temp;
        }
        else return userMapper.getUser(userCode.getId());
    }
    public void getUserEmail(String Token) throws Exception{
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(Token);

        HttpEntity<MultiValueMap<String,String>> restRequest = new HttpEntity<>(headers);

       String url = "https://www.googleapis.com/oauth2/v1/userinfo";

        ResponseEntity<GoogleUser> restRepsonse = restTemplate.exchange(url, HttpMethod.GET, restRequest, GoogleUser.class);
        System.out.println(restRepsonse.getBody().getEmail());
        System.out.println(restRepsonse.getBody().getName());
    }

    @Override
    public kakaoUser getKakaoUserInfo(String Token) throws Exception {
        // Header??? Bearer Token?????? ??????
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(Token);

        HttpEntity<MultiValueMap<String,String>> restRequest = new HttpEntity<>(headers);

        String url = "https://kapi.kakao.com/v2/user/me";

        // ????????? URI??? Token??? ????????? restResponse??? ??????
        ResponseEntity<kakaoUser> restRepsonse = restTemplate.postForEntity(url, restRequest, kakaoUser.class);
        System.out.println(restTemplate.postForEntity(url, restRequest, String.class).getBody());
        return restRepsonse.getBody();
    }
}
