package com.example.primejsf.service;

import com.example.primejsf.dto.*;
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
public class CarService {

    @Autowired
    private RestService restService;

    public CarDTO openNew(){
        return new CarDTO(-1,0, 0, "", 0, 0);
    }

    public List<CarDTO> getCars() {
        List<CarDTO> carList = new ArrayList<CarDTO>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/cars/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            carList = apiRestMapper.mapList(response, CarDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return carList;
    }

    public CarDTO getCarById(int carID) {
        CarDTO car = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<CarDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/cars/{id}");
            String uri = template.expand(carID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            car = apiRestMapper.mapOne(response, CarDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return car;
    }

    public void createCar(CarDTO car) {
        restService.POST("/api/v1/cars/", car, String.class).getBody();
    }


    public void updateCar(CarDTO car) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/cars/", params, car, String.class).getBody();
    }


    public void deleteCar(int carID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/cars/{id}");
        String uri = template.expand(carID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
