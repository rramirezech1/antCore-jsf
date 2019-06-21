package com.antsolution.antcore.controller.seguridad;

import com.antsolutions.antcore.ejb.UsuarioSeguridadFacadeLocal;
import com.antsolutions.antcore.model.UsuarioSeguridad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpSession;
import org.primefaces.event.SelectEvent;

@Named
@ViewScoped
public class UsuarioSeguridadController implements Serializable {

    @EJB
    private UsuarioSeguridadFacadeLocal usuarioSeguridadEJB;

    private UsuarioSeguridad selected;
    private Boolean deshabilitado = true;
    private List<UsuarioSeguridad> listUsuarioSeguridad = new ArrayList();

    public UsuarioSeguridadController() {
    }

    @PostConstruct
    public void init() {
        buscarUsuarioSeguridad();
    }

    public void newUsuarioSeguridad() {
        deshabilitado = false;
        selected = new UsuarioSeguridad();
        selected.setNombreUsuario("");

    }
    
    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.getExternalContext().getSessionMap().clear();
        ExternalContext externalContext = context.getExternalContext();
        Object session = externalContext.getSession(true);
        HttpSession httpSession = (HttpSession) session;
        httpSession.invalidate();
        return "/login.xhtml?faces-redirect=true";
    }

    public void guardar() {
        try {
            usuarioSeguridadEJB.create(selected);
            listUsuarioSeguridad.add(selected);
            deshabilitado = true;
            selected = null;

        } catch (Exception e) {

        }
    }
    
     public void modificar(){
        try {
            usuarioSeguridadEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            usuarioSeguridadEJB.remove(selected);
            listUsuarioSeguridad.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }

    private void buscarUsuarioSeguridad() {
        listUsuarioSeguridad = usuarioSeguridadEJB.findAll();
    }

    public void onRowSelect(SelectEvent event) {
        selected = (UsuarioSeguridad) event.getObject();
        deshabilitado = false;
    }

    public UsuarioSeguridad getSelected() {
        if (selected == null){
            selected = new UsuarioSeguridad();
        }
        return selected;
    }

    public void setSelected(UsuarioSeguridad selected) {
        if (selected != null && selected.getIdUsuario()!= null){
            this.selected = selected;
        }
    }

    public List<UsuarioSeguridad> getListUsuarioSeguridad() {
        return listUsuarioSeguridad;
    }

    public void setListUsuarioSeguridad(List<UsuarioSeguridad> listUsuarioSeguridad) {
        this.listUsuarioSeguridad = listUsuarioSeguridad;
    }

    public UsuarioSeguridadFacadeLocal getUsuarioSeguridadEJB() {
        return usuarioSeguridadEJB;
    }

    public void setUsuarioSeguridadEJB(UsuarioSeguridadFacadeLocal usuarioSeguridadEJB) {
        this.usuarioSeguridadEJB = usuarioSeguridadEJB;
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

}
