package com.example.primejsf.bean;

import com.example.primejsf.dto.FeesDTO;
import com.example.primejsf.service.FeesService;
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
public class FeesBean implements Serializable {

    private List<FeesDTO> products;
    private FeesDTO selectedProduct;
    private List<FeesDTO> selectedProducts;

    @Autowired
    private FeesService feesService;

    @PostConstruct
    public void init(){
        products = feesService.getFees();
    }

    public FeesBean(){}

    public List<FeesDTO> getProducts() {
        return products;
    }

    public void setProducts(List<FeesDTO> products) {
        this.products = products;
    }

    public FeesDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(FeesDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<FeesDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<FeesDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = feesService.openNew();
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedProducts.size();
            return size > 1 ? size + " rows selected" : "1 row selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedProducts != null && !this.selectedProducts.isEmpty();
    }

    public void deleteSelectedProducts() {
        for (FeesDTO f: selectedProducts) {
            feesService.deleteFee(f.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            feesService.createFee(this.selectedProduct);
            products = feesService.getFees();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            feesService.updateFee(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        feesService.deleteFee(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
