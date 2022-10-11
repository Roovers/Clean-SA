package domain;

public class Administrador extends Usuario {

    public Administrador() {
    }

    public Administrador(Integer id, String nombreDeUsuario, String password, Integer nivelPermisos) {
        super(id, nombreDeUsuario, password, nivelPermisos);
    }
}
