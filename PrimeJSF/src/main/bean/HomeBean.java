package com.example.primejsf.bean;

import javax.faces.bean.ManagedBean;
import java.io.IOException;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
public class HomeBean {

    public String logout(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/login/login.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String navigateToHome(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/Home.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String navigateToFees(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/fees.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToDistrict(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/district.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToFleets(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/fleet.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToGroups(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/group.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToCars(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/car.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToContracts(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/contract.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToBrands(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/brand.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToPlans(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/plan.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToPlannedServices(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/plannedService.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToIncomeCut(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/contractMonth.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToRouteSheet(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/route.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToDrivers(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/drivers.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
    public String navigateToUsers(){
        try {
            getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/data/users.xhtml");
        } catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }
}
