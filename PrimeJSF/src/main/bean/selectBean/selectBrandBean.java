package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.BrandDTO;
import com.example.primejsf.service.BrandService;
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
public class selectBrandBean {
    private List<SelectItem> brandDTOList;
    private String selection;

    @Autowired
    private BrandService brandService;

    @PostConstruct
    public void init() {
//        List<BrandDTO> list = brandService.getBrands();
//        brandDTOList = new ArrayList<>();
//
//        for (BrandDTO brandDTO : list) {
//            SelectItem opt = new SelectItem(brandDTO.getId(), brandDTO.getName());
//            brandDTOList.add(opt);
//        }
    }

    public List<SelectItem> getBrandDTOList() {
        return brandDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
