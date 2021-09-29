package libreria;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import libreria.entidades.Autor;
import libreria.entidades.Cliente;
import libreria.entidades.Editorial;
import libreria.entidades.Libro;
import libreria.entidades.Prestamo;
import libreria.servicio.AutorService;
import libreria.servicio.ClienteService;
import libreria.servicio.EditorialService;
import libreria.servicio.LibroService;
import libreria.servicio.PrestamoService;

public class Libreria {

    public static void main(String[] args) {

        LibroService libro = new LibroService();
        EditorialService edit = new EditorialService();
        AutorService autor = new AutorService();
        Scanner sc = new Scanner(System.in).useDelimiter("\n");
        ClienteService cli = new ClienteService();
        PrestamoService pres = new PrestamoService();
        boolean flag = true;

        do {

            System.out.println("*************BIENVENIDO A EDITORIAL**********");
            System.out.println("1- Alta/baja/modificar AUTOR ****************");
            System.out.println("2-  Alta/baja/modificar EDITORIAL************");
            System.out.println("3- Alta/baja/modificar LIBRO*****************");
            System.out.println("4- Buscar autor por nombre*******************");
            System.out.println("5- Buscar libro por ISBN*********************");
            System.out.println("6- Buscar libro por nombre de Autor**********");
            System.out.println("7- Buscar libro por nombre de EDitorial******");
            System.out.println("71- buscar libro por titulo******************");
            System.out.println("8- Salir*************************************");
            System.out.println("9- listar los autores************************");
            System.out.println("10-listar los editoriales********************");
            System.out.println("11- listar libro*****************************");
            System.out.println("12-Crear nuevo cliente***********************");
            System.out.println("13-Prestar Libro*****************************");
            System.out.println("14-Devolver libro****************************");
            System.out.println("15-Buscar los prestamos de un cliente********");

            switch (sc.next()) {
                case "1":
                    System.out.println("Ingrese la opcion para AUTOR");
                    System.out.println("ingrese 1 para alta");
                    System.out.println("ingrese 2 para baja");
                    System.out.println("ingrese 3 para modificar");

                    switch (sc.next()) {
                        case "1":
                            autor.alta();
                            break;

                        case "2":
                            autor.baja();
                            break;
                        case "3":
                            autor.modificar();
                            break;

                        default:
                            System.out.println("Ingreso invalido.");
                    }

                    break;

                case "2":
                    System.out.println("Ingrese la opcion para EDITORIAL");
                    System.out.println("ingrese 1 para alta");
                    System.out.println("ingrese 2 para baja");
                    System.out.println("ingrese 3 para modificar");

                    switch (sc.next()) {
                        case "1":
                            edit.alta();
                            break;

                        case "2":
                            edit.baja();
                            break;
                        case "3":
                            edit.modificar();
                            break;

                        default:
                            System.out.println("Ingreso invalido.");

                    }
                    break;
                case "3":

                    System.out.println("Ingrese la opcion para LIBRO");
                    System.out.println("ingrese 1 para alta");
                    System.out.println("ingrese 2 para baja");
                    System.out.println("ingrese 3 para modificar");

                    switch (sc.next()) {
                        case "1":
                            try {

                                System.out.println("Ingrese el isbn del libro");
                                Long isbn = sc.nextLong();

                                List<Libro> exist = libro.buscarISBN(isbn);
                            
                                while (!exist.isEmpty()) {

                                    System.out.println("ISBN repetido.Ingrese nuevamente");
                                    isbn = sc.nextLong();

                                }

                                System.out.println("ingrese el titulo del libro");
                                String titulo = sc.next();
                                System.out.println("ingrese el año del libro");
                                Integer anio = sc.nextInt();
                                System.out.println("ingrese los ejemplares del libro");
                                Integer ejemplares = sc.nextInt();
                                Libro libro1 = new Libro(isbn, titulo, anio, ejemplares, 0, ejemplares, true);
                                System.out.println("Los autores existentes son: ");
                                ArrayList<Autor> listarAutore = autor.listarAutores();
                                for (Autor autor1 : listarAutore) {

                                    System.out.println("autor: " + autor1.getNombre() + " id: " + autor1.getId());

                                }
                                System.out.println("Ingrese el id del autor");

                                Integer a = sc.nextInt();
                                for (Autor autor1 : listarAutore) {

                                    if (autor1.getId().equals(a)) {

                                        libro1.setAutor(autor1);
                                        break;

                                    }
                                }
                                System.out.println("Los editoriales existentes son:");
                                ArrayList<Editorial> ed = edit.listarEditores();
                                for (Editorial editorial : ed) {
                                    System.out.println(editorial.toString());
                                }
                                System.out.println("ingrese el id de la editorial");
                                Integer b = sc.nextInt();
                                for (Editorial editorial : ed) {

                                    if (editorial.getId().equals(b)) {
                                        libro1.setEditorial(editorial);
                                        break;
                                    }

                                }
                                libro.alta(libro1);
                            } catch (Exception e) {

                                System.out.println("Datos ingresados incorrectos");

                            }
                            break;

                        case "2":
                            System.out.println("ingrese el isbn del libro a dar de baja");
                            Integer isbn = sc.nextInt();

                            libro.baja(isbn);
                            break;
                        case "3":
                            libro.modificar();
                            break;

                        default:
                            System.out.println("Ingreso invalido.");
                    }

                    break;
                case "4":
                    autor.buscarNombreAutor();

                    break;
                case "5":
                    System.out.println("Ingrese el ISBN del libro a buscar");
                    Long isbn = sc.nextLong();

                    List<Libro> a = libro.buscarISBN(isbn);
                    for (Libro libro1 : a) {
                        System.out.println(libro1.toString());
                    }

                    break;
                case "6":
                    System.out.println("Ingrese el nombre del autor del libro a buscar ");
                    String nombre = sc.next();

                    List<Libro> lib = libro.buscarPorNombreAutor(nombre);
                    for (Libro libro1 : lib) {
                        System.out.println(libro1.toString());
                    }

                    break;
                case "7":
                    System.out.println("Ingrese la editorial del libro a buscar");
                    String nombr = sc.next();

                    List<Libro> libr = libro.buscarPorNombreEditorial(nombr);
                    for (Libro libro1 : libr) {
                        System.out.println(libro1.toString());
                    }

                    break;
                case "71":

                    System.out.println("Ingrese el titulo del libro a buscar ");

                    String titulo = sc.next();

                    List<Libro> l = libro.buscarPorTitulo(titulo);

                    for (Libro li : l) {
                        System.out.println(li);

                    }

                    break;
                case "8":
                    System.out.println("Finalizando . . . ");
                    flag = false;
                    break;
                case "9":
                    System.out.println("La lista de autores es:");
                    for (Autor listarAutore : autor.listarAutores()) {
                        System.out.println("autor: " + listarAutore.getNombre() + " id: " + listarAutore.getId());
                    }

                    break;
                case "10":
                    System.out.println("La lista de editoriales");
                    try {
                        for (Editorial editoriales : edit.listarEditores()) {
                            System.out.println(editoriales);
                        }
                    } catch (Exception e) {
                        System.out.println("error en el main");
                    }
                    break;
                case "11":
                    System.out.println("La lista de libros es:");
                    for (Libro lis : libro.listarLibro()) {
                        if (lis.getAlta()) {
                            System.out.println(lis.toString());
                        }

                    }

                    break;
                case "12":

                    System.out.println("Ingrese el dni del cliente");
                    Long dni = sc.nextLong();
                    System.out.println("Ingrese el nombre del cliente");
                    String nomb = sc.next();
                    System.out.println("Ingrese el apellido del cliente");
                    String ape = sc.next();
                    System.out.println("Ingrese el telefono del cliente");
                    String tel = sc.next();
                    Cliente cliente = new Cliente(dni, nomb, ape, tel);

                    cli.alta(cliente);

                    break;
                case "13":
                    System.out.println("La lista de clientes es:");

                    List<Cliente> c = cli.buscarCliente();
                    for (Cliente cl : c) {
                        System.out.println("id: " + cl.getId() + " Dni:" + cl.getDni() + " nombre: " + cl.getNombre() + " apellido: " + cl.getApellido());
                    }
                    System.out.println("ingresar el id del cliente ");
                    Integer id = sc.nextInt();
                    List<Cliente> cc = cli.buscarClienteID(id);
                    System.out.println("La lista de libros disponibles es:");
                    List<Libro> libroo = libro.listarLibroDisponible();

                    for (Libro libro1 : libroo) {
                        System.out.println(libro1.toString());
                    }

                    System.out.println("Ingrese el ISBN del libro");
                    Long isb = sc.nextLong();
                    List<Libro> li = libro.buscarISBN(isb);
                    System.out.println("Ingresar fecha del prestamo en formato \"yyyy/MM/dd\"");
                    String presta = sc.next();

                    
                    if(pres.verificarFecha(presta)){
                    
                       
                    java.sql.Date fecha = new Date(java.sql.Date.parse(presta));

                    Prestamo prestamo = new Prestamo(li.get(0), fecha, null, cc.get(0));
                    pres.alta(prestamo);
                    libro.prestarLibro(li.get(0));

                    System.out.println("Prestamo exitoso");
                    
                    
                    }else{
                    
                        System.out.println("fecha invalida ");
                    
                    }
                    
                    
                 

                    break;
                case "14":
                    System.out.println("Los  prestamos sin fecha de devolucion son: ");

                    List<Prestamo> lista = pres.listarPrestamoSinDevol();
                    for (Prestamo prestamo1 : lista) {
                        System.out.println(prestamo1);
                    }

                    System.out.println("ingrese el id del prestamo ");
                    Integer idprestamo = sc.nextInt();

                    List<Prestamo> pr = pres.listarPrestamo(idprestamo);
                    System.out.println(pr.get(0));

                    System.out.println("Ingrese la fecha de devolucion formato yyyy/mm/dd");
                    String fec = sc.next();

                    java.sql.Date fechadev = new Date(java.sql.Date.parse(fec));

                    List<Libro> actual = libro.buscarISBN(pr.get(0).getLibro().getIsbn());

                    libro.DevolverLibro(actual.get(0));
                    pr.get(0).setFechaDevolucion(fechadev);
                    pres.modificar(pr.get(0));
                    System.out.println("Devolucion exitosa");
                    break;
                case "15":

                    System.out.println("Los clientes son:");
                    
                    
                    List<Cliente> list= cli.buscarCliente();
                    
                    for (Cliente cl : list) {
                        
                        System.out.println(cl.toString());
                        
                    }
                    System.out.println("Ingrese el id del cliente");
                    Integer idCliente= sc.nextInt();
                    
                    if (cli.buscarClienteID(idCliente).isEmpty()) {
                        
                        System.out.println("No se encontró al usuario");
                         
                    }

                    for (Prestamo prestamo1 : pres.listarPrestamo(idCliente)) {
                        System.out.println(prestamo1);
                    }

                    break;

                default:
                    System.out.println("La opcion ingresada es invalida");
            }

        } while (flag);

    }

}
