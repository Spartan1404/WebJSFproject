package com.example.primejsf.service;

import com.example.primejsf.dto.BrandDTO;
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
public class BrandService {

    @Autowired
    private RestService restService;

    public BrandDTO openNew(){
        return new BrandDTO(-1,null,0, null, 0);
    }

    public List<BrandDTO> getBrands() {
        List<BrandDTO> brandList = new ArrayList<BrandDTO>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/marca/", params, String.class).getBody();

            System.out.println("\nResponse: " + response + "\n");
            brandList = apiRestMapper.mapList(response, BrandDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return brandList;
    }

    public BrandDTO getBrandById(int brandID) {
        BrandDTO brand = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<BrandDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/marca/{id}");
            String uri = template.expand(brandID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            brand = apiRestMapper.mapOne(response, BrandDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return brand;
    }

    public void createBrand(BrandDTO brand) {
        restService.POST("/api/v1/marca/", brand, String.class).getBody();
    }


    public void updateBrand(BrandDTO brand) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/marca/", params, brand, String.class).getBody();
    }


    public void deleteBrand(int brandID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/marca/{id}");
        String uri = template.expand(brandID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
