package com.example.primejsf.bean;

import com.example.primejsf.dto.FleetDTO;
import com.example.primejsf.service.FleetService;
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
public class FleetBean implements Serializable {

    private List<FleetDTO> products;
    private FleetDTO selectedProduct;
    private List<FleetDTO> selectedProducts;

    @Autowired
    private  FleetService fleetService;

    @PostConstruct
    public void init(){
        products = fleetService.getFleets();
    }

    public FleetBean(){}

    public List<FleetDTO> getProducts() {
        return products;
    }

    public void setProducts(List<FleetDTO> products) {
        this.products = products;
    }

    public FleetDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(FleetDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<FleetDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<FleetDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = fleetService.openNew();
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
        for (FleetDTO f: selectedProducts) {
            fleetService.deleteFleet(f.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            fleetService.createFleet(this.selectedProduct);
            products = fleetService.getFleets();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            fleetService.updateFleet(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        fleetService.deleteFleet(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
