package com.example.primejsf.bean;

import com.example.primejsf.dto.DistrictDTO;
import com.example.primejsf.service.DistrictService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;


@ManagedBean
@Component
@ViewScoped
public class DistrictBean implements Serializable {
    private List<DistrictDTO> products;
    private DistrictDTO selectedProduct;
    private List<DistrictDTO> selectedProducts;

    @Autowired
    private DistrictService districtService;

    @PostConstruct
    public void init(){
        products = districtService.getDistricts();
    }

   public DistrictBean(){}

    public List<DistrictDTO> getProducts() {
        return products;
    }

    public void setProducts(List<DistrictDTO> products) {
        this.products = products;
    }

    public DistrictDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(DistrictDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<DistrictDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<DistrictDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = districtService.openNew();
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
        for (DistrictDTO d: selectedProducts) {
            districtService.deleteDistrict(d.getId());
        }

        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            districtService.createDistrict(this.selectedProduct);

            products = districtService.getDistricts();
            //this.products.add(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            districtService.updateDistrict(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }
        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {

        districtService.deleteDistrict(this.selectedProduct.getId());

        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
