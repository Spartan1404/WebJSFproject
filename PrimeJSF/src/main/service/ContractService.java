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
public class ContractService {

    @Autowired
    private RestService restService;

    public ContractDTO openNew(){
        return new ContractDTO("", null, null, 0, 0);
    }

    public List<ContractDTO> getContracts() {
        List<ContractDTO> ContractList = new ArrayList<ContractDTO>();
        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDTO> apiRestMapper = new ApiRestMapper<>();
            String response = (String)restService.GET("/api/v1/contrato/", params, String.class).getBody();
            System.out.println("\nResponse: " + response + "\n");
            ContractList = apiRestMapper.mapList(response, ContractDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ContractList;
    }

    public ContractDTO getContractById(int contractID) {
        ContractDTO contract = null;

        try {
            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
            ApiRestMapper<ContractDTO> apiRestMapper = new ApiRestMapper<>();

            UriTemplate template = new UriTemplate("/api/v1/contrato/{id}");
            String uri = template.expand(contractID).toString();
            String response = (String)restService.GET(uri, params, String.class).getBody();
            contract = apiRestMapper.mapOne(response, ContractDTO.class);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return contract;
    }

    public void createContract(ContractDTO contract) {
        restService.POST("/api/v1/contrato/", contract, String.class).getBody();
    }


    public void updateContract(ContractDTO contract) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        restService.PUT("/api/v1/contrato/", params, contract, String.class).getBody();
    }


    public void deleteContract(int contractID) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        UriTemplate template = new UriTemplate("/api/v1/contrato/{id}");
        String uri = template.expand(contractID).toString();
        restService.DELETE(uri, params, String.class, null).getBody();
    }
}
