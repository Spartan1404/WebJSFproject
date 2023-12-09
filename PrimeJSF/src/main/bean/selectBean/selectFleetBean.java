package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.FleetDTO;
import com.example.primejsf.service.FleetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@RequestScoped
public class selectFleetBean {

    private List<SelectItem> fleetDTOList;
    private String selection;

    @Autowired
    private FleetService fleetService;

    @PostConstruct
    public void init() {
//        List<FleetDTO> list = fleetService.getFleets();
//        fleetDTOList = new ArrayList<>();
//
//        for (FleetDTO f: list) {
//            SelectItem opt = new SelectItem(f.getId(), f.toString());
//            fleetDTOList.add(opt);
//        }
    }

    public List<SelectItem> getFleetDTOList() {
        return fleetDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

}
