package ejercicio9;

import ejercicio2.Constantes;

import java.sql.*;

public class Main {
    private void ejercicio9() {
        Connection connection;
        String sqlSelectCursToProf = "SELECT * from profesor left join curso on codTutor=codProf";
        Statement mostrarSelect;
        ResultSet resultado;
        int numeroColumnas;
        ResultSetMetaData resultadoTotal;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.createStatement();
            resultado = mostrarSelect.executeQuery(sqlSelectCursToProf);
            resultadoTotal = resultado.getMetaData();
            numeroColumnas = resultadoTotal.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.printf("Columna %d: %n ", i);
                System.out.printf(" Nombre: %s %n  Tipo: %s %n ", resultadoTotal.getColumnName(i), resultadoTotal.getColumnTypeName(i));
                System.out.printf(" Puede ser nula?: %s %n ", resultadoTotal.isNullable(i) == 0?"No":"Si");
                System.out.printf(" Máximo ancho de la columna: %d %n",
                        resultadoTotal.getColumnDisplaySize(i));
            }
            mostrarSelect.close();
            resultado.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main().ejercicio9();
    }
}
