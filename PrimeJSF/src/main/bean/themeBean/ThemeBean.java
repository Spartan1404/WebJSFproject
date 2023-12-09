package com.example.primejsf.bean.themeBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class ThemeBean implements Serializable {

    private String theme = "luna-pink";
    private boolean dark = true;
    private String style = "dark.css";

    public void changeTheme3(){
        if (!dark){
            setDarkTheme();
        } else {
            setLightTheme();
        }
    }

    public boolean isDark() {
        return dark;
    }

    public void setDark(boolean dark) {
        this.dark = dark;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme){
        this.theme = theme;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getStyle() {
        return style;
    }

    public void setDarkTheme() {
        this.theme = "luna-pink";
        style = "dark.css";
    }

    public void setLightTheme() {
        this.theme = "nova-light";
        style = "light.css";
    }
}
