
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Producto;


public class ProductoImpl extends coneccion implements ICRUD<Producto>{

    @Override
    public void registrar(Producto producto) throws Exception {
            this.conectar();
        try {
            String sql = "INSERT INTO PERSONA (NOMPRO,PREPRO,CATEGO,CANPRO,FECVEN,ESTPRO)VALUES (?,?,?,?,?,?)";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getNOMPRO());
            ps.setString(2, producto.getPREPRO());
            ps.setString(3, producto.getCATEGO());
            ps.setString(4, producto.getCANPRO());
            ps.setString(5, producto.getFECVEN());
            ps.setString(6, producto.getESTPRO());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void modificar(Producto producto) throws Exception {
            this.conectar();
        try {
            String sql = "UPDATE PERSONA SET NOMPRO=?,PREPRO=?,CATEGO=?,CANPRO=?,FECVEN=?,ESTPRO=? WHERE IDPRO=?";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getNOMPRO());
            ps.setString(2, producto.getPREPRO());
            ps.setString(3, producto.getCATEGO());
            ps.setString(4, producto.getCANPRO());
            ps.setString(5, producto.getFECVEN());
            ps.setString(6, producto.getESTPRO());
            ps.setString(7, producto.getIDPRO());
            ps.executeUpdate();
            ps.clearParameters();
            ps.close();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.desconectar();
        }}
    
   

    @Override
    public void eliminar(Producto producto) throws Exception {
            this.conectar();
        try {
            String sql = "ALTER TABLE ESTPRO FROM PERSONA  WHERE ESTPRO LIKE 'I' ";
            PreparedStatement ps = this.getCn().prepareStatement(sql);
            ps.setString(1, producto.getIDPRO());
            ps.setString(2, producto.getESTPRO());
            ps.close();
        } catch (SQLException e) {
             throw e;
        }finally{
        this.desconectar();
        }}

    @Override
    public List<Producto> lstList() throws Exception {

        List<Producto> listado;
        ResultSet rs;
        try {
            this.conectar();
            String sql = "SELECT * FROM PERSONAL WHERE ESTPRO = 'A'";
            listado = new ArrayList<>();
            PreparedStatement ps = this.getCn().prepareCall(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIDPRO(rs.getString("IDPRO"));
                producto.setNOMPRO(rs.getString("NOMPRO"));
                producto.setNOMPRO(rs.getString("PREPRO"));
                producto.setNOMPRO(rs.getString("CATEGO"));
                producto.setNOMPRO(rs.getString("CANPRO"));
                producto.setNOMPRO(rs.getString("FECVEN"));
                producto.setNOMPRO(rs.getString("ESTPRO"));
                listado.add(producto);
            }
        } catch (SQLException e) {
            throw e;

        } finally {
            this.desconectar();
        }
        return listado;   
    }
    
}
