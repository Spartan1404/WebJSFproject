package com.example.primejsf.service;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.dto.DriverDTO;
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
public class DriverService {

    @Autowired
    private RestService restService;

    public DriverDTO openNew(){
        return new DriverDTO(0,"","","",0,"");
    }

    public List<DriverDTO> getDrivers(){
        List<DriverDTO> listDrivers = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/drivers/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listDrivers = apiRestMapper.mapList(response, DriverDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listDrivers;
    }

    public DriverDTO getDriverById(int driverID) {
        DriverDTO driver = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DriverDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/drivers/{id}");
            String uri = template.expand(driverID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            driver = apiRestMapper.mapOne(response, DriverDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return driver;
    }

    public void createDriver(DriverDTO driver) {
        restService.POST("/api/v1/driver/", driver, String.class).getBody();
    }

    public void updateDriver(DriverDTO driver) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/driver/", params, driver, String.class).getBody();
    }

    public void deleteDriver(int driverID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/driver/{id}");
        String uri = template.expand(driverID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
