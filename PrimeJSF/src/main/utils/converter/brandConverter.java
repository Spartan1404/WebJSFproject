package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.BrandDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "brandConverter", managed = true)
public class brandConverter implements Converter<BrandDTO> {
    @Override
    public BrandDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, BrandDTO brandDTO) {
        return brandDTO.toString();
    }
}
