package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.CarDTO;
import com.example.primejsf.dto.ContractDTO;
import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.service.CarService;
import com.example.primejsf.service.ContractService;
import com.example.primejsf.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@RequestScoped
public class selectContractBean {
    private List<SelectItem> contractDTOList;
    private String selection;

    @Autowired
    private ContractService contractService;

    @PostConstruct
    public void init() {
//        List<ContractDTO> list = contractService.getContracts();
//        contractDTOList = new ArrayList<>();
//        for (ContractDTO c: list) {
//            SelectItem opt = new SelectItem(c.getId() ,c.getName());
//            contractDTOList.add(opt);
//        }
    }

    public List<SelectItem> getContractDTOList() {
        return contractDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
