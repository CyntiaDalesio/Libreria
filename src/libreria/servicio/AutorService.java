package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Autor;
import libreria.persistencia.AutorPersistencia;

public class AutorService {

    AutorPersistencia ap = new AutorPersistencia();
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public void alta() {
        Autor autor = new Autor();
        System.out.println("Ingrese nombre del autor");

        autor.setNombre(sc.next());

        ap.alta(autor);

    }

    public void modificar() {

        System.out.println("Ingrese nombre del autor");

        String nombre = (sc.next());

        List<Autor> a = ap.buscarPorNombreAutor(nombre);

        while(a.size()==0){
            System.out.println("nombre de autor no encontrado.Ingrese otro nombre de autor");
        
         nombre = (sc.next());

        a = ap.buscarPorNombreAutor(nombre);
        
        }
        
        
        System.out.println("Ingrese el nuevo nombre del autor");
        nombre = (sc.next());
        System.out.println("ingrese true o false para alta de la autor");
        switch (sc.next().toLowerCase()) {
            case "true":
                a.get(0).setAlta(true);
                break;
            case "false":
                a.get(0).setAlta(false);
            default:
                System.out.println("ingreso inválido, se dejará el alta que tenia previamente");
        }

        a.get(0).setNombre(nombre);

        ap.modificar(a.get(0));

    }

    public void baja() {
        try{
        System.out.println("Ingrese nombre del autor a dar de baja");

        String nombre = (sc.next());

        List<Autor> a = ap.buscarPorNombreAutor(nombre);
        if (a.size()==0) {

            System.out.println("El autor a dar de baja no existe");

        } else {

            a.get(0).setAlta(false);
            ap.baja(a.get(0));
        }

    }catch(Exception e){
    
            System.out.println(e.getMessage());
    }
    
    }

    public void buscarNombreAutor() {

        System.out.println("Ingrese el nombre del autor a buscar");
       
        ap.imprimirListaAutor(ap.buscarPorNombreAutor(sc.next()));

    }

    public ArrayList<Autor> listarAutores() {

        ArrayList<Autor> a = new ArrayList();

        for (Autor autor : ap.listarAutores()) {

            if (autor.getAlta()) {
                a.add(autor);

            }

        }
        return a;

    }

}
