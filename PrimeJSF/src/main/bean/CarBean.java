package com.example.primejsf.bean;

import com.example.primejsf.dto.*;
import com.example.primejsf.service.BrandService;
import com.example.primejsf.service.CarService;
import com.example.primejsf.service.DriverService;
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
public class CarBean implements Serializable {

    private List<CarDTO> products;
    private CarDTO selectedProduct;
    private List<CarDTO> selectedProducts;

    @Autowired
    private CarService carService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private FleetService fleetService;
    @Autowired
    private DriverService driverService;

    @PostConstruct
    public void init(){
        products = carService.getCars();
    }

    public BrandDTO showBrand(int id){
        return brandService.getBrandById(id);
    }

    public FleetDTO showFleet(int id){
        return fleetService.getFleetById(id);
    }

    public DriverDTO showDriver(int id){
        return driverService.getDriverById(id);
    }

    public CarBean(){}

    public List<CarDTO> getProducts() {
        return products;
    }

    public void setProducts(List<CarDTO> products) {
        this.products = products;
    }

    public CarDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(CarDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<CarDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<CarDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = carService.openNew();
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
        for (CarDTO c: selectedProducts) {
            carService.deleteCar(c.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            carService.createCar(this.selectedProduct);
            products = carService.getCars();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            carService.updateCar(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        carService.deleteCar(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
