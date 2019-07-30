package com.example.demo.controller;

import com.example.demo.dto.AccessTokenDTO;
import com.example.demo.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {

    @Autowired
    GithubProvider provider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClientId("70a34622407c41973e42");
        accessTokenDTO.setClientSecret("1de8778ea080c3ff7f885c7498d930b728bcd451");
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirectUri("http://localhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = provider.getAccessToken(accessTokenDTO);
        System.out.println(provider.getUser(accessToken).getName());
        return "index";
    }
}
