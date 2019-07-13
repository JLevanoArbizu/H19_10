
package controlador;

import dao.ProductoImpl;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Producto;


@Named(value = "productoC")
@SessionScoped
public class ProductoC implements Serializable {

    
    private Producto producto = new Producto();
    private ProductoImpl dao;
    private List<Producto> listarpro;
    private String accionPro;


    @PostConstruct
    public void init() {
        try {
            listar();
        } catch (Exception e) {
        }

    }

    public ProductoC() {
        dao = new ProductoImpl();
        producto =new Producto();

    }


    public void limpiar() {
        producto = new Producto();
    }

    public void registrar() throws Exception {
        try {
            dao = new ProductoImpl();
            dao.registrar(producto);
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
            dao = new ProductoImpl();
            dao.modificar(producto);
            listar();
            limpiar();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "modificacion correcta", null));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "error al modificar", null));
       
            throw e;
        }
    }

    public void eliminar(Producto pro) throws Exception {
        try {
            dao = new ProductoImpl();
            dao.eliminar(pro);
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Eliminado", ""));
            listar();
            limpiar();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "error", " al eliminar"));
            throw e;
        }
    }

    public void listar() throws Exception {
        ProductoImpl Conexion;
         listarpro = new ArrayList<>();
        try {
            Conexion = new ProductoImpl();
            listarpro = Conexion.lstList();
        } catch (Exception e) {
            throw e;
        }
    }
    
    
}
