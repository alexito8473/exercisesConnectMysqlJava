package ejercicio5;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

public class Main {
    private void ejercicio5() {
        Connection connection;
        String codOe = "FPB";
        String codCurso = "1A";
        String controlador = "M%";
        int resultado;
        double porcentaje = 0.10;
        PreparedStatement sentencia;
        String sqlActualizarAsignatura = "UPDATE asignatura set horasSemanales=horasSemanales+(horasSemanales*?), horasTotales=horasTotales+(horasTotales*?) WHERE codAsig in (SELECT codAsig FROM curso c join reparto r on c.codOe=r.codOe and c.codCurso= r.codCurso where c.codOe = ? and c.codCurso=?) AND codAsig LIKE ?";
        try {
            connection = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sqlActualizarAsignatura);
            sentencia.setDouble(1, porcentaje);
            sentencia.setDouble(2, porcentaje);
            sentencia.setString(3, codOe);
            sentencia.setString(4, codCurso);
            sentencia.setString(5, controlador);
            resultado = sentencia.executeUpdate();
            System.out.println("Se han actualizado un total de " + resultado + "fila/s");
        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new Main().ejercicio5();
    }
}
