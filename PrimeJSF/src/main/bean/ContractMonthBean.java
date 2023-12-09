package com.example.primejsf.bean;

import com.example.primejsf.dto.ContractDTO;
import com.example.primejsf.dto.ContractMonthDTO;
import com.example.primejsf.dto.FeesDTO;
import com.example.primejsf.service.ContractMonthService;
import com.example.primejsf.service.ContractService;
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
public class ContractMonthBean implements Serializable {
    private List<ContractMonthDTO> products;
    private List<ContractMonthDTO> selectedProducts;
    private ContractMonthDTO selectedProduct;

    @Autowired
    private ContractMonthService contractMonthService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private FeesService feesService;

    @PostConstruct
    public void init(){
        products = contractMonthService.getContractMonths();
    }

    public FeesDTO showFee(int id){
        return feesService.getFeeById(id);
    }

    public ContractDTO showContract(int id){
        return contractService.getContractById(id);
    }

    public ContractMonthBean(){}

    public List<ContractMonthDTO> getProducts() {
        return products;
    }

    public void setProducts(List<ContractMonthDTO> products) {
        this.products = products;
    }

    public List<ContractMonthDTO> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<ContractMonthDTO> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    public ContractMonthDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ContractMonthDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public void openNew() {
        this.selectedProduct = contractMonthService.openNew();
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
        for (ContractMonthDTO c: selectedProducts) {
            contractMonthService.deleteContractMonth(c.getId());
        }
        this.products.removeAll(this.selectedProducts);
        this.selectedProducts = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    public void saveProduct() {
        if (this.selectedProduct.getId() == -1) {
            contractMonthService.createContractMonth(this.selectedProduct);
            products = contractMonthService.getContractMonths();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Added"));
        }
        else {
            contractMonthService.updateContractMonth(this.selectedProduct);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));
        }

        PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void deleteProduct() {
        contractMonthService.deleteContractMonth(this.selectedProduct.getId());
        this.products.remove(this.selectedProduct);
        this.selectedProducts.remove(this.selectedProduct);
        this.selectedProduct = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
}
