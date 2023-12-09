package com.example.primejsf.service;

import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.utils.ApiRestMapper;
import com.example.primejsf.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupService {

    @Autowired
    private RestService restService;

    public GroupDTO openNew(){
        return new GroupDTO("","");
    }

    public List<GroupDTO> getGroups(){
        List<GroupDTO> listGroups = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<GroupDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/grupo/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listGroups = apiRestMapper.mapList(response, GroupDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listGroups;
    }

    public GroupDTO getGroupById(int GroupID) {
        GroupDTO Group = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<GroupDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/grupo/{id}");
            String uri = template.expand(GroupID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            Group = apiRestMapper.mapOne(response, GroupDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return Group;
    }

    public void createGroup(GroupDTO Group) {
        restService.POST("/api/v1/grupo/", Group, String.class).getBody();
    }

    public void updateGroup(GroupDTO Group) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/grupo/", params, Group, String.class).getBody();
    }

    public void deleteGroup(int GroupID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/grupo/{id}");
        String uri = template.expand(GroupID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
