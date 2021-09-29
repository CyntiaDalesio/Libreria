package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Autor;

public class AutorPersistencia {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void alta(Autor autor) {
        try {
            em.getTransaction().begin();
            em.persist(autor);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    public void modificar(Autor autor) {

        em.getTransaction().begin();

        autor = em.find(Autor.class, autor.getId());

        em.merge(autor);
        em.getTransaction().commit();
    }

    public void baja(Autor autor) {
        em.getTransaction().begin();

        autor = em.find(Autor.class, autor.getId());

        em.merge(autor);
        em.getTransaction().commit();

    }

    public List<Autor> buscarPorNombreAutor(String nombre) {
        em.getTransaction().begin();
        List<Autor> autores = em.createQuery("SELECT c FROM Autor c WHERE c.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        em.getTransaction().commit();
        return autores;
    }

    public List<Autor> listarAutores() {
        em.getTransaction().begin();
        List<Autor> autores = em.createQuery("SELECT c FROM Autor c")
                // .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
        em.getTransaction().commit();
        return autores;
    }

    public void imprimirListaAutor(List<Autor> lista) {
        for (Autor autores : lista) {
            System.out.println(autores.getNombre());
        }
    }

}
