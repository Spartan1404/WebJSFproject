package com.example.primejsf.service;

import com.example.primejsf.dto.CarDTO;
import com.example.primejsf.dto.PlanDTO;
import com.example.primejsf.dto.PlanDTO;
import com.example.primejsf.utils.ApiRestMapper;
import com.example.primejsf.utils.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlanService {

    @Autowired
    private RestService restService;

    public PlanDTO openNew(){
        return new PlanDTO(0,0,0,null);
    }

    public List<PlanDTO> getPlans(){
        List<PlanDTO> listPlans = null;
        try {
            MultiValueMap<String, String > params = new LinkedMultiValueMap<>();
            ApiRestMapper<PlanDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/planificacion/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            listPlans = apiRestMapper.mapList(response, PlanDTO.class);

        } catch (IOException e){
            e.printStackTrace();
        }
        return listPlans;
    }

    public PlanDTO getPlanById(int PlanID) {
        PlanDTO Plan = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<PlanDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/planificacion/{id}");
            String uri = template.expand(PlanID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            Plan = apiRestMapper.mapOne(response, PlanDTO.class);

        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return Plan;
    }

    public void createPlan(PlanDTO Plan) {
        restService.POST("/api/v1/planificacion/", Plan, String.class).getBody();
    }

    public void updatePlan(PlanDTO Plan) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/planificacion/", params, Plan, String.class).getBody();
    }

    public void deletePlan(int PlanID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/planificacion/{id}");
        String uri = template.expand(PlanID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
