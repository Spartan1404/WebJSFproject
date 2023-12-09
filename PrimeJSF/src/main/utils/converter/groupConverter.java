package com.example.primejsf.utils.converter;

import com.example.primejsf.dto.GroupDTO;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value = "groupConverter", managed = true)
public class groupConverter implements Converter<GroupDTO> {
    @Override
    public GroupDTO getAsObject(FacesContext facesContext, UIComponent uiComponent, String s) {
        return null;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, GroupDTO groupDTO) {
        return groupDTO.toString();
    }
}
