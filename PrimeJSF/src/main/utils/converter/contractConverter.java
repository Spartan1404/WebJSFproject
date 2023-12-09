package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.ContractDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "contractConverter", managed = true)
public class contractConverter implements Converter<ContractDTO> {
    @Override
    public ContractDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, ContractDTO contractDTO) {
        return contractDTO.toString();
    }
}
