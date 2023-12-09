package com.example.primejsf.bean;

import com.example.primejsf.dto.BrandDTO;
import com.example.primejsf.dto.DriverDTO;
import com.example.primejsf.dto.NADriverDTO;
import com.example.primejsf.service.BrandService;
import com.example.primejsf.service.DriverService;
import com.example.primejsf.service.NADriverService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class NADriverBean {

    private List<NADriverDTO> products;
    private NADriverDTO selectedProduct;
    private List<NADriverDTO> selectedProducts;

    @Autowired
    private NADriverService naDriverService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private BrandService brandService;

    @PostConstruct
    public void init(){
        products = naDriverService.getNADrivers();
    }

    public BrandDTO showBrand(int id){
        return brandService.getBrandById(id);
    }

    public DriverDTO showDriver(int id){
        return driverService.getDriverById(id);
    }

    public NADriverBean(){}

    public List<NADriverDTO> getProducts() {
        return products;
    }

    public void setProducts(List<NADriverDTO> products) {
        this.products = products;
    }

    public NADriverDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(NADriverDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<NADriverDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<NADriverDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = naDriverService.openNew();
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
        for (NADriverDTO na: this.selectedProducts) {
            naDriverService.deleteNADriver(na.getId_driver());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId_driver() == -1) {
            naDriverService.createNADriver(this.selectedProduct);
            products = naDriverService.getNADrivers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            naDriverService.updateNADriver(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        naDriverService.deleteNADriver(this.selectedProduct.getId_driver());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
