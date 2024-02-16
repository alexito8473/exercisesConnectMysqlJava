package ejercicio17;

import ejercicio2.Constantes;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Main {
    private void ejercicio17(){
        Connection connection;
        String codOe="DAM";
        String codCurso="1A";
        String nombre="";
        String sql = "{? = call codCurNombreTutor(?,?)}";
        CallableStatement sentencia;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareCall(sql);
            sentencia.registerOutParameter(1, Types.VARCHAR);
            sentencia.setString(2, codOe);
            sentencia.setString(3, codCurso);
            sentencia.execute();
            System.out.printf("La profesora o profesor es %s", sentencia.getString(1));
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main( String[] args ) {
        new Main().ejercicio17();
    }
}
