package com.example.primejsf.service;

import com.example.primejsf.dto.ContractDTO;
import com.example.primejsf.dto.DistrictDTO;
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
public class DistrictService {

    @Autowired
    private RestService restService;

    public DistrictDTO openNew(){
        return new DistrictDTO(-1, "New District");
    }

    public List<DistrictDTO> getDistricts(){
        List<DistrictDTO> listDistricts = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<DistrictDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/distrito/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listDistricts = apiRestMapper.mapList(response, DistrictDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listDistricts;
    }

    public DistrictDTO getDistrictById(int districtID) {
        DistrictDTO district = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<DistrictDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/distrito/{id}");
            String uri = template.expand(districtID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            district = apiRestMapper.mapOne(response, DistrictDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return district;
    }

    public void createDistrict(DistrictDTO district) {
        restService.POST("/api/v1/distrito/", district, String.class).getBody();
    }

    public void updateDistrict(DistrictDTO district) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/distrito/", params, district, String.class).getBody();
    }

    public void deleteDistrict(int districtID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/distrito/{id}");
        String uri = template.expand(districtID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
