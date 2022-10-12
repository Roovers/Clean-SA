package IU;

import domain.*;
import negocios.ListaDeProductos;
import negocios.ListaDeUsuarios;

import javax.swing.*;

public class interfaz {

    static ListaDeProductos inventario = new ListaDeProductos();
    static ListaDeUsuarios usuarios = new ListaDeUsuarios();

    public void loginMenu () {
        Administrador admin1 = new Administrador(1,"admin","admin",0);
        EncargadoDeVentas encargado1 = new EncargadoDeVentas(2,"encargadovent","123",1);
        EncargadoDeDeposito encargado2 = new EncargadoDeDeposito(3,"encargadodep","123",2);
        EmpleadoDeVentas empleado1 = new EmpleadoDeVentas(4,"empleadovent","123",3);
        EmpleadoDeDeposito empleado2 = new EmpleadoDeDeposito(5,"empleadodep","123",4);
        usuarios.addUser(admin1);
        usuarios.addUser(encargado1);
        usuarios.addUser(encargado2);
        usuarios.addUser(empleado1);
        usuarios.addUser(empleado2);
        switch (usuarios.login()){
            case 0 :
                imprimirMenuAdmin();
                break;
            case 1 :
                imprimirMenuEncargadoVentas();
                break;
            case 2 :
                JOptionPane.showMessageDialog(null,"ENCARGADO DE DEPOSITO");
                break;
            case 3 :
                JOptionPane.showMessageDialog(null,"EMPLEADO DE VENTAS");
                break;
            case 4 :
                JOptionPane.showMessageDialog(null,"EMPLEADO DE VENTAS");
                break;
            case 5 : JOptionPane.showMessageDialog(null,"Tu Usuario no esta dado de alta!");
                break;
        }
    }

    public void imprimirMenuAdmin() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                "\n  ➕  1 - Ingresar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  \n  ❌\u200B  3 - Eliminar Producto \n \uD83D\uDD1A   4 - Editar un producto", "\uD83D\uDD11\u200B Usuario : ADMINISTRADOR ", 1));


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
            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
    }

    public void imprimirMenuEncargadoVentas() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                                "\n  ➕  1 - Buscar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  \n  ❌\u200B" +
                "  3 - Realizar venta \n \uD83D\uDD1A   4 - Ver Stock \n 5 - Generar Ticket \n 6 -Consultar Ventas por Fecha \n 7 - Buscar producto PAR \n 8 - Editar producto PAR ",  "\uD83D\uDD11\u200B Usuario : Encargado de Ventas ", 1));

        switch (opcion) {
            case 1:
                buscarProducto();
                break;
            case 2:
                listarProductos();
                break;
            case 3:
                /*venta();*/
                break;
            case 4:
                /* verStockProducto(); */
                break;
            case 5:
                /*generarTicket();*/
                break;
            case 6:
                /*consultarVentasPorFecha();*/
                break;
            case 7:
                /*BuscaPAR();*/
                break;
            case 8:
                /*editarPAR();*/
                break;

            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
    }
    public void imprimirMenuEmpleadoVentas() {
        int opcion = Integer.parseInt(JOptionPane.showInputDialog(null, " \u200B⛔\u200B A T E N C I O N \u200B⛔\u200B \nPara acceder a la opcion ingrese el numero que corresponda " +
                "\n  ➕  1 - Buscar Producto \n  \uD83D\uDD0E  2 - Consultar Inventario  \n  ❌\u200B" +
                "  3 - Realizar venta \n \uD83D\uDD1A   4 - Ver Stock \n 5 - Generar Ticket",  "\uD83D\uDD11\u200B Usuario : Encargado de Ventas ", 1));

        switch (opcion) {
            case 1:
                buscarProducto();
                break;
            case 2:
                listarProductos();
                break;
            case 3:
                /*venta();*/
                break;
            case 4:
               /* verStockProducto(); */
                break;
            case 5:
                /*generarTicket();*/
                break;
            default:
                System.out.println("Se eligio una opcion incorrecta volver a intentar");
                break;
        }
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


        Producto p = new Producto(idProducto, nombre, precio, detalle, cantidad, nivelDeToxicidad);

        if (inventario.addProducto(p)) {
            JOptionPane.showMessageDialog(null, " El producto fue agregado correctamente al inventario ! ", "Producto Agregado", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/ok.png")));

        } else {
            JOptionPane.showMessageDialog(null, " El producto no se pudo agregar correctamente , revise los datos ingresados ! ", "Producto NO Agregado", JOptionPane.PLAIN_MESSAGE,
                    new ImageIcon(interfaz.class.getResource("/img/fail.png")));

        }
        imprimirMenuAdmin();

    }

    public void editarUnProducto(){
        int serialProductoAEditar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el codigo del producto a editar"));
        inventario.editarProducto(serialProductoAEditar);

        imprimirMenuAdmin();

    }

    public void listarProductos() {
        inventario.listarProductos();
        imprimirMenuAdmin();
    }

    private void buscarProducto () {
         int p = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el código del producto"));
         inventario.buscarProducto(p);

    }

    public interfaz() {
        loginMenu();
      //  imprimirMenu();
    }
}

