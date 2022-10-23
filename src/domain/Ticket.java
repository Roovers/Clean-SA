package domain;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Ticket {

    private Integer id;
    private List<ItemTicket> listaProductos;
    private LocalDate fecha;
    private int total;

    private int idUsuario;

    public Ticket() {
    }
    public Ticket(Integer id, List<ItemTicket> listaProductos) {
        this.id = id;
        this.listaProductos = listaProductos;
    }
    public Ticket(Integer id, List<ItemTicket> listaProductos, LocalDate fecha) {
        this.id = id;
        this.listaProductos = listaProductos;
        this.fecha = fecha;
        this.total = 0;
    }
    public Ticket( List<ItemTicket> listaProductos, LocalDate fecha) {
        this.listaProductos = listaProductos;
        this.fecha = fecha;
        this.total = 0;
    }
    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ItemTicket> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ItemTicket> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = LocalDate.now();

    }

    public void calcularTotal(){
        for ( ItemTicket i : listaProductos) this.total = this.total + (i.getProducto().getPrecio() * i.getCantidad());
    }

    public void agregarProductoAlTicket(ItemTicket i){
        this.listaProductos.add(i);
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return " T I C K E T  " +
                "\n-------------------" +
                "\n  ID : " + id +
                "\n FECHA :  " + fecha +
                "\n PRODUCTOS : " + listaProductos +
                "\n TOTAL : " + " $ " + total ;
    }
}
