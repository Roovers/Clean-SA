package domain;

import java.util.Date;
import java.util.List;

public class Ticket {

    private Integer id;
    private List<ItemTicket> listaProductos;
    private Date fecha;
    private int total;

    public Ticket() {
    }

    public Ticket(Integer id, List<ItemTicket> listaProductos, Date fecha) {
        this.id = id;
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


    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void calcularTotal(){
        for ( ItemTicket i : listaProductos) this.total = this.total + (i.getProducto().getPrecio() * i.getCantidad());
    }

    public void agregarProductoAlTicket(ItemTicket i){
        this.listaProductos.add(i);
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
