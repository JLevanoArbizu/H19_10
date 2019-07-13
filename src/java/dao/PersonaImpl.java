package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Persona;

public class PersonaImpl extends coneccion implements ICRUD<Persona> {

    @Override
    public void registrar(Persona persona) throws Exception {
        this.conectar();
        try {
            String sql = "INSERT INTO PERSONA (NOMPER,APEPER,USEPER,PSWPER,FECPER,DNIPER,CELPER,TIPPER,ESTPER)VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getUSEPER());
            ps.setString(4, persona.getPSWPER());
            ps.setString(5, persona.getFECPER());
            ps.setString(6, persona.getDNIPER());
            ps.setString(7, persona.getCELPER());
            ps.setString(8, persona.getTIPPER());
            ps.setString(9, persona.getESTPER());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void modificar(Persona persona) throws Exception {
        this.conectar();
        try {
            String sql = "UPDATE PERSONA SET NOMPER=?,APEPER=?,USEPER=?,PSWPER=?,FECPER=?,DNIPER=?,CELPER=?,TIPPER=?,ESTPER=? WHERE IDPER=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getNOMPER());
            ps.setString(2, persona.getAPEPER());
            ps.setString(3, persona.getUSEPER());
            ps.setString(4, persona.getPSWPER());
            ps.setString(5, persona.getFECPER());
            ps.setString(6, persona.getDNIPER());
            ps.setString(7, persona.getCELPER());
            ps.setString(8, persona.getTIPPER());
            ps.setString(9, persona.getESTPER());
            ps.setString(10, persona.getIDPER());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }
       

    @Override
    public void eliminar(Persona persona) throws Exception {
        this.conectar();
        try {
            String sql = "ALTER TABLE PERSONA FROM PERSONA  WHERE ESTPER LIKE 'I' ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, persona.getESTPER());
            ps.close();
        } catch (SQLException e) {
             throw e;
        }finally{
        this.desconectar();
        }
    }

    @Override
    public List<Persona> lstList() throws Exception {
        List<Persona> listado;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM PERSONAL WHERE ESTPER = 'A'";
            listado = new ArrayList<>();
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Persona persona = new Persona();
                persona.setIDPER(rs.getString("IDPER"));
                persona.setNOMPER(rs.getString("NOMPER"));
                persona.setAPEPER(rs.getString("APEPER"));
                persona.setUSEPER(rs.getString("USEPER"));
                persona.setPSWPER(rs.getString("PSWPER"));
                persona.setFECPER(rs.getString("FECPER"));
                persona.setDNIPER(rs.getString("DNIPER"));
                persona.setCELPER(rs.getString("CELPER"));
                persona.setTIPPER(rs.getString("TIPPER"));
                persona.setESTPER(rs.getString("ESTPER"));
                listado.add(persona);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.desconectar();
        }
        return listado;

    }

}
