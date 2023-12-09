package com.example.primejsf.service;

import com.example.primejsf.dto.UsersDTO;
import com.example.primejsf.utils.ApiRestMapper;
import com.example.primejsf.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestService restService;

    public UsersDTO openNew(){
        return new UsersDTO("","","");
    }

    public List<UsersDTO> getUsers(){
        List<UsersDTO> listUsers = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<UsersDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/users/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listUsers = apiRestMapper.mapList(response, UsersDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listUsers;
    }

    public UsersDTO getUserById(int UserID) {
        UsersDTO User = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<UsersDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/users/{id}");
            String uri = template.expand(UserID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            User = apiRestMapper.mapOne(response, UsersDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return User;
    }

    public void createUser(UsersDTO User) {
        restService.POST("/api/v1/users/", User, String.class).getBody();
    }

    public void updateUser(UsersDTO User) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/users/", params, User, String.class).getBody();
    }

    public void deleteUser(int UserID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/users/{id}");
        String uri = template.expand(UserID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
