package test;

import IU.interfaz;
import domain.Conexion;

public class main {
    public static void main(String[] args) {
        Conexion c = new Conexion();
        c.conectar();
        interfaz pantallaPrincipal = new interfaz();
    }
}
