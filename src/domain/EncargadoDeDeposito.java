package domain;

public class EncargadoDeDeposito extends Usuario{
    public EncargadoDeDeposito() {
    }

    public EncargadoDeDeposito(Integer id, String nombreDeUsuario, String password, Integer nivelPermisos) {
        super(id, nombreDeUsuario, password, nivelPermisos);
    }
}
