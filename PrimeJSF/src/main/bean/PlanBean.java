package com.example.primejsf.bean;

import com.example.primejsf.dto.CarDTO;
import com.example.primejsf.dto.PlanDTO;
import com.example.primejsf.service.CarService;
import com.example.primejsf.service.PlanService;
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
public class PlanBean implements Serializable {
    private List<PlanDTO> products;
    private PlanDTO selectedProduct;
    private List<PlanDTO> selectedProducts;

    @Autowired
    private PlanService planService;
    @Autowired
    private CarService carService;

    @PostConstruct
    public void init(){
        products = planService.getPlans();
    }

    public CarDTO showCar(int id){
        return carService.getCarById(id);
    }

    public PlanBean(){}

    public List<PlanDTO> getProducts() {
        return products;
    }

    public void setProducts(List<PlanDTO> products) {
        this.products = products;
    }

    public PlanDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(PlanDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<PlanDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<PlanDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = planService.openNew();
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
        for (PlanDTO p: selectedProducts) {
            planService.deletePlan(p.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            planService.createPlan(this.selectedProduct);
            products = planService.getPlans();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            planService.updatePlan(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        planService.deletePlan(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
