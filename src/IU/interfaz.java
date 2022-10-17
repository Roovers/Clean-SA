package IU;

import domain.*;
import negocios.ListaDeProductos;
import negocios.ListaDeUsuarios;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class interfaz {

    // Lista de Productos que componen el inventario.
    static ListaDeProductos inventario = new ListaDeProductos();
    // Lista de Usuarios.
    static ListaDeUsuarios usuarios = new ListaDeUsuarios();
    // Lista de Ventas Registradas.
    private List<Ticket> registroVentas = new ArrayList<>();

    // Selector de menú que corresponde a cada usuario.
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
                imprimirMenuEmpleadoDeVentas();
                break;
            case 4:
                JOptionPane.showMessageDialog(null, "EMPLEADO DE VENTAS");
                break;
        }
    }

    // Método que imprime menú para administrador.
    public void imprimirMenuAdmin() { // MENU ADMIN GENERAL

        int opcion = Integer.parseInt((String) JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B " +
                        "\nPara acceder a la opcion ingrese el numero que corresponda " +
                        "\n  \uD83D\uDD27\u200B  1 - Administrar Productos \n  \uD83D\uDD27\u200B  2 - Administrar Usuarios \n  ❌\u200B  3 - Cerrar Sesion ", "Usuario : ADMINISTRADOR" , JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/admin.png")), null, null));

        switch (opcion) {
            case 1:     // MENU ADMIN PRODUCTOS
                opcion = Integer.parseInt((String)(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B " +
                                "\nPara acceder a la opcion ingrese el numero que corresponda " +
                                "\n  ➕  1 - Agregar un  Producto \n  \uD83D\uDD0E  2 - Consultar el Inventario  " +
                                "\n  ❌\u200B  3 - Eliminar un Producto \n \uD83D\uDD04\u200B   4 - Editar un producto   " +
                                "\n  \uD83D\uDD0E  5 - Buscar un Producto" + "\n  \u200B↩️\u200B  6 - Volver al Menu Principal" , "\uD83D\uDD11\u200B Usuario : ADMINISTRADOR ", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/admin.png")), null, null)));

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
                        buscarProducto();
                        break;
                    case 6:
                        imprimirMenuAdmin();
                        break;
                    default:
                        JOptionPane.showMessageDialog(null,"Se eligio una opcion incorrecta volver a intentar");
                        break;
                }
                imprimirMenuAdmin();

            case 2:     // MENU ADMIN USUARIOS
                opcion = Integer.parseInt((String) (JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B " +
                        "\nPara acceder a la opcion ingrese el numero que corresponda " +
                        "\n  ➕  1 - Dar de Alta un  Usuario \n  \uD83D\uDD0E  2 - Lista de Usuarios  " +
                        "\n  ❌\u200B  3 - Dar de Baja un Usuario \n \uD83D\uDD04\u200B   4 - Editar un Usuario   " +
                        "\n  \uD83D\uDD0E  5 - Buscar un Usuario" + "\n  \u200B↩️\u200B  6 - Volver al Menu Principal" , "\uD83D\uDD11\u200B Usuario : ADMINISTRADOR ", JOptionPane.DEFAULT_OPTION,
                        new ImageIcon(interfaz.class.getResource("/img/admin.png")), null, null)));

                switch (opcion) {
                    case 1:
                        agregarUsuario();
                        break;
                    case 2:
                        listarUsuarios();
                        break;
                    case 3:
                        borrarUsuario();
                        break;
                    case 4:
                        editarUnUsuario();
                        break;
                    case 5:
                        buscarUsuario();
                        break;
                    case 6:
                        imprimirMenuAdmin();
                        break;

                    default:
                        JOptionPane.showMessageDialog(null,"Se eligio una opcion incorrecta volver a intentar");
                        break;
                }
                imprimirMenuAdmin();

            case 3:     // CERRAR SESION
                loginMenu();
                break;

                default:
                    JOptionPane.showMessageDialog(null,"Se eligio una opcion incorrecta volver a intentar");
                break;
        }
        imprimirMenuAdmin();
    }

    // Método que imprime menú para Encargado de Depósito.
    public void imprimirMenuEncargadoDeDeposito() {

        int opcion = Integer.parseInt((String)(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B " +
                "\nPara acceder a la opcion ingrese el numero que corresponda " + "\n  ➕  1 - Ingresar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  " +
                "\n  ❌\u200B  3 - Eliminar Producto \n  \uD83D\uDD04\u200B  4 - Editar un producto \n  \uD83D\uDD0E  5 - Buscar un producto \n  \uD83D\uDD0E  6 - Consultar SOLO productos P.A.R \n  ❌\u200B  7 - Cerrar Sesion " , "\uD83D\uDD11\u200B Usuario : ENCARGADO DE DEPÓSITO ", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/encd.png")), null, null)));

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
            case 7:
                loginMenu();
                break;

            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
    }

    // Método que imprime menú para Encargado de Ventas.
    public void imprimirMenuEncargadoDeVentas() {
        int opcion = Integer.parseInt((String)(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                "\n  \uD83D\uDD0E  1 - Buscar un Producto \n  \uD83D\uDD0E  2 - Consultar Stock  \n  \u200B\uD83D\uDCB2  3 - Vender un producto \n  \uD83D\uDD0E  4 - Consultar Registro de Ventas  \n  ❌\u200B  5 - Cerrar Sesion  ",  "\uD83D\uDD11\u200B Usuario : ENCARGADO VENTAS ", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/encv.png")), null, null)));
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
                inventario.generarVenta();
                imprimirMenuEncargadoDeVentas();
            case 4:
                inventario.verRegistroDeVentas();
                imprimirMenuEncargadoDeVentas();
                break;
            case 5:
                loginMenu();
                break;
        }
    }

    // Método que imprime menú para Empleado de Ventas.
    public void imprimirMenuEmpleadoDeVentas(){
        int opcion = Integer.parseInt((String)(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                        "\n  \uD83D\uDD0E  1 - Buscar un Producto \n  \uD83D\uDD0E  2 - Consultar Stock  \n  \u200B\uD83D\uDCB2  3 - Vender un producto \n  ❌\u200B  4 - Cerrar Sesion  ",  "\uD83D\uDD11\u200B Usuario : EMPLEADO DE VENTAS ", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/emplv.png")), null, null)));
        switch (opcion) {
            case 1:
                buscarProductoComun();
                imprimirMenuEmpleadoDeVentas();
                break;
            case 2:
                consultarStockProductoComun();
                imprimirMenuEmpleadoDeVentas();
                break;
            case 3:
                venderProductoComun();
                imprimirMenuEmpleadoDeVentas();
            case 4:
                loginMenu();
                break;
        }
    }

    // Método que toma datos para agregar un producto del inventario.
    private void ingresarProducto() {
        int resp;
        do {
            int idProducto = Integer
                    .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el Codigo del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));

            String nombre = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

            int precio = Integer
                    .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el precio del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/precio.png")), null, null));

            String detalle = (String) JOptionPane.showInputDialog(null, "Ingrese el detalle del prducto :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

            int cantidad = Integer
                    .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el cantidad de unidades :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/cant.png")), null, null));

            String nivelDeToxicidad = (String) JOptionPane.showInputDialog(null, "Ingrese Nivel de toxicidad del prducto  ALTO o BAJO :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/toxi.png")), null, null);


            Producto producto = new Producto(idProducto, nombre, precio, detalle, cantidad, nivelDeToxicidad.toLowerCase());

            if (inventario.addProducto(producto)) {
                JOptionPane.showMessageDialog(null, " El Producto fue Agregado Correctamente al Inventario ! ", "PRODUCTO AGREGADO", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/ok.png")));

            } else {
                JOptionPane.showMessageDialog(null, " El Producto no se pudo Agregar Correctamente , revise los datos ingresados ! ", "ERROR", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/error.png")));

            }
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea agregar otro Producto? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/mas.png")), null, null));
        } while (resp == 1);

    }

    // Método que agrega un usuario mediante ingreso de datos.
    public void agregarUsuario () {

        int resp;
        do {
            int idUsuario = Integer
                    .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el id del Usuario :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));

            String nombre = (String) JOptionPane.showInputDialog(null, "Ingrese el nombre de Usuario:", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

            String password = (String) JOptionPane.showInputDialog(null, "Ingrese la Clave de Usuario :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/ficha.png")), null, null);

            int nivelDePermisos = Integer
                    .parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el nivel de permisos :", "Ingreso De Datos", JOptionPane.DEFAULT_OPTION,
                            new ImageIcon(interfaz.class.getResource("/img/all.png")), null, null));

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
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea agregar otro Usuario? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/user+.png")), null, null));
        } while (resp == 1);

    }

    // Método que toma datos para borrar un producto del inventario.
    private void borrarProducto() {
        int codigoProducto = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el Codigo del prducto a borrar :", "BORRAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
        if(inventario.buscarProducto(codigoProducto) != null) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas borrar este producto?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if(confirm == 0) {
                inventario.borrarProducto(codigoProducto);
                JOptionPane.showMessageDialog(null, " El producto se elimino correctamente ! ", "Producto Borrado", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/delete.png")));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, " ERROR - El código ingresado es erroneo ", "ERROR", JOptionPane.PLAIN_MESSAGE,
                new ImageIcon(interfaz.class.getResource("/img/error.png")));
        imprimirMenuAdmin();
    }

    private void borrarUsuario() {

        int idUsuario = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del usuario a borrar :", "BORRAR Usuario", JOptionPane.DEFAULT_OPTION,
                new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
        if(usuarios.buscarUsuario(idUsuario) != null) {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Estas seguro que deseas borrar este Usuario?", "Confirmacion", JOptionPane.YES_NO_OPTION);
            if(confirm == 0) {
                usuarios.borrarUsuario(idUsuario);
                JOptionPane.showMessageDialog(null, " El Usuario se elimino correctamente ! ", "Usuario Borrado", JOptionPane.PLAIN_MESSAGE,
                        new ImageIcon(interfaz.class.getResource("/img/delete2.jpg")));
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "ERROR - El Usuario ingresado es erroneo");
        imprimirMenuAdmin();
    }

    // Método que toma datos para modificar un producto del inventario.
    public void editarUnProducto() {
        int resp ;
        do {
            int serialProductoAEditar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea editar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            inventario.editarProducto(serialProductoAEditar);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea editar otro producto? \n Ingrese 1 - SI \n Ingrese 2 - NO", "CONFIRMACION", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/mas.png")), null, null));
        } while (resp == 1);

    }

    public void editarUnUsuario() {
        int resp ;
        do {
            int idUsuarioAEditar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del Usuario que desea editar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            usuarios.editarUsuario(idUsuarioAEditar);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea editar otro Usuario? \n Ingrese 1 - SI \n Ingrese 2 - NO", "CONFIRMACION", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/user+.png")), null, null));
        } while (resp == 1);
        imprimirMenuAdmin();
    }

    // Método que busca productos mediante ingreso de datos.
    public void buscarProducto() {
        int resp;
        do {
            int codigoProductoABuscar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea buscar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            Producto producto = inventario.buscarProducto(codigoProductoABuscar);
            if (producto != null) {
                JOptionPane.showMessageDialog(null, producto);
            } else {
                JOptionPane.showMessageDialog(null, "El producto que deseas buscar no existe en el inventario");
            }
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea buscar otro Producto? \n 1 - SI \n 2 - NO", "BUSCAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/busc.png")), null, null));
        } while (resp == 1);
    }

    public void buscarUsuario() {
        int resp;
        do {
            int idUsuarioABuscar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del usuario que desea buscar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            Usuario usuario = usuarios.buscarUsuario(idUsuarioABuscar);
            if (usuario != null) {
                JOptionPane.showMessageDialog(null, usuario);
            } else {
                JOptionPane.showMessageDialog(null, "El Usuario que deseas buscar no esta dado de alta en el sistema");
            }
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea Buscar otro Usuario? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/busc.png")), null, null));
        } while (resp == 1);
    }

    // Método que muestra una lista de los productos del inventario.
    public void listarProductos() {
        inventario.listarProductos();
        imprimirMenuAdmin();
    }

    public void listarUsuarios() {
        usuarios.listarUsuarios();
        imprimirMenuAdmin();
    }

    // Método que se ejecuta en el main.
    public interfaz() {
        sobrecargarInventario();
        sobrecargarUsuarios();
        loginMenu();

    }

    // Método que hardcodea el ingreso de productos al inventario.
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

    // Método que hardcodea el alta de usuarios al sistema.
    public void sobrecargarUsuarios() {
        Administrador admin1 = new Administrador(1, "admin", "admin", 0);
        Administrador admin2 = new Administrador(6, "a", "a", 0);
        EncargadoDeVentas encargado1 = new EncargadoDeVentas(2, "env", "123", 1);
        EncargadoDeDeposito encargado2 = new EncargadoDeDeposito(3, "end", "123", 2);
        EmpleadoDeVentas empleado1 = new EmpleadoDeVentas(4, "emv", "123", 3);
        EmpleadoDeDeposito empleado2 = new EmpleadoDeDeposito(5, "emd", "123", 4);
        usuarios.addUser(admin1);
        usuarios.addUser(admin2);
        usuarios.addUser(encargado1);
        usuarios.addUser(encargado2);
        usuarios.addUser(empleado1);
        usuarios.addUser(empleado2);
    }

    // Método que muestra una lista de productos PAR.
    public void listarProductosPar() {
        inventario.listarProductosPar();
    }

    // Método que vende un prodcuto mediante ingreso de datos.
    public void venderProducto() {
            inventario.generarVenta();
    }
    // Método que consulta stock de un prodcuto mediante ingreso de datos.
    public void consultarStock() {
        int resp;
        do {
            int idProductoABuscar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea buscar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            inventario.consultarStock(idProductoABuscar);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea Consultar Stock de otro Producto? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/busc.png")), null, null));
        } while (resp == 1);
    }

    // Método que vende un prodcuto NO PAR mediante ingreso de datos.
    public void venderProductoComun(){
        int resp;
        do {
            int idProductoAVender = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto que desea vender"));
            inventario.venderUnProductoComun(idProductoAVender);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea Vender otro Producto? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/precio.png")), null, null));
        } while (resp == 1);
    }

    // Método que consulta stock de productos NO PAR.
    public void consultarStockProductoComun(){
        int resp;
        do {
            int idProductoAConsultar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea buscar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            inventario.consultarStockProductoComun(idProductoAConsultar);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea Consultar Stock de otro Producto? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/busc.png")), null, null));
        } while (resp == 1);
    }

    // Método que busca productos NO PAR.
    public void buscarProductoComun(){
        int resp;
        do {
            int idProductoABuscar = Integer.parseInt((String) JOptionPane.showInputDialog(null, "Ingrese el ID del producto que desea buscar", "INGRESO DE DATOS", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/cod.png")), null, null));
            inventario.buscarProductoComun(idProductoABuscar);
            resp =  Integer.parseInt((String) JOptionPane.showInputDialog(null, "Desea Consultar otro Producto? \n 1 - SI \n 2 - NO", "AGREGAR PRODUCTO", JOptionPane.DEFAULT_OPTION,
                    new ImageIcon(interfaz.class.getResource("/img/busc.png")), null, null));
        } while (resp == 1);

    }

}



