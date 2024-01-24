package ejercicio3;

import ejercicio2.Constantes;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Main {
    private void ejercicio3() {
        System.out.println("Insertar la siguiente oferta educativa:\n" +
                "cod_OE: FPB\n" +
                "nombre: FP Básica Informática y comunicaciones\n" +
                "descripción: La formación profesional básica de informática y comunicaciones tiene una\n" +
                "duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas de\n" +
                "Formación en centros de trabajo (FCT) en empresas del Sector");
        String sql = "INSERT INTO ofertaeducativa VALUES (?,?,?,?,?)";
        PreparedStatement sentencia;
        Connection connection;
        int fila;
        try {
            connection = connectServer(Constantes.CLASS_NAME, Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, "FPB");
            sentencia.setString(2, "FP Básica Informática y comunicaciones");
            sentencia.setString(3, "La formación profesional básica de informática y comunicaciones tiene una duración de 2000 horas repartidas entre dos cursos académicos incluyendo 240 horas de Formación en centros de trabajo (FCT) en empresas del Sector");
            sentencia.setString(4, "FP");
            sentencia.setTimestamp(5, Timestamp.valueOf(LocalDateTime.now()) );
            fila = sentencia.executeUpdate();
            System.out.println("Han sido afectada un total de "+ fila +" fila/s");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Connection connectServer(String className, String url, String usuario, String contraseña) throws ClassNotFoundException, SQLException {
        Class.forName(Constantes.CLASS_NAME);
        return DriverManager.getConnection(url, usuario, contraseña);
    }

    public static void main(String[] args) {
        new Main().ejercicio3();
    }
}
