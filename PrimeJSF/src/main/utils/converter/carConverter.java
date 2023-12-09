package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.CarDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "carConverter", managed = true)
public class carConverter implements Converter<CarDTO> {

    @Override
    public CarDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, CarDTO carDTO) {
        return carDTO.toString();
    }
}
