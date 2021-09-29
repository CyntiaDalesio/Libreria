package libreria.servicio;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import libreria.entidades.Editorial;
import libreria.persistencia.EditorialPersistencia;

public class EditorialService {

    EditorialPersistencia ed = new EditorialPersistencia();
    Scanner sc = new Scanner(System.in).useDelimiter("\n");

    public void alta() {
        Editorial editorial = new Editorial();
        System.out.println(editorial.toString());
        
        System.out.println("Ingrese nombre de la editorial");

        editorial.setNombre(sc.next());

        ed.alta(editorial);

    }

    public void modificar() {

        System.out.println("Ingrese nombre de la editorial");

        String nombre = (sc.next());

        List<Editorial> a = ed.buscarPorNombreEditor(nombre);

        System.out.println("Ingrese el nuevo nombre de la editorial");
        nombre = (sc.next());
        System.out.println("ingrese true o false para alta de la editorial");
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

        ed.modificar(a.get(0));

    }

    public void baja() {
        System.out.println("Ingrese nombre de la editorial a dar de baja");

        String nombre = (sc.next());

        List<Editorial> a = ed.buscarPorNombreEditor(nombre);

        a.get(0).setAlta(false);
        ed.baja(a.get(0));

    }

    public void buscarNombreEditor() {

        System.out.println("Ingrese el nombre de la editorial a buscar");
        //ap.buscarPorNombreAutor(sc.next());
        ed.imprimirListaEditores(ed.buscarPorNombreEditor(sc.next()));

    }

    public ArrayList<Editorial> listarEditores() {
   ArrayList<Editorial> ab = new ArrayList();
      try{
     

        for (Editorial edi : ed.listarEditores()) {

            if (edi.getAlta()) {
                ab.add(edi);

            }

        }
        
      }catch(Exception e){
          System.out.println("error en el editorial service "+e.getLocalizedMessage()+" "+e.fillInStackTrace());
      e.printStackTrace();
      }
      return ab;

    }
}
