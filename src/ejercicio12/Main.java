package ejercicio12;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio12() {
        Connection connection;
        String sqlSelectCodProf = "SELECT codProf, nombre, apellidos from profesor";
        String sqlSelectAsigProf = "SELECT codAsig FROM reparto where codProf=?";
        PreparedStatement mostrarSelectProfesor;
        PreparedStatement mostrarSelectAsigProf;
        ResultSet resultadoCodProf;
        ResultSet resultadoAsigProf;
        boolean noDatos;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelectProfesor = connection.prepareStatement(sqlSelectCodProf);
            resultadoCodProf = mostrarSelectProfesor.executeQuery();
            mostrarSelectAsigProf = connection.prepareStatement(sqlSelectAsigProf);
            while (resultadoCodProf.next()) {
                noDatos = true;
                mostrarSelectAsigProf.setString(1, resultadoCodProf.getString(1));
                resultadoAsigProf = mostrarSelectAsigProf.executeQuery();
                System.out.printf("El codigo profesor %s, es %-10s %-17s, sus asignaturas son  -> ", resultadoCodProf.getString(1), resultadoCodProf.getString(2), resultadoCodProf.getString(3));
                while (resultadoAsigProf.next()) {
                    System.out.printf("Codigo asignatura -> %-6s | ", resultadoAsigProf.getString(1));
                    noDatos = false;
                }
                if ( noDatos ) {
                    System.out.print("No tiene asignaturas");
                }
                System.out.println();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main( String[] args ) {
        new Main().ejercicio12();
    }
}
