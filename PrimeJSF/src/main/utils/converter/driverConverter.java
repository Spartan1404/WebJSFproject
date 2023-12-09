package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.DriverDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "driverConverter", managed = true)
public class driverConverter implements Converter<DriverDTO> {
    @Override
    public DriverDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, DriverDTO driverDTO) {
        return driverDTO.toString();
    }
}
