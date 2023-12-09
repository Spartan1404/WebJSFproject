package com.example.primejsf.bean.selectBean;

import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.service.GroupService;
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
public class selectGroupBean {
    private List<SelectItem> groupDTOList;
    private String selection;

    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void init() {
//        List<GroupDTO> list = groupService.getGroups();
//        groupDTOList = new ArrayList<>();
//
//        for (GroupDTO g:list) {
//            SelectItem opt = new SelectItem(g.getId(), g.toString());
//            groupDTOList.add(opt);
//        }
    }

    public List<SelectItem> getGroupDTOList() {
        return groupDTOList;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }
}
