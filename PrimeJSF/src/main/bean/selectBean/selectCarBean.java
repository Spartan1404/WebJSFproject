package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.CarDTO;
import com.example.primejsf.service.CarService;
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
public class selectCarBean {
    private List<SelectItem> carDTOList;
    private String selection;

    @Autowired
    private CarService carService;

    @PostConstruct
    public void init() {
//        List<CarDTO> list = carService.getCars();
//        carDTOList = new ArrayList<>();
//
//        for(CarDTO carDTO: list){
//            SelectItem opt = new SelectItem(carDTO.getId(), carDTO.getPlaca());
//            carDTOList.add(opt);
//        }
    }

    public List<SelectItem> getCarDTOList() {
        return carDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
