package ejercicio6;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class Main {
    private void ejercicio6() {
        Connection connection;
        String codOe ="FPB";
        String codCurso ="1A";
        int resultado;
        double porcentaje=0.10;
        PreparedStatement sentencia;
        String sqlEliminarAsignatura ="DELETE FROM asignatura WHERE codAsig IN (SELECT codAsig FROM curso c join reparto r on c.codOe=r.codOe and c.codCurso= r.codCurso where c.codOe = ? and c.codCurso=?)" ;
        String sqlEliminarOfertaEducativa ="DELETE FROM ofertaeducativa WHERE codOe =?" ;
        try {
            connection = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            // Elimino las asignaturas
            sentencia=connection.prepareStatement(sqlEliminarAsignatura);
            sentencia.setString(1,codOe);
            sentencia.setString(2,codCurso);
            resultado = sentencia.executeUpdate();
            System.out.println("1-> Se han borrad un total de "+ resultado + "fila/s ");
            // Elimino las oferta educativa
            sentencia=connection.prepareStatement(sqlEliminarOfertaEducativa);
            sentencia.setString(1,codOe);
            resultado = sentencia.executeUpdate();
            System.out.println("2-> Se han borrad un total de "+ resultado + "fila/s ");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main().ejercicio6();
    }
}
