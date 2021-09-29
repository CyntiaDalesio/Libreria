package libreria.persistencia;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import libreria.entidades.Libro;

public class LibroPersistencia {

    private final EntityManagerFactory emf = Persistence.createEntityManagerFactory("libreriaPU");
    private final EntityManager em = emf.createEntityManager();

    public void alta(Libro libro) {
        try {
            em.getTransaction().begin();
            em.persist(libro);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    public void modificar(Libro libro) {
        
          try {
            em.getTransaction().begin();
            em.merge(libro);
            em.getTransaction().commit();

        } catch (Exception e) {

            System.out.println(e.getLocalizedMessage());
        }
    }

    

    public List<Libro> baja(Integer isbn) {

        em.getTransaction().begin();
        List<Libro> libros = em.createQuery("SELECT c FROM Libro c")
                // .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
        em.getTransaction().commit();
        return libros;

    }

    public List<Libro> listarLibro() {
        em.getTransaction().begin();
        List<Libro> libros = em.createQuery("SELECT c FROM Libro c")
                // .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
        em.getTransaction().commit();
       
        return libros;
    }
    
       public List<Libro> listarLibroDisponible() {
        em.getTransaction().begin();
        List<Libro> libros = em.createQuery("SELECT c FROM Libro c where c.ejemplaresRestantes>0")
                // .setParameter("nombre", "%"+nombre+"%")
                .getResultList();
        em.getTransaction().commit();
        return libros;
    }
    
    
    
//------------------------BUSCADORES--------------------------------------------
    public List<Libro> buscarISBN(Long ISBN) {
         em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("SELECT c FROM Libro c WHERE c.isbn = :isbn")
                .setParameter("isbn",ISBN)
                .getResultList();
        em.getTransaction().commit();
        return Libro;
    }
    
        public List<Libro> buscarPorTitulo(String titulo) {
         em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("SELECT c FROM Libro c WHERE c.titulo like :titulo")
                .setParameter("titulo","%" + titulo + "%")
                .getResultList();
        em.getTransaction().commit();
        return Libro;
    }

    public List<Libro> buscarPorAutor(String nombre) {
           em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("SELECT c FROM Autor c WHERE c.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        em.getTransaction().commit();
        return Libro;
    }

    public List<Libro> buscarPorEditorial(String nombreEditorial) {
        em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("SELECT c FROM Autor c WHERE c.nombre LIKE :nombre")
                .setParameter("nombre", "%" + nombreEditorial + "%")
                .getResultList();
        em.getTransaction().commit();
        return Libro;
    }
    
      public List<Libro> buscarPorNombreAutor(String nombre) {
        em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("select l from Libro l join l.autor a where a.nombre like :nombre",Libro.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        em.getTransaction().commit();
        return Libro;
        

    }
    
          public List<Libro> buscarPorNombreEditorial(String nombre) {
        em.getTransaction().begin();
        List<Libro> Libro = em.createQuery("select l from Libro l join l.editorial a where a.nombre like :nombre",Libro.class)
                .setParameter("nombre", "%" + nombre + "%")
                .getResultList();
        em.getTransaction().commit();
        return Libro;
        

    }
    

}
