package com.example.primejsf.service;

import com.example.primejsf.dto.BrandDTO;
import com.example.primejsf.dto.DriverDTO;
import com.example.primejsf.dto.NADriverDTO;
import com.example.primejsf.dto.NADriverDTO;
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
public class NADriverService {

    @Autowired
    private RestService restService;




    public NADriverDTO openNew(){return new NADriverDTO(0, 0);}

    public List<NADriverDTO> getNADrivers(){
        List<NADriverDTO> listNADrivers = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<NADriverDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/non_assigned_drivers/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listNADrivers = apiRestMapper.mapList(response, NADriverDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listNADrivers;
    }

    public NADriverDTO getNADriverById(int NADriverID) {
        NADriverDTO NADriver = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<NADriverDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/non_assigned_drivers/{id}");
            String uri = template.expand(NADriverID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            NADriver = apiRestMapper.mapOne(response, NADriverDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return NADriver;
    }

    public void createNADriver(NADriverDTO NADriver) {
        restService.POST("/api/v1/non_assigned_drivers/", NADriver, String.class).getBody();
    }

    public void updateNADriver(NADriverDTO NADriver) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/non_assigned_drivers/", params, NADriver, String.class).getBody();
    }

    public void deleteNADriver(int NADriverID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/non_assigned_drivers/{id}");
        String uri = template.expand(NADriverID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
