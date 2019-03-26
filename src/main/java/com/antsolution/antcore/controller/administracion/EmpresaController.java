
package com.antsolution.antcore.controller.administracion;

import com.antsolutions.antcore.ejb.EmpresaFacadeLocal;
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
public class EmpresaController implements Serializable {
    
    private Empresa selected;
    private Boolean deshabilitado = true;
    private List<Empresa> listEmpresa = new ArrayList();
       
    @EJB
    private EmpresaFacadeLocal empresaEJB;
    
    public EmpresaController(){
    }
        
    @PostConstruct
    public void init(){
        buscarEmpresa();
    }
    
    public void newEmpresa(){
        deshabilitado = false;
        selected = new Empresa();
        selected.setNombreEmpresa("");
    
    }
    
    public void guardar(){
        try {
            empresaEJB.create(selected);
            listEmpresa.add(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void modificar(){
        try {
            empresaEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            empresaEJB.remove(selected);
            listEmpresa.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    

     private void buscarEmpresa() {
        listEmpresa = empresaEJB.findAll();
    }
   

    public void onRowSelect(SelectEvent event) {
        selected = (Empresa) event.getObject();
        deshabilitado = false;
    }


    public Empresa getSelected() {
        if (selected == null){
            selected = new Empresa();
        }
        return selected;
    }

    public void setSelected(Empresa selected) {
        if (selected != null && selected.getIdEmpresa() != null){
            this.selected = selected;
        }
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public List<Empresa> getListEmpresa() {
        return listEmpresa;
    }

    public void setListEmpresa(List<Empresa> listEmpresa) {
        this.listEmpresa = listEmpresa;
    }
    
   
}
