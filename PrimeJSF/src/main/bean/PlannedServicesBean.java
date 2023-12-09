package com.example.primejsf.bean;


import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.dto.PlannedServicesDTO;
import com.example.primejsf.service.GroupService;
import com.example.primejsf.service.PlannedServicesService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class PlannedServicesBean implements Serializable {
    private List<PlannedServicesDTO> products;
    private List<PlannedServicesDTO> selectedProducts;
    private PlannedServicesDTO selectedProduct;

    @Autowired
    private PlannedServicesService plannedServicesService;
    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void init(){
        products = plannedServicesService.getPlannedServices();
    }

    public GroupDTO showGroup(int id){
        return groupService.getGroupById(id);
    }

    public PlannedServicesBean(){}

    public List<PlannedServicesDTO> getProducts() {
        return products;
    }

    public void setProducts(List<PlannedServicesDTO> products) {
        this.products = products;
    }

    public List<PlannedServicesDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<PlannedServicesDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public PlannedServicesDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(PlannedServicesDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void openNew() {
        this.selectedProduct = plannedServicesService.openNew();
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        for (PlannedServicesDTO ps: selectedProducts) {
            plannedServicesService.deletePlannedService(ps.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            plannedServicesService.createPlannedService(this.selectedProduct);
            products = plannedServicesService.getPlannedServices();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            plannedServicesService.updatePlannedService(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        plannedServicesService.deletePlannedService(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
