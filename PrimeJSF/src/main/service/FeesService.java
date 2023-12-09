package com.example.primejsf.service;

import com.example.primejsf.dto.FeesDTO;
import com.example.primejsf.dto.FeesDTO;
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
public class FeesService {

    @Autowired
    private RestService restService;

    public FeesDTO openNew(){
        return new FeesDTO(1,0, 0, 0);
    }

    public List<FeesDTO> getFees(){
        List<FeesDTO> listFees = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<FeesDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/tarifa/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listFees = apiRestMapper.mapList(response, FeesDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listFees;
    }

    public FeesDTO getFeeById(int feeID) {
        FeesDTO fee = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<FeesDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/tarifa/{id}");
            String uri = template.expand(feeID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            fee = apiRestMapper.mapOne(response, FeesDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return fee;
    }

    public void createFee(FeesDTO fee) {
        restService.POST("/api/v1/tarifa/", fee, String.class).getBody();
    }

    public void updateFee(FeesDTO fee) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/tarifa/", params, fee, String.class).getBody();
    }

    public void deleteFee(int feeID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/tarifa/{id}");
        String uri = template.expand(feeID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
