package ejercicio7;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio7(){
        /*
        String sqlSelectProf = "SELECT * from profesor order by ? desc ";
        String filtro1Nombre = "apellidos";
        String filtroAscendente = "";
        String filtro2Nombre = "fechaAlta";
        String filtroDescemdente = "desc";
        * */
        Connection connection;
        String sqlSelectApellProf = "SELECT * from profesor order by apellidos ";
        String sqlSelectFechaProf = "SELECT * from profesor order by fechaAlta desc";
        PreparedStatement mostrarSelect;
        ResultSet resultadoTutor;
        try {
            connection = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.prepareStatement(sqlSelectApellProf);
            resultadoTutor = mostrarSelect.executeQuery();
            System.out.println("Version A)");
            while (resultadoTutor.next()) {
                System.out.printf("%-10s %-10s %-17s %-10s",resultadoTutor.getString(1), resultadoTutor.getString(2), resultadoTutor.getString(3), resultadoTutor.getString(4)+"\n");
            }
            mostrarSelect = connection.prepareStatement(sqlSelectFechaProf);
            resultadoTutor = mostrarSelect.executeQuery();
            System.out.println("\nVersion B)");
            while (resultadoTutor.next()) {
                System.out.printf("%-10s %-10s %-17s %-10s",resultadoTutor.getString(1), resultadoTutor.getString(2), resultadoTutor.getString(3), resultadoTutor.getString(4)+"\n");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Main().ejercicio7();
    }
}
