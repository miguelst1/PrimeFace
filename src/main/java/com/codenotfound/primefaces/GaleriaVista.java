package com.codenotfound.primefaces;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
 
@ManagedBean
public class GaleriaVista {
     
    private List<String> imagenes;
     
    @PostConstruct
    public void init() {
    	imagenes = new ArrayList<String>();
        for (int i = 1; i <= 8; i++) {
        	imagenes.add("ajcf" + i + ".jpg");
        }
    }
 
    public List<String> getImagenes() {
        return imagenes;
    }
}