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
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RouteSheetService {

    @Autowired
    private RestService restService;

    public RouteSheetDTO openNew() {
        return new RouteSheetDTO(0, 0, null, null, 0, 0, 0, 0, 0);
    }

    public List<RouteSheetDTO> getRouteSheets(){
        List<RouteSheetDTO> listRouteSheets = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<RouteSheetDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/hoja_ruta/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listRouteSheets = apiRestMapper.mapList(response, RouteSheetDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listRouteSheets;
    }

    public RouteSheetDTO getRouteSheetById(int RouteSheetID) {
        RouteSheetDTO RouteSheet = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<RouteSheetDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/hoja_ruta/{id}");
            String uri = template.expand(RouteSheetID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            RouteSheet = apiRestMapper.mapOne(response, RouteSheetDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return RouteSheet;
    }

    public void createRouteSheet(RouteSheetDTO RouteSheet) {
        restService.POST("/api/v1/hoja_ruta/", RouteSheet, String.class).getBody();
    }

    public void updateRouteSheet(RouteSheetDTO RouteSheet) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/hoja_ruta/", params, RouteSheet, String.class).getBody();
    }

    public void deleteRouteSheet(int RouteSheetID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/hoja_ruta/{id}");
        String uri = template.expand(RouteSheetID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }

}
