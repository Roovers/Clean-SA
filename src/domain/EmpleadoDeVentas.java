package domain;

public class EmpleadoDeVentas extends Usuario {
    public EmpleadoDeVentas() {
    }

    public EmpleadoDeVentas(Integer id, String nombreDeUsuario, String password, Integer nivelPermisos) {
        super(id, nombreDeUsuario, password, nivelPermisos);
    }
}
