package controlador;

import dao.PersonaImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import modelo.Persona;

@Named(value = "personalCon")
@SessionScoped
public class PersonaC implements Serializable {

    private Persona persona = new Persona();
    private PersonaImpl dao;
    private List<Persona> listadoPer;
    private String accionPer;


    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }

    }

    public PersonaC() {
        dao = new PersonaImpl();
        persona = new Persona();
       

    }


    public void limpiar() {
        persona = new Persona();
    }

    public void registrar() throws Exception {
        try {
            dao = new PersonaImpl();
            dao.registrar(persona);
            limpiar();
            listar();
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Registro", "completo"));
        } catch (Exception e) {
             FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "ERROR", " al reagistrar"));
            throw e;
        }
    }

    public void modificar() throws Exception {
        try {
            dao = new PersonaImpl();
            dao.modificar(persona);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "modificacion correcta", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "error al modificar", null));
       
            throw e;
        }
    }

    public void eliminar(Persona per) throws Exception {
        try {
            dao = new PersonaImpl();
            dao.eliminar(per);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", ""));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "error", " al eliminar"));
            throw e;
        }
    }

    public void listar() throws Exception {
        PersonaImpl Conexion;
         listadoPer = new ArrayList();
        try {
            Conexion = new PersonaImpl();
            listadoPer = Conexion.lstList();
        } catch (Exception e) {
            throw e;
        }
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public List<Persona> getListadoPer() {
        return listadoPer;
    }

    public void setListadoPer(List<Persona> listadoPer) {
        this.listadoPer = listadoPer;
    }


    public PersonaImpl getDao() {
        return dao;
    }

    public void setDao(PersonaImpl dao) {
        this.dao = dao;
    }


    public String getAccionPer() {
        return accionPer;
    }

    public void setAccionPer(String accionPer) {
        limpiar();
        this.accionPer = accionPer;
    }

}
