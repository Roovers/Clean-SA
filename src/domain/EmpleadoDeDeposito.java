package domain;

public class EmpleadoDeDeposito extends Usuario{
    public EmpleadoDeDeposito() {
    }

    public EmpleadoDeDeposito(Integer id, String nombreDeUsuario, String password, Integer nivelPermisos) {
        super(id, nombreDeUsuario, password, nivelPermisos);
    }
}
