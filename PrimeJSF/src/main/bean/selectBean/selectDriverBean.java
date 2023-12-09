package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.dto.DriverDTO;
import com.example.primejsf.service.DriverService;
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
public class selectDriverBean {
    private List<SelectItem> driverDTOList;
    private String selection;

    @Autowired
    private DriverService driverService;

    @PostConstruct
    public void init() {
//        List<DriverDTO> list = driverService.getDrivers();
//        driverDTOList = new ArrayList<>();
//
//        for (DriverDTO d: list) {
//            SelectItem opt1 = new SelectItem(d.getId(), d.getName());
//            driverDTOList.add(opt1);
//        }
    }

    public List<SelectItem> getDriverDTOList() {
        return driverDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
