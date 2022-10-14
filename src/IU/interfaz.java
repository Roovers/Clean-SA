package IU;

import domain.*;
import negocios.ListaDeProductos;
import negocios.ListaDeUsuarios;

import javax.swing.*;

public class interfaz {

    static ListaDeProductos inventario = new ListaDeProductos();
    static ListaDeUsuarios usuarios = new ListaDeUsuarios();

    public void loginMenu() {

        switch (usuarios.login()) {
            case 0:
                imprimirMenuAdmin();
                break;
            case 1:
                imprimirMenuEncargadoDeVentas();
                break;
            case 2:
                imprimirMenuEncargadoDeDeposito();

                break;
            case 3:
                JOptionPane.showMessageDialog(null, "EMPLEADO DE VENTAS");
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "EMPLEADO DE VENTAS");
                break;
            case 5:
                JOptionPane.showMessageDialog(null, "Tu Usuario no esta dado de alta!");
                break;
        }
    }

    public void imprimirMenuAdmin() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                "\n  ➕  1 - Ingresar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  \n  ❌\u200B  3 - Eliminar Producto \n \uD83D\uDD1A   4 - Editar un producto   \n   5 - agregar usuario", "\uD83D\uDD11\u200B Usuario : ADMINISTRADOR ", 1));


        switch (opcion) {
            case 1:
                ingresarProducto();
                break;
            case 2:
                listarProductos();
                break;
            case 3:
                borrarProducto();
                break;
            case 4:
                editarUnProducto();
                break;

            case 5:
                agregarUsuario();
                break;
            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
        imprimirMenuAdmin();
    }

    private void borrarProducto() {
        // int codigoProducto = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del Producto a borrar :"));
        int codigoProducto = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el Codigo del prducto a borrar :", "BORRAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
        for (Producto p : inventario.getInventario()) {
            if (p.getIdProducto() == codigoProducto) {

                int confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas borrar este producto?", "Confirmacion", JOptionPane.YES_NO_OPTION);

                if (confirm == 0) {
                    inventario.borrarProducto(codigoProducto);
                    JOptionPane.showMessageDialog(null, " El producto se elimino correctamente ! ", "Producto Borrado", JOptionPane.PLAIN_MESSAGE,
                            new ImageIcon(interfaz.class.getResource("/img/delete2.jpg")));
                    break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERROR - El codigo ingresado es erroneo");
                break;
            }
        }
        imprimirMenuAdmin();
    }

    private void ingresarProducto() {
        int idProducto = Integer
                .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el Codigo del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));

        String nombre = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

        int precio = Integer
                .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el precio del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/preci.png")), null, null));

        String detalle = (String) JOptionPane.showInputDialog(null, "Ingrese el detalle del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

        int cantidad = Integer
                .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el cantidad de unidades :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/cant.png")), null, null));

        String nivelDeToxicidad = (String) JOptionPane.showInputDialog(null, "Ingrese Nivel de toxicidad del prducto  ALTO o BAJO :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/toxi.png")), null, null);


        Producto p = new Producto(idProducto, nombre, precio, detalle, cantidad, nivelDeToxicidad.toLowerCase());

        if (inventario.addProducto(p)) {
            JOptionPane.showMessageDialog(null, " El producto fue agregado correctamente al inventario ! ", "Producto Agregado", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/ok.png")));

        } else {
            JOptionPane.showMessageDialog(null, " El producto no se pudo agregar correctamente , revise los datos ingresados ! ", "Producto NO Agregado", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/fail.png")));

        }


    }

    public void editarUnProducto() {
        int serialProductoAEditar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto a editar"));
        inventario.editarProducto(serialProductoAEditar);

        imprimirMenuAdmin();

    }

    public void listarProductos() {
        inventario.listarProductos();
        imprimirMenuAdmin();
    }

    public interfaz() {
        sobrecargarInventario();
        sobrecargarUsuarios();
        loginMenu();
        //  imprimirMenu();
    }

    public void sobrecargarInventario() {
        Producto p1 = new Producto(1, "JABON ALA PLUS", 500, "El mejor JABON de BS AS", 100, "BAJO");
        Producto p2 = new Producto(2, "TRAPO DE PISO FIMAX", 399, "Trapo de 90x90", 100, "BAJO");
        Producto p3 = new Producto(3, "Rejilla La Estrella", 500, "Rejilla economica", 100, "BAJO");
        Producto p4 = new Producto(4, "AROMATIZANTE ECOLOGICO GENIUS", 600, "Sabor a Frutilla", 100, "BAJO");
        Producto p5 = new Producto(5, "Esponja Cuchurrumin", 140, "Color amarillo", 100, "BAJO");
        Producto p6 = new Producto(6, "Naftalina HyperX", 450, "Es Multiuso", 10, "ALTO");
        Producto p7 = new Producto(7, "Detergente Ala", 200, "Limpia todoo", 23, "ALTO");
        Producto p8 = new Producto(8, "Lavandina", 173, "Para tunear la ropa", 65, "ALTO");
        Producto p9 = new Producto(9, "Kerosene", 400, "Elimina los piojos", 8, "ALTO");
        Producto p10 = new Producto(10, "Hidróxido de potasio", 200, "Para limpieza de Pinturas", 10, "ALTO");
        inventario.addProducto(p1);
        inventario.addProducto(p2);
        inventario.addProducto(p3);
        inventario.addProducto(p4);
        inventario.addProducto(p5);
        inventario.addProducto(p6);
        inventario.addProducto(p7);
        inventario.addProducto(p8);
        inventario.addProducto(p9);
        inventario.addProducto(p10);
    }

    public void sobrecargarUsuarios() {
        Administrador admin1 = new Administrador(1, "admin", "admin", 0);
        EncargadoDeVentas encargado1 = new EncargadoDeVentas(2, "encargadovent", "123", 1);
        EncargadoDeDeposito encargado2 = new EncargadoDeDeposito(3, "encargadodep", "123", 2);
        EmpleadoDeVentas empleado1 = new EmpleadoDeVentas(4, "empleadovent", "123", 3);
        EmpleadoDeDeposito empleado2 = new EmpleadoDeDeposito(5, "empleadodep", "123", 4);
        usuarios.addUser(admin1);
        usuarios.addUser(encargado1);
        usuarios.addUser(encargado2);
        usuarios.addUser(empleado1);
        usuarios.addUser(empleado2);
    }

    public void buscarProducto() {
        int codigoProductoABuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto que desea buscar"));
        Producto p = inventario.buscarProducto(codigoProductoABuscar);
        if (p != null) {
            JOptionPane.showMessageDialog(null, p);
        } else {
            JOptionPane.showMessageDialog(null, "El producto que deseas buscar no existe en el inventario");
        }
    }

    public void listarProductosPar() {
        inventario.listarProductosPar();
    }

    public void imprimirMenuEncargadoDeDeposito() {

        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B " +
                "\nPara acceder a la opcion ingrese el numero que corresponda " + "\n  ➕  1 - Ingresar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  " +
                "\n  ❌\u200B  3 - Eliminar Producto \n \uD83D\uDD1A   4 - Editar un producto, \n   5 - Editar un producto, \n   6 - Consultar SOLO productos P.A.R ", "\uD83D\uDD11\u200B Usuario : ENCARGADO DE DEPÓSITO ", 1));

        switch (opcion) {
            case 1:
                ingresarProducto();
                imprimirMenuEncargadoDeDeposito();
                break;
            case 2:
                listarProductos();
                imprimirMenuEncargadoDeDeposito();
                break;
            case 3:
                borrarProducto();
                imprimirMenuEncargadoDeDeposito();
                break;
            case 4:
                editarUnProducto();
                imprimirMenuEncargadoDeDeposito();
                break;
            case 5:
                buscarProducto();
                imprimirMenuEncargadoDeDeposito();
                break;
            case 6:
                listarProductosPar();
                imprimirMenuEncargadoDeDeposito();
                break;

            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
    }

    public void imprimirMenuEncargadoDeVentas() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                "\n  ➕  1 - Buscar un Producto \n  \uD83D\uDD0E  2 - Consultar Stock  \n  ❌\u200B  3 - Vender un producto \n \uD83D\uDD1A", "\uD83D\uDD11\u200B Usuario : ENCARGADO VENTAS ", 1));
        switch (opcion) {
            case 1:
                buscarProducto();
                imprimirMenuEncargadoDeVentas();
                break;
            case 2:
                consultarStock();
                imprimirMenuEncargadoDeVentas();
                break;
            case 3:
                venderProducto();
                imprimirMenuEncargadoDeVentas();
        }
    }

    public void venderProducto() {
        int idProductoAVender = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto que desea vender"));
        inventario.venderUnProducto(idProductoAVender);
    }

    public void consultarStock() {
        int idProductoABuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto que desea saber el stock"));
        inventario.consultarStock(idProductoABuscar);
    }

   public void agregarUsuario () {

       int idUsuario = Integer
               .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el id :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                       new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));

       String nombre = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
               new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

       String password = (String) JOptionPane.showInputDialog(null, "Ingrese el password :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
               new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

       int nivelDePermisos = Integer
               .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el nivel de permisos :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                       new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));

       switch (nivelDePermisos) {
           case 0: Administrador a = new Administrador(idUsuario, nombre, password, nivelDePermisos);
               if (usuarios.addUser(a)) {
                   JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
               } else {
                   JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
               }
               break;
           case 1: EncargadoDeVentas encv = new EncargadoDeVentas(idUsuario, nombre, password, nivelDePermisos);
               if (usuarios.addUser(encv)) {
                   JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
               } else {
                   JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
               }
               break;
           case 2: EncargadoDeDeposito encd = new EncargadoDeDeposito(idUsuario, nombre, password, nivelDePermisos);
               if (usuarios.addUser(encd)) {
                   JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
               } else {
                   JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
               }
               break;
           case 3: EmpleadoDeVentas empv = new EmpleadoDeVentas(idUsuario, nombre, password, nivelDePermisos);
               if (usuarios.addUser(empv)) {
                   JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
               } else {
                   JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
               }
               break;

           case 4: EmpleadoDeDeposito empd = new EmpleadoDeDeposito(idUsuario, nombre, password, nivelDePermisos);
               if (usuarios.addUser(empd)) {
                   JOptionPane.showMessageDialog(null, "USUARIO DADO DE ALTA.");
               } else {
                   JOptionPane.showMessageDialog(null, "ERROR , HAY DATOS INGRESADOS INVALIDOS.");
               }
               break;
           default:
               System.out.println("Se eligio una opcion incorrecta volver a intentar");
               break;
       }
    }
}



