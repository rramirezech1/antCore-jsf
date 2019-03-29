package com.antsolution.antcore.controller.seguridad;

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
public class GrupoSeguridadController implements Serializable {

    @EJB
    private GrupoSeguridadFacadeLocal grupoSeguridadEJB;

    private GrupoSeguridad selected;
    private Boolean deshabilitado = true;
    private List<GrupoSeguridad> listGrupoSeguridad = new ArrayList();

    public GrupoSeguridadController() {
    }

    @PostConstruct
    public void init() {
        buscarGrupoSeguridad();
    }

    public void newGrupoSeguridad() {
        deshabilitado = false;
        selected = new GrupoSeguridad();
        selected.setNombreGrupo("");

    }

    public void guardar() {
        try {
            grupoSeguridadEJB.create(selected);
            listGrupoSeguridad.add(selected);
            deshabilitado = true;
            selected = null;

        } catch (Exception e) {

        }
    }
    
     public void modificar(){
        try {
            grupoSeguridadEJB.edit(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }
    
    public void eliminar(){
        try {
            grupoSeguridadEJB.remove(selected);
            listGrupoSeguridad.remove(selected);
            deshabilitado = true;
            selected = null;
        
        } catch (Exception e){
        
        }
    }

    private void buscarGrupoSeguridad() {
        listGrupoSeguridad = grupoSeguridadEJB.findAll();
    }

    public void onRowSelect(SelectEvent event) {
        selected = (GrupoSeguridad) event.getObject();
        deshabilitado = false;
    }

    public GrupoSeguridad getSelected() {
        if (selected == null){
            selected = new GrupoSeguridad();
        }
        return selected;
    }

    public void setSelected(GrupoSeguridad selected) {
        if (selected != null && selected.getIdGrupo()!= null){
            this.selected = selected;
        }
    }

    public List<GrupoSeguridad> getListGrupoSeguridad() {
        return listGrupoSeguridad;
    }

    public void setListGrupoSeguridad(List<GrupoSeguridad> listGrupoSeguridad) {
        this.listGrupoSeguridad = listGrupoSeguridad;
    }

    public GrupoSeguridadFacadeLocal getGrupoSeguridadEJB() {
        return grupoSeguridadEJB;
    }

    public void setGrupoSeguridadEJB(GrupoSeguridadFacadeLocal grupoSeguridadEJB) {
        this.grupoSeguridadEJB = grupoSeguridadEJB;
    }

    public Boolean getDeshabilitado() {
        return deshabilitado;
    }

    public void setDeshabilitado(Boolean deshabilitado) {
        this.deshabilitado = deshabilitado;
    }

}
