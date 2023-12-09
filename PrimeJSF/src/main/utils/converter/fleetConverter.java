package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.FleetDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "fleetConverter", managed = true)
public class fleetConverter implements Converter<FleetDTO> {
    @Override
    public FleetDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, FleetDTO fleetDTO) {
        return fleetDTO.toString();
    }
}
