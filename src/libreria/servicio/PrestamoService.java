package libreria.servicio;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import libreria.entidades.Libro;
import static libreria.entidades.Libro_.anio;
import libreria.entidades.Prestamo;
import libreria.persistencia.PrestamoPersistencia;

public class PrestamoService {

    PrestamoPersistencia per = new PrestamoPersistencia();

    public void alta(Prestamo prestamo) {

        per.alta(prestamo);

    }
   
    public void modificar(Prestamo prestamo) {

        per.modificar(prestamo);

    }

    public List<Prestamo> listarPrestamoSinDevol() {

        return per.listarPrestamoSinDevolucion();
    }

    public List<Prestamo> listarPrestamo(Integer id) {

        return per.listarPrestamo(id);
    }

    public boolean verificarFecha(String fecha) {
        System.out.println(fecha.length());
        String Stringanio = fecha.substring(0, 4);
        String Stringmes = fecha.substring(5, 7);
        String Stringdia = fecha.substring(fecha.length() - 2, fecha.length());

        System.out.println("año " + Stringanio);
        System.out.println("mes " + Stringmes);
        System.out.println("dia " + Stringdia);

        Integer anio = Integer.valueOf(Stringanio);
        Integer mes = Integer.valueOf(Stringmes);
        Integer dia = Integer.valueOf(Stringdia);

        
         boolean esFechaValida = true;
        try {
            LocalDate.of(anio, mes, dia);
        } catch (DateTimeException e) {
            esFechaValida = false;
        }
        return esFechaValida;
        
        

    }
    
    

}
