package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.dto.PlannedServicesDTO;
import com.example.primejsf.service.GroupService;
import com.example.primejsf.service.PlannedServicesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ManagedBean
@RequestScoped
public class selectServiceBean {
    private List<SelectItem> serviceDTOList;
    private String selection;

    @Autowired
    private PlannedServicesService plannedServicesService;

    @PostConstruct
    public void init() {
//        List<PlannedServicesDTO> list = plannedServicesService.getPlannedServices();
//        serviceDTOList = new ArrayList<>();
//
//        for (PlannedServicesDTO pl: list) {
//            SelectItem opt = new SelectItem(pl.getId(), pl.toString());
//            serviceDTOList.add(opt);
//        }
    }

    public List<SelectItem> getServiceDTOList() {
        return serviceDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
