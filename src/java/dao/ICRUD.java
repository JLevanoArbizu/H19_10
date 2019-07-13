
package dao;

import java.util.List;

public interface ICRUD<Generico>{
    
    void registrar (Generico gen)throws Exception;
    void modificar (Generico gen)throws Exception;
    void eliminar (Generico gen)throws Exception;
    List<Generico> lstList() throws Exception;
    
}
