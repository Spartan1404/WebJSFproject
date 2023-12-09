package com.example.primejsf.bean;

import com.example.primejsf.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@ManagedBean
@ViewScoped
public class LoginBean {
    private String username;
    private String password;

    @Autowired
    private LoginService loginService;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String login(){
       // List<UserDTO> u = new UserService().getItems();
        if ((username.equals("Carlos") && password.equals("1234")) ||
                (username.equals("Alejo") && password.equals("1234")) ||
                (username.equals("Vanesa") && password.equals("1234")) ){
            try {
                getFacesContext().getExternalContext().redirect(getRequest().getContextPath() + "/pages/Home.xhtml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error Message","Login Error, username: Carlos, password: 1234"));
        }

        return null;
    }

    public String loginNuevo(){
        return null;
    }

    protected HttpServletRequest getRequest() {
        return (HttpServletRequest) getFacesContext().getExternalContext().getRequest();
    }

    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    private String dispatchToUrl(String url) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        try {
            dispatcher.forward(request, response);
            facesContext.responseComplete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
