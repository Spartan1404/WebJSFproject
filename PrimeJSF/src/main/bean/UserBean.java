package com.example.primejsf.bean;

import com.example.primejsf.dto.UsersDTO;
import com.example.primejsf.service.UserService;
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
public class UserBean implements Serializable {
    private List<UsersDTO> products;
    private UsersDTO selectedProduct;
    private List<UsersDTO> selectedProducts;

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init(){
        products = userService.getUsers();
    }

    public UserBean(){}

    public List<UsersDTO> getProducts() {
        return products;
    }

    public void setProducts(List<UsersDTO> products) {
        this.products = products;
    }

    public UsersDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(UsersDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<UsersDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<UsersDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public void openNew() {
        this.selectedProduct = userService.openNew();
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
        for (UsersDTO u: this.selectedProducts) {
            userService.deleteUser(u.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            userService.createUser(this.selectedProduct);
            products = userService.getUsers();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            userService.updateUser(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        userService.deleteUser(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
