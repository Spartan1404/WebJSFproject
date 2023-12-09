package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.service.DistrictService;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "districtConverter", managed = true)
public class districtConverter implements Converter<DistrictDTO> {

    private DistrictService service = new DistrictService();

    @Override
    public DistrictDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        System.out.println(s + "LLegamos al converter");
        return new DistrictDTO("New District");
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, DistrictDTO districtDTO) {
        System.out.println(districtDTO.getName() + "PINGAAAAA");
        return districtDTO.toString();
    }


}
