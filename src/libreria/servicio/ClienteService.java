
package libreria.servicio;

import java.util.List;
import libreria.entidades.Cliente;
import libreria.persistencia.ClientePersistencia;


public class ClienteService {

    ClientePersistencia c = new ClientePersistencia();
    
    public void alta(Cliente cliente){
    
    c.alta(cliente);
    
    }
    public List<Cliente> buscarCliente(){
    
    List<Cliente> listCliente= c.buscarCliente();
    
    return listCliente;
    }
      public List<Cliente> buscarClienteID(Integer id){
    
   
    return c.buscarCliente();
    }
    
    
    
}
