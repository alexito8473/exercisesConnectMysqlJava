package ejercicio18;

import ejercicio2.Constantes;

import java.sql.*;

public class Main {
    private void ejercicio18() {
        Connection connection;
        String codOe = "DAM";
        String codCurso = "2A";
        String codAsig = "AD";
        String sql = " call codCurso_codAsig_toNameProf_hourWeek(?, ?, ?,?, ?)";
        CallableStatement sentencia;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareCall(sql);
            sentencia.setString(1, codOe);
            sentencia.setString(2, codCurso);
            sentencia.setString(3, codAsig);
            sentencia.registerOutParameter(4, Types.INTEGER);
            sentencia.registerOutParameter(5, Types.VARCHAR);
            sentencia.executeUpdate();
        System.out.printf("Las horas semanales son %d y las parte el profesor %s",sentencia.getInt(4),sentencia.getString(5));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main( String[] args ) {
        new Main().ejercicio18();
    }
}
