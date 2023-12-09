package com.example.primejsf.service;

import com.example.primejsf.dto.AutenUserDTO;
import com.example.primejsf.dto.LoginRequestDTO;
import com.example.primejsf.utils.ApiRestMapper;
import com.example.primejsf.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    private RestService restService;

    public AutenUserDTO Login(LoginRequestDTO user){
        AutenUserDTO a = null;
        try {
            ApiRestMapper<AutenUserDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String) restService.POST("api/v1/login/login", user, String.class).getBody();
            a = apiRestMapper.mapOne(response, AutenUserDTO.class);
        } catch (Exception e){
            e.printStackTrace();
        }
        return a;
    }
}
