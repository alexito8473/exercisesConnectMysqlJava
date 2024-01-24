package ejercicio4;

import com.mysql.cj.exceptions.StreamingNotifiable;
import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    private void ejercicio4() {
        System.out.println("Añade el primer curso de la FP Básica con el tutor Daniel Ayala Soriano y las siguientes\n" +
                "asignaturas.");
        Connection connection;
        String nombreTutor = "María";
        String apellidosTutor = "Gallego Díaz";
        String codTutor;
        String codFP = "FPB";
        String codCurso = "1A";
        String codAsig1 = "OPU";
        String nombre1 = "Operaciones auxiliares para la configuración y la explotación";
        int horasSemanales1 = 7;
        int horasTotales1 = 245;
        String codAsig2 = "MMT";
        String nombre2 = "Montaje y mantenimiento de sistemas y componentes informáticos";
        int horasSemanales2 = 9;
        int horasTotales2 = 315;
        try {
            connection = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            codTutor = obtenerCodTutor(connection, nombreTutor, apellidosTutor);
            añadirCursoFP(connection, codFP, codCurso, codTutor);
            añadirAsignatura(connection, codAsig1, nombre1, horasSemanales1, horasTotales1);
            añadirAsignatura(connection, codAsig2, nombre2, horasSemanales2, horasTotales2);
            añadirReparto(connection, codFP, codCurso, codAsig1, obtenerCodTutor(connection, "Carmelo", "Villegas Cruz"));
            añadirReparto(connection, codFP, codCurso, codAsig2, codTutor);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private String obtenerCodTutor(Connection connection, String nombre, String apellidos) throws SQLException {
        String sqlSelectProfesor = "SELECT codProf FROM horario.profesor where nombre= ? and apellidos= ? ";
        PreparedStatement sentenciaSelectTutor;
        ResultSet resultadoTutor;
        String codTutor = null;
        sentenciaSelectTutor = connection.prepareStatement(sqlSelectProfesor);
        sentenciaSelectTutor.setString(1, nombre);
        sentenciaSelectTutor.setString(2, apellidos);
        resultadoTutor = sentenciaSelectTutor.executeQuery();
        while (resultadoTutor.next()) {
            codTutor = resultadoTutor.getString(1);
        }
        return codTutor;
    }

    private void añadirCursoFP(Connection connection, String codFp, String codCurso, String codTutor) throws SQLException {
        String insertCurso = "INSERT INTO curso VALUES (?,?,?)";
        PreparedStatement sentencia = connection.prepareStatement(insertCurso);
        sentencia.setString(1, codFp);
        sentencia.setString(2, codCurso);
        sentencia.setString(3, codTutor);
        System.out.println("Se ha añadidio un total de " + sentencia.executeUpdate() + "de filas");
    }

    private void añadirAsignatura(Connection connection, String codAsig, String nombre, int horasSemanales, int horasTotales) throws SQLException {
        String insertCurso = "INSERT INTO asignatura VALUES (?,?,?,?)";
        PreparedStatement sentencia = connection.prepareStatement(insertCurso);
        sentencia.setString(1, codAsig);
        sentencia.setString(2, nombre);
        sentencia.setInt(3, horasSemanales);
        sentencia.setInt(4, horasTotales);
        System.out.println("Se ha añadidio un total de " + sentencia.executeUpdate() + "de filas");
    }

    private void añadirReparto(Connection connection, String codOe, String codCurso, String codAsig, String codProf) throws SQLException {
        String insertCurso = "INSERT INTO reparto VALUES (?,?,?,?)";
        PreparedStatement sentencia = connection.prepareStatement(insertCurso);
        sentencia.setString(1, codOe);
        sentencia.setString(2, codCurso);
        sentencia.setString(3, codAsig);
        sentencia.setString(4, codProf);
        System.out.println("Se ha añadidio un total de " + sentencia.executeUpdate() + " filas");
    }

    public static void main(String[] args) {
        new Main().ejercicio4();
    }
}
