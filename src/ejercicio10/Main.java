package ejercicio10;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio10(){
        Connection connection;
        String sqlSelectApellProf = "SELECT o.codOe,c.codCurso, o.nombre,p.nombre from ofertaeducativa o join curso c join profesor p on o.codOe=c.codOe and p.codProf=c.codTutor";
        PreparedStatement mostrarSelect;
        ResultSet resultadoTutor;
        try {
            connection = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.prepareStatement(sqlSelectApellProf);
            resultadoTutor = mostrarSelect.executeQuery();
            while (resultadoTutor.next()) {
                System.out.printf("%-4s %-3s %-60s %-10s\n",resultadoTutor.getString("o.codOe"), resultadoTutor.getString("c.codCurso"), resultadoTutor.getString("o.nombre"), resultadoTutor.getString("p.nombre"));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Main().ejercicio10();
    }
}
