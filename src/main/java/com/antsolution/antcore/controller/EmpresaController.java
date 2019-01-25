
package com.antsolution.antcore.controller;

import com.antsolutions.antcore.ejb.EmpresaFacadeLocal;
import com.antsolutions.antcore.model.Empresa;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class EmpresaController implements Serializable {
    
    @EJB
    private EmpresaFacadeLocal empresaEJB;
    private Empresa empresa;
    
    @PostConstruct
    public void init(){
        empresa = new Empresa();
    }
    
    public void registrar(){
        try {
            empresaEJB.create(empresa);
        
        } catch (Exception e){
        
        }
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
