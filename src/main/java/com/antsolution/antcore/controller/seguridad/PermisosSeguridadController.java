package com.antsolution.antcore.controller.seguridad;

import com.antsolutions.antcore.ejb.UsuarioGrupoSeguridadFacadeLocal;
import com.antsolutions.antcore.model.UsuarioGrupoSeguridad;
import com.antsolutions.antcore.ejb.UsuarioSeguridadFacadeLocal;
import com.antsolutions.antcore.model.UsuarioSeguridad;
import com.antsolutions.antcore.ejb.GrupoSeguridadFacadeLocal;
import com.antsolutions.antcore.model.GrupoSeguridad;
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
public class PermisosSeguridadController implements Serializable {

    private UsuarioGrupoSeguridad selected;
    private Boolean deshabilitado = true;
    private List<UsuarioSeguridad> listUsuarioSeguridad = new ArrayList();
    private List<GrupoSeguridad> listGrupoSeguridad = new ArrayList();
    private List<UsuarioGrupoSeguridad> listUsuarioGrupoSeguridad = new ArrayList();
    
    @EJB
    private UsuarioSeguridadFacadeLocal usuarioSeguridadEJB;
    @EJB
    private GrupoSeguridadFacadeLocal grupoSeguridadEJB;
    @EJB
    private UsuarioGrupoSeguridadFacadeLocal usuarioGrupoSeguridadEJB;
    
    public PermisosSeguridadController() {
    }

    @PostConstruct
    public void init() {
        buscarUsuarioGrupoSeguridad();
        listUsuarioSeguridad =  usuarioSeguridadEJB.findAll();  
        listGrupoSeguridad =  grupoSeguridadEJB.findAll();
    }

    public void newUsuarioGrupoSeguridad() {
        deshabilitado = false;
        selected = new UsuarioGrupoSeguridad();
    }

    public void guardar() {
        try {
            usuarioGrupoSeguridadEJB.create(selected);
            listUsuarioGrupoSeguridad.add(selected);
            deshabilitado = true;
            selected = null;

        } catch (Exception e) {

        }
    }
    
     public void modificar(){
        try {
            usuarioGrupoSeguridadEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            usuarioGrupoSeguridadEJB.remove(selected);
            listUsuarioGrupoSeguridad.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }

    private void buscarUsuarioGrupoSeguridad() {
        listUsuarioGrupoSeguridad = usuarioGrupoSeguridadEJB.findAll();
    }

    public void onRowSelect(SelectEvent event) {
        selected = (UsuarioGrupoSeguridad) event.getObject();
        deshabilitado = false;
    }

    public UsuarioGrupoSeguridad getSelected() {
        if (selected == null){
            selected = new UsuarioGrupoSeguridad();
        }
        return selected;
    }

    public void setSelected(UsuarioGrupoSeguridad selected) {
        if (selected != null && selected.getIdUsuarioGrupo()!= null){
            this.selected = selected;
        }
    }

    public List<UsuarioGrupoSeguridad> getListUsuarioGrupoSeguridad() {
        return listUsuarioGrupoSeguridad;
    }

    public void setListUsuarioGrupoSeguridad(List<UsuarioGrupoSeguridad> listUsuarioGrupoSeguridad) {
        this.listUsuarioGrupoSeguridad = listUsuarioGrupoSeguridad;
    }

    public UsuarioGrupoSeguridadFacadeLocal getUsuarioGrupoSeguridadEJB() {
        return usuarioGrupoSeguridadEJB;
    }

    public void setUsuarioGrupoSeguridadEJB(UsuarioGrupoSeguridadFacadeLocal usuarioGrupoSeguridadEJB) {
        this.usuarioGrupoSeguridadEJB = usuarioGrupoSeguridadEJB;
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

    public List<UsuarioSeguridad> getListUsuarioSeguridad() {
        return listUsuarioSeguridad;
    }

    public void setListUsuarioSeguridad(List<UsuarioSeguridad> listUsuarioSeguridad) {
        this.listUsuarioSeguridad = listUsuarioSeguridad;
    }

    public List<GrupoSeguridad> getListGrupoSeguridad() {
        return listGrupoSeguridad;
    }

    public void setListGrupoSeguridad(List<GrupoSeguridad> listGrupoSeguridad) {
        this.listGrupoSeguridad = listGrupoSeguridad;
    }
    

}
