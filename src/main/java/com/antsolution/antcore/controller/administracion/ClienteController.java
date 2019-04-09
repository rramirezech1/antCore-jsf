
package com.antsolution.antcore.controller.administracion;

import com.antsolutions.antcore.ejb.ClienteFacadeLocal;
import com.antsolutions.antcore.ejb.DepartamentoFacadeLocal;
import com.antsolutions.antcore.ejb.MunicipioFacadeLocal;
import com.antsolutions.antcore.ejb.PaisFacadeLocal;
import com.antsolutions.antcore.model.Cliente;
import com.antsolutions.antcore.model.Departamento;
import com.antsolutions.antcore.model.Municipio;
import com.antsolutions.antcore.model.Pais;
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
public class ClienteController implements Serializable {
    
    private Cliente selected;
    private Boolean deshabilitado = true;
    private List<Cliente> listCliente = new ArrayList();
    private List<Pais> listPais = new ArrayList();
    private List<Departamento> listDepartamento = new ArrayList();
    private List<Municipio> listMunicipio = new ArrayList();
    
    @EJB
    private ClienteFacadeLocal clienteEJB;
    @EJB
    private PaisFacadeLocal paisEJB;
    @EJB
    private DepartamentoFacadeLocal departamentoEJB;
    @EJB
    private MunicipioFacadeLocal municipioEJB;
    
            
    public ClienteController(){
    }
        
    @PostConstruct
    public void init(){
        buscarCliente();
        listPais =  paisEJB.findAll();  
        listDepartamento =  departamentoEJB.findAll();        
    }
    
    public void newCliente(){
        deshabilitado = false;
        selected = new Cliente();
        selected.setNombreCliente("");
    
    }
    
    public void guardar(){
        try {
            clienteEJB.create(selected);
            listCliente.add(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void modificar(){
        try {
            clienteEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            clienteEJB.remove(selected);
            listCliente.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    

     private void buscarCliente() {
        listCliente = clienteEJB.findAll();
    }
     
    public void onDeptoChange() {
         listMunicipio = municipioEJB.findByDepartamento(selected.getIdDepartamento());
    }
    
    
    public void onPaisChange() {
         listDepartamento = departamentoEJB.findByPais(selected.getIdPais());
    }
   

    public void onRowSelect(SelectEvent event) {
        selected = (Cliente) event.getObject();
        deshabilitado = false;
    }


    public Cliente getSelected() {
        if (selected == null){
            selected = new Cliente();
        }
        return selected;
    }

    public void setSelected(Cliente selected) {
        if (selected != null && selected.getIdCliente() != null){
            this.selected = selected;
        }
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public List<Cliente> getListCliente() {
        return listCliente;
    }
    
    public void setListCliente(List<Cliente> listCliente) {
        this.listCliente = listCliente;
    }
    
     public List<Departamento> getListDepartamento() {
        return listDepartamento;
    }
   
    public List<Municipio> getListMunicipio() {
        return listMunicipio;
    }

    public List<Pais> getListPais() {
        return listPais;
    }
    
    
}
