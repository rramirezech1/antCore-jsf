
package com.antsolution.antcore.controller.administracion;

import com.antsolutions.antcore.ejb.EmpresaFacadeLocal;
import com.antsolutions.antcore.ejb.SucursalFacadeLocal;
import com.antsolutions.antcore.model.Sucursal;
import com.antsolutions.antcore.model.Empresa;
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
    private List<Empresa> listEmpresa = new ArrayList();
       
    @EJB
    private SucursalFacadeLocal sucursalEJB;
    
    @EJB
    private EmpresaFacadeLocal empresaEJB;
    
    public SucursalController(){
    }
        
    @PostConstruct
    public void init(){
        listEmpresa =  empresaEJB.findAll();  
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
            listSucursal.add(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void modificar(){
        try {
            sucursalEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            sucursalEJB.remove(selected);
            listSucursal.remove(selected);
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
        if (selected == null){
            selected = new Sucursal();
        }
        return selected;
    }

    public void setSelected(Sucursal selected) {
        if (selected != null && selected.getIdSucursal() != null){
            this.selected = selected;
        }
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
    
     public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }
   
}
