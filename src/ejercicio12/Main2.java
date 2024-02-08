package ejercicio12;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main2 {
    private void ejercicio12_2() {
        Connection connection;
        String sqlSelectReparto = "SELECT codProf,codAsig FROM reparto ";
        PreparedStatement mostrarSelectProfesor;
        ResultSet resultadoCodProf;
        String igualdad = "";
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelectProfesor = connection.prepareStatement(sqlSelectReparto);
            resultadoCodProf = mostrarSelectProfesor.executeQuery();
            while (resultadoCodProf.next()) {
                if ( !igualdad.equals(resultadoCodProf.getString(1)) ) {
                    System.out.printf("\nEl codigo profesor %s, sus asignaturas son  ->", resultadoCodProf.getString(1));
                    igualdad = resultadoCodProf.getString(1);
                }
                System.out.printf("Codigo asignatura -> %-6s | ", resultadoCodProf.getString(2));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main( String[] args ) {
        new Main2().ejercicio12_2();
    }
}
