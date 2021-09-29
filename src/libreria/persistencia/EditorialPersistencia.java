package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Editorial;

public class EditorialPersistencia {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void alta(Editorial editorial) {
        try {
            em.getTransaction().begin();
            em.persist(editorial);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    public void modificar(Editorial editorial) {

        em.getTransaction().begin();

        editorial = em.find(Editorial.class, editorial.getId());

        em.merge(editorial);
        em.getTransaction().commit();
    }

    public void baja(Editorial editorial) {
        em.getTransaction().begin();

        editorial = em.find(Editorial.class, editorial.getId());

        em.merge(editorial);
        em.getTransaction().commit();

    }

    public List<Editorial> buscarPorNombreEditor(String nombre) {
        em.getTransaction().begin();
        List<Editorial> editoriales = em.createQuery("SELECT c FROM Editorial c WHERE c.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        em.getTransaction().commit();
        return editoriales;
    }

    public List<Editorial> listarEditores() {
        em.getTransaction().begin();
        List<Editorial> editoriales = em.createQuery("SELECT a FROM Editorial a")
                // .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
        em.getTransaction().commit();
        return editoriales;
    }

    public void imprimirListaEditores(List<Editorial> lista) {
        for (Editorial ed : lista) {
            System.out.println(ed.getNombre());
        }
    }

}
