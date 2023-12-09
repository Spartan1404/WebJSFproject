package com.example.primejsf.bean;

import com.example.primejsf.dto.CarDTO;
import com.example.primejsf.dto.PlannedServicesDTO;
import com.example.primejsf.dto.RouteSheetDTO;
import com.example.primejsf.service.CarService;
import com.example.primejsf.service.PlannedServicesService;
import com.example.primejsf.service.RouteSheetService;
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
public class RouteSheetBean implements Serializable {
    private List<RouteSheetDTO> products;
    private List<RouteSheetDTO> selectedProducts;
    private RouteSheetDTO selectedProduct;

    @Autowired
    private RouteSheetService routeSheetService;
    @Autowired
    private CarService carService;
    @Autowired
    private PlannedServicesService plannedServicesService;

    @PostConstruct
    public void init(){
        products = routeSheetService.getRouteSheets();
    }

    public CarDTO showCar(int id){
        return carService.getCarById(id);
    }

    public PlannedServicesDTO showService(int id){
        return plannedServicesService.getPlannedServiceById(id);
    }

    public RouteSheetBean(){}

    public List<RouteSheetDTO> getProducts() {
        return products;
    }

    public void setProducts(List<RouteSheetDTO> products) {
        this.products = products;
    }

    public List<RouteSheetDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<RouteSheetDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public RouteSheetDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(RouteSheetDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void openNew() {
        this.selectedProduct = routeSheetService.openNew();
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
        for (RouteSheetDTO r: this.selectedProducts) {
            routeSheetService.deleteRouteSheet(r.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            routeSheetService.createRouteSheet(this.selectedProduct);
            products = routeSheetService.getRouteSheets();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            routeSheetService.updateRouteSheet(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        routeSheetService.deleteRouteSheet(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
