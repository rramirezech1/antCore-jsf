
package com.antsolution.antcore.controller;

import com.antsolutions.antcore.ejb.SucursalFacadeLocal;
import com.antsolutions.antcore.model.Sucursal;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class SucursalController implements Serializable {
    private Sucursal selected;
    private Boolean deshabilitado = true;
    private List<Sucursal> listSucursal = new ArrayList();
       
    @EJB
    private SucursalFacadeLocal sucursalEJB;
    
    public SucursalController(){
    }
        
    @PostConstruct
    public void init(){
        buscarSucursal();
    }
    
    public void newSucursal(){
        deshabilitado = false;
        selected = new Sucursal();
        selected.setNombreSucursal("");
    
    }
    
    public void guardar(){
        try {
            sucursalEJB.create(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
     private void buscarSucursal() {
        listSucursal = sucursalEJB.findAll();
    }
   

    public void onRowSelect(SelectEvent event) {
        selected = (Sucursal) event.getObject();
        deshabilitado = false;
    }

    public Sucursal getSelected() {
        return selected;
    }

    public void setSelected(Sucursal selected) {
        this.selected = selected;
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public List<Sucursal> getListSucursal() {
        return listSucursal;
    }

    public void setListSucursal(List<Sucursal> listSucursal) {
        this.listSucursal = listSucursal;
    }

    public SucursalFacadeLocal getSucursalEJB() {
        return sucursalEJB;
    }

    public void setSucursalEJB(SucursalFacadeLocal sucursalEJB) {
        this.sucursalEJB = sucursalEJB;
    }
    
    

}
