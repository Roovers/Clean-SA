package domain;

public class ItemTicket {

    private Producto producto;

    private int cantidad;

    public ItemTicket() {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public ItemTicket(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }


    @Override
    public String toString() {
        return " P R O D U C T O : " + producto.getNombreDeProducto() +
                " | C A N T I D A D : " + cantidad + "\n";
    }
}
