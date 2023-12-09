package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.service.DistrictService;
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
public class selectDistrictBean {

    private List<SelectItem> districtDTOList;
    private DistrictService service;

    @Autowired
    private DistrictService districtService;

    @PostConstruct
    public void init() {
//        List<DistrictDTO> list = districtService.getDistricts();
//        districtDTOList = new ArrayList<>();
//
//        for (DistrictDTO d: list) {
//            SelectItem opt = new SelectItem(d.getId(), d.getName());
//            districtDTOList.add(opt);
//        }
    }

    public List<SelectItem> getDistrictDTOList() {
        return districtDTOList;
    }

    public void setDistrictDTOList(List<SelectItem> districtDTOList) {
        this.districtDTOList = districtDTOList;
    }

}
