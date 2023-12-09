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
import java.util.ArrayList;
import java.util.List;

@Service
public class ContractMonthService {

    @Autowired
    private RestService restService;

    public ContractMonthDTO openNew(){
       return new ContractMonthDTO(0, 0, 0, 0, LocalDate.now(), 0);
    }

    public List<ContractMonthDTO> getContractMonths() {
        List<ContractMonthDTO> userList = new ArrayList<ContractMonthDTO>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractMonthDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/contrato_mes/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            userList = apiRestMapper.mapList(response, ContractMonthDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public ContractMonthDTO getContractMonthById(int contractMonthID) {
        ContractMonthDTO contractMonth = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractMonthDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/contrato_mes/{id}");
            String uri = template.expand(contractMonthID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            contractMonth = apiRestMapper.mapOne(response, ContractMonthDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return contractMonth;
    }

    public void createContractMonth(ContractMonthDTO contractMonth) {
        restService.POST("/api/v1/contrato_mes/", contractMonth, String.class).getBody();
    }


    public void updateContractMonth(ContractMonthDTO contractMonth) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/contrato_mes/", params, contractMonth, String.class).getBody();
    }


    public void deleteContractMonth(int contractMonthID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/contrato_mes/{id}");
        String uri = template.expand(contractMonthID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
