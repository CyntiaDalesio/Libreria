
package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Cliente;


public class ClientePersistencia {
    
    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    private final EntityManager em = emf.createEntityManager();
    
    
    
    public void alta(Cliente cliente) {
        try {
            em.getTransaction().begin();
            em.persist(cliente);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    
    public List<Cliente> buscarCliente(){
    
      em.getTransaction().begin();
        List<Cliente> cl = em.createQuery("SELECT c FROM Cliente c")
                .getResultList();
        em.getTransaction().commit();
        return cl;
    }
    public List<Cliente> buscarClienteID(Integer id){
     em.getTransaction().begin();
        List<Cliente> cl = em.createQuery("SELECT c FROM Cliente c where c.id like :id")
                .setParameter("id",id)
                .getResultList();
        em.getTransaction().commit();
        return cl;
    }
    
}
