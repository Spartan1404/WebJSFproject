package com.example.primejsf.service;

import com.example.primejsf.dto.FleetDTO;
import com.example.primejsf.dto.FleetDTO;
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
public class FleetService {

    @Autowired
    private RestService restService;

    public FleetDTO openNew(){
        return new FleetDTO(1);
    }

    public List<FleetDTO> getFleets(){
        List<FleetDTO> listFleets = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<FleetDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/flotilla/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listFleets = apiRestMapper.mapList(response, FleetDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listFleets;
    }

    public FleetDTO getFleetById(int FleetID) {
        FleetDTO Fleet = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<FleetDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/flotilla/{id}");
            String uri = template.expand(FleetID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            Fleet = apiRestMapper.mapOne(response, FleetDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return Fleet;
    }

    public void createFleet(FleetDTO Fleet) {
        restService.POST("/api/v1/flotilla/", Fleet, String.class).getBody();
    }

    public void updateFleet(FleetDTO Fleet) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/flotilla/", params, Fleet, String.class).getBody();
    }

    public void deleteFleet(int FleetID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/flotilla/{id}");
        String uri = template.expand(FleetID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
