package ejercicio15;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;


public class Main {
    private void ejercicio15(){
        Connection connection;
        LocalDateTime tiempoActual =LocalDateTime.now();
        String diaActual=tiempoActual.getDayOfWeek().getDisplayName(TextStyle.FULL,new Locale("es","Es")).toUpperCase();
        String tramoReal = String.format("%s:%s:%s",tiempoActual.getHour(),tiempoActual.getMinute(),tiempoActual.getSecond());
        System.out.println(tramoReal);
        String sql = "Select r.codOe,r.codCurso,r.codAsig,p.nombre,p.apellidos,dia from horario h join reparto r on r.codOe=h.codOe and r.codCurso=h.codCurso and r.codAsig= h.codAsig join profesor p on p.codProf=r.codProf join tramohorario t on t.codTramo=h.codTramo where t.horaFin>=? and t.horaInicio<=? and dia=?";
        PreparedStatement sentencia;
        ResultSet resultado;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, tramoReal);
            sentencia.setString(2, tramoReal);
            sentencia.setString(3, diaActual);
            resultado = sentencia.executeQuery();
            while (resultado.next()) {
                 System.out.printf("%s %s %-7s %-10s %-10s\n", resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main( String[] args ) {
        new Main().ejercicio15();
    }
}
