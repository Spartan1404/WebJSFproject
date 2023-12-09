package com.example.primejsf.bean;

import com.example.primejsf.dto.GroupDTO;
import com.example.primejsf.service.GroupService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class GroupBean implements Serializable {

    private List<GroupDTO> products;
    private GroupDTO selectedProduct;
    private List<GroupDTO> selectedProducts;

    @Autowired
    private GroupService groupService;

    @PostConstruct
    public void init(){
        products = groupService.getGroups();
    }

    public GroupBean(){}

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public List<GroupDTO> getProducts() {
        return products;
    }

    public void setProducts(List<GroupDTO> products) {
        this.products = products;
    }

    public GroupDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(GroupDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<GroupDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<GroupDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = groupService.openNew();
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
        for (GroupDTO g: selectedProducts) {
            groupService.deleteGroup(g.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            groupService.createGroup(this.selectedProduct);
            products = groupService.getGroups();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            groupService.updateGroup(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        groupService.deleteGroup(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
