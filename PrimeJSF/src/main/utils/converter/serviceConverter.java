package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.PlannedServicesDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "serviceConverter", managed = true)
public class serviceConverter implements Converter<PlannedServicesDTO> {
    @Override
    public PlannedServicesDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, PlannedServicesDTO serviceDTO) {
        return serviceDTO.toString();
    }
}
