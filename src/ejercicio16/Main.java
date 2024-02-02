package ejercicio16;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    private void ejercicio16(){
        Connection connection;
        String sql = "Select nombre, horasSemanales, count(distinct codCurso , codOe ),count(distinct codOe ) from asignatura a join reparto r on r.codAsig=a.codAsig where horasSemanales>=3 group by nombre, horasSemanales";
        PreparedStatement sentencia;
        ResultSet resultado;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.printf("El nombre es %-65s, las horas semanales son %s, el número de cursos son %s y el número de ofertas educativas %s \n", resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main( String[] args ) {
        new Main().ejercicio16();
    }
}
