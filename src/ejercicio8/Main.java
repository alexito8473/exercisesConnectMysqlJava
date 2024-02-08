package ejercicio8;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    private void ejercicio8() {
        Connection connection;
        String sqlSelectCursToProf = "SELECT * from profesor left join curso on codTutor=codProf";

        Statement mostrarSelect;
        ResultSet resultadoTutor;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.createStatement();
            resultadoTutor = mostrarSelect.executeQuery(sqlSelectCursToProf);
            while (resultadoTutor.next()) {
                System.out.printf("%-4s %-11s %-18s %-11s ",resultadoTutor.getString("codProf"), resultadoTutor.getString("nombre"), resultadoTutor.getString("apellidos"), resultadoTutor.getString("fechaAlta"));
                if (resultadoTutor.getString("codCurso")!=null) {
                    System.out.printf("tiene el curso -> %-3s %s \n", resultadoTutor.getString("codOe"), resultadoTutor.getString("codCurso"));
                } else {
                    System.out.println("Este profesor no es tutor");
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main().ejercicio8();
    }
}
