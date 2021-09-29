package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;

public class PrestamoPersistencia {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void alta(Prestamo prest) {
        try {
            em.getTransaction().begin();
            em.persist(prest);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    
        public void modificar(Prestamo prest) {
        try {
            em.getTransaction().begin();
            em.merge(prest);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }
    
    
    
    
      public List<Prestamo> listarPrestamoSinDevolucion() {
        em.getTransaction().begin();
        List<Prestamo> prestamo = em.createQuery("SELECT c FROM Prestamo c where c.fechaDevolucion is null")
                .getResultList();
        em.getTransaction().commit();
        return prestamo;
    }
      public List<Prestamo> listarPrestamo(Integer id) {
        em.getTransaction().begin();
        List<Prestamo> prestamo = em.createQuery("SELECT c FROM Prestamo c where c.id like :id")
                 .setParameter("id",id)
                .getResultList();
        em.getTransaction().commit();
        return prestamo;
    }
    
    
}
