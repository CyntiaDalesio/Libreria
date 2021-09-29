package libreria.servicio;


import java.util.List;
import libreria.entidades.Libro;
import libreria.persistencia.LibroPersistencia;

public class LibroService {

    LibroPersistencia lp = new LibroPersistencia();

    public void alta(Libro libro) {

        lp.alta(libro);

    }

    public void modificar() {

    }

    public void baja(Integer isbn) {

        List<Libro> libro = lp.baja(isbn);

        libro.get(0).setAlta(false);
    }

    public List<Libro> listarLibro() {

        return lp.listarLibro();
    }
    public List<Libro> listarLibroDisponible() {

        return lp.listarLibroDisponible();
    }
    
    
    
      public List<Libro> buscarISBN(Long ISBN){
        return lp.buscarISBN(ISBN);
      
      }
       public List<Libro> buscarPorTitulo(String titulo){
        return lp.buscarPorTitulo(titulo);
      
      }
    public List<Libro> buscarPorAutor(String nombre){
      return lp.buscarPorAutor(nombre);
    }
      public List<Libro> buscarPorEditorial(String nombreEditorial){
        return lp.buscarPorEditorial(nombreEditorial);
      }
    
      
        public List<Libro> buscarPorNombreAutor(String nombre){
        return lp.buscarPorNombreAutor(nombre);
      } 
      
        public List<Libro> buscarPorNombreEditorial(String nombre){
        return lp.buscarPorNombreEditorial(nombre);
      }  
        
        
        
        public void prestarLibro(Libro libro){
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()+1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()-1);
        lp.modificar(libro);
        }
        
         public void DevolverLibro(Libro libro){
        libro.setEjemplaresPrestados(libro.getEjemplaresPrestados()-1);
        libro.setEjemplaresRestantes(libro.getEjemplaresRestantes()+1);
        lp.modificar(libro);
        }
        
        
        
        
}
