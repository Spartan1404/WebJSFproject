package com.example.primejsf.bean;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.dto.DriverDTO;
import com.example.primejsf.service.DistrictService;
import com.example.primejsf.service.DriverService;
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
public class DriverBean implements Serializable {
    private List<DriverDTO> products;
    private List<DriverDTO> selectedProducts;
    private DriverDTO selectedProduct;

    @Autowired
    private DriverService driverService;
    @Autowired
    private DistrictService districtService;

    @PostConstruct
    public void init(){
        products = driverService.getDrivers();
    }

    public DistrictDTO showDistrict(int id){
        return districtService.getDistrictById(id);
    }

    public DriverBean(){

    }

    public List<DriverDTO> getProducts() {
        return products;
    }

    public void setProducts(List<DriverDTO> products) {
        this.products = products;
    }

    public List<DriverDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<DriverDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public DriverDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(DriverDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }



    public void openNew() {
        this.selectedProduct = driverService.openNew();
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

        for (DriverDTO d: selectedProducts) {
            driverService.deleteDriver(d.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            driverService.createDriver(this.selectedProduct);
            products = driverService.getDrivers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            driverService.updateDriver(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        driverService.deleteDriver(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
