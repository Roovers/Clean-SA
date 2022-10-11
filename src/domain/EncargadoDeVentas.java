package domain;

public class EncargadoDeVentas extends Usuario{
    public EncargadoDeVentas() {
    }

    public EncargadoDeVentas(Integer id, String nombreDeUsuario, String password, Integer nivelPermisos) {
        super(id, nombreDeUsuario, password, nivelPermisos);
    }

}
