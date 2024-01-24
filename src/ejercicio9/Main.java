package ejercicio9;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class Main {
    private void ejercicio9() {
        Connection connection;
        String sqlSelectCursToProf = "SELECT * from profesor left join curso on codTutor=codProf";
        PreparedStatement mostrarSelect;
        ResultSet resultado;
        int numeroColumnas;
        String nula;
        ResultSetMetaData resultadoTotal;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.prepareStatement(sqlSelectCursToProf);
            resultado = mostrarSelect.executeQuery();
            resultadoTotal = resultado.getMetaData();
            numeroColumnas = resultadoTotal.getColumnCount();
            for (int i = 1; i <= numeroColumnas; i++) {
                System.out.printf("Columna %d: %n ", i);
                System.out.printf(" Nombre: %s %n  Tipo: %s %n ", resultadoTotal.getColumnName(i), resultadoTotal.getColumnTypeName(i));
                if (resultadoTotal.isNullable(i) == 0) {
                    nula = "No";
                } else {
                    nula = "Si";
                }
                System.out.printf(" Puede ser nula?: %s %n ", nula);
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
