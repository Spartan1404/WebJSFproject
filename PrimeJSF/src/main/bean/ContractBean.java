package com.example.primejsf.bean;

import com.example.primejsf.dto.*;
import com.example.primejsf.service.CarService;
import com.example.primejsf.service.ContractService;
import com.example.primejsf.service.GroupService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class ContractBean implements Serializable {
    private List<ContractDTO> products;
    private ContractDTO selectedProduct;
    private List<ContractDTO> selectedProducts;

    @Autowired
    private ContractService contractService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private CarService carService;

    @PostConstruct
    public void init(){
        products = contractService.getContracts();
    }

    public CarDTO showCar(int id){
        return carService.getCarById(id);
    }

    public GroupDTO showGroup(int id){
        return groupService.getGroupById(id);
    }

    public ContractBean(){}

    public List<ContractDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ContractDTO> products) {
        this.products = products;
    }

    public ContractDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ContractDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<ContractDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<ContractDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = contractService.openNew();
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
        for(ContractDTO c: selectedProducts) {
           contractService.deleteContract(c.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            contractService.createContract(this.selectedProduct);
            products = contractService.getContracts();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            contractService.updateContract(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        contractService.deleteContract(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
