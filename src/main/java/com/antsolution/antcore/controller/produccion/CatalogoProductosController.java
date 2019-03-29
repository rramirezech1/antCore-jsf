
package com.antsolution.antcore.controller.produccion;

import com.antsolutions.antcore.ejb.CatalogoProductosFacadeLocal;
import com.antsolutions.antcore.model.CatalogoProductos;
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
public class CatalogoProductosController implements Serializable {
    
    private CatalogoProductos selected;
    private Boolean deshabilitado = true;
    private List<CatalogoProductos> listProducto = new ArrayList();
    
       
    @EJB
    private CatalogoProductosFacadeLocal productosEJB;
    
    
    public CatalogoProductosController(){
    }
        
    @PostConstruct
    public void init(){
        listProducto =  productosEJB.findAll();  
        buscarProductos();
    }
    
    public void newProducto(){
        deshabilitado = false;
        selected = new CatalogoProductos();
        selected.setDescripcionProducto("");
    
    }
    
    public void guardar(){
        try {
            productosEJB.create(selected);
            listProducto.add(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void modificar(){
        try {
            productosEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            productosEJB.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    

     private void buscarProductos() {
        listProducto = productosEJB.findAll();
    }
   

    public void onRowSelect(SelectEvent event) {
        selected = (CatalogoProductos) event.getObject();
        deshabilitado = false;
    }

    public CatalogoProductos getSelected() {
         if (selected == null){
            selected = new CatalogoProductos();
        }
        return selected;
    }

    public void setSelected(CatalogoProductos selected) {
        if (selected != null && selected.getIdProducto()!= null){
            this.selected = selected;
        }
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public List<CatalogoProductos> getListProducto() {
        return listProducto;
    }

    public void setListProducto(List<CatalogoProductos> listProducto) {
        this.listProducto = listProducto;
    }

    public CatalogoProductosFacadeLocal getProductosEJB() {
        return productosEJB;
    }

    public void setProductosEJB(CatalogoProductosFacadeLocal productosEJB) {
        this.productosEJB = productosEJB;
    }


    
    
}
