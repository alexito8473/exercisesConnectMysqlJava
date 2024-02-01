package ejercicio17;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    private void ejercicio17(){
        Connection connection;
        String codOe="DAM";
        String codCurso="1A";
        String sql = " select codCurNombreTutor(?,?);";
        PreparedStatement sentencia;
        ResultSet resultado;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, codOe);
            sentencia.setString(2, codCurso);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                System.out.printf("La profesora o profesor es %s", resultado.getString(1));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main( String[] args ) {
        new Main().ejercicio17();
    }
}
