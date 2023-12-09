package com.example.primejsf.bean;

import com.example.primejsf.dto.BrandDTO;
import com.example.primejsf.service.BrandService;
import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ManagedBean
@ViewScoped
public class BrandBean implements Serializable {
    private List<BrandDTO> products;
    private BrandDTO selectedProduct;
    private List<BrandDTO> selectedProducts;

    @Autowired
    private BrandService brandService;

    @PostConstruct
    public void init(){
        products = brandService.getBrands();
    }

    public List<BrandDTO> getProducts() {
        return products;
    }

    public void setProducts(List<BrandDTO> products) {
        this.products = products;
    }

    public BrandDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(BrandDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<BrandDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<BrandDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = brandService.openNew();
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
        for (BrandDTO b : selectedProducts) {
            brandService.deleteBrand(b.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            brandService.createBrand(this.selectedProduct);
            products = brandService.getBrands();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            brandService.updateBrand(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        brandService.deleteBrand(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
