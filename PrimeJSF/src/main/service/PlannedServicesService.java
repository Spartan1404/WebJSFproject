package com.example.primejsf.service;

import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.dto.PlannedServicesDTO;
import com.example.primejsf.dto.PlannedServicesDTO;
import com.example.primejsf.utils.ApiRestMapper;
import com.example.primejsf.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlannedServicesService {

    @Autowired
    private RestService restService;

    public PlannedServicesDTO openNew(){return new PlannedServicesDTO("",0, "",LocalTime.now(), 0);}

    public List<PlannedServicesDTO> getPlannedServices(){
        List<PlannedServicesDTO> listPlannedServices = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<PlannedServicesDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/servicio/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listPlannedServices = apiRestMapper.mapList(response, PlannedServicesDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listPlannedServices;
    }

    public PlannedServicesDTO getPlannedServiceById(int PlannedServiceID) {
        PlannedServicesDTO PlannedService = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<PlannedServicesDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/servicio/{id}");
            String uri = template.expand(PlannedServiceID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            PlannedService = apiRestMapper.mapOne(response, PlannedServicesDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return PlannedService;
    }

    public void createPlannedService(PlannedServicesDTO PlannedService) {
        restService.POST("/api/v1/servicio/", PlannedService, String.class).getBody();
    }

    public void updatePlannedService(PlannedServicesDTO PlannedService) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/servicio/", params, PlannedService, String.class).getBody();
    }

    public void deletePlannedService(int PlannedServiceID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/servicio/{id}");
        String uri = template.expand(PlannedServiceID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
