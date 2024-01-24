package ejercicio11;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio11() {
        Connection connection;
        String sqlSelectApellProf = "SELECT c.codOe,c.codCurso,codAsig,codTramo from curso c join horario h on c.codCurso=h.codCurso and c.codOe=h.codOe where c.codOe=? and c.codCurso=? and codAsig=?";
        String oferta = "DAM";
        String curso = "2A";
        String asignatura = "SIST";
        PreparedStatement mostrarSelect;
        boolean sinDatos=true;
        ResultSet resultadoTutor;
        int cantidadColumna;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            mostrarSelect = connection.prepareStatement(sqlSelectApellProf);
            mostrarSelect.setString(1, oferta);
            mostrarSelect.setString(2, curso);
            mostrarSelect.setString(3, asignatura);
            resultadoTutor = mostrarSelect.executeQuery();
            cantidadColumna = resultadoTutor.getMetaData().getColumnCount();
            while (resultadoTutor.next()) {
                for (int i = 1; i <= cantidadColumna; i++) {
                    System.out.printf("%-5s", resultadoTutor.getString(i));
                }
                System.out.println();
                sinDatos=false;
            }
            if(sinDatos){
                System.out.println("Nos se ha encontrado ningún dato que coincida");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {
        new Main().ejercicio11();
    }
}
