package ejercicio14;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio14(){
        Connection connection;
        String codTramo = "J4";
        String sql = "Select r.codOe,r.codCurso,r.codAsig,r.codProf,p.nombre,p.apellidos, horaInicio,horaFin,dia  from horario h join reparto r on r.codOe=h.codOe and r.codCurso=h.codCurso and r.codAsig= h.codAsig join profesor p on p.codProf=r.codProf join tramohorario t on t.codTramo=h.codTramo where h.codTramo=?";
        PreparedStatement sentencia;
        ResultSet resultado;
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, codTramo);
            resultado = sentencia.executeQuery();

            while (resultado.next()) {
                System.out.printf("El profesor/a %-10s %-15s, el dia %-6s, de las %s a las %s, tiene la asignatura %-5s del curso %-3s %s\n",resultado.getString(5),resultado.getString(6),resultado.getString(9).toLowerCase(),resultado.getString(7),resultado.getString(8),resultado.getString(3),resultado.getString(1),resultado.getString(2));
                //System.out.printf("%s %s %s %s %s %s %s %s %s\n", resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(4), resultado.getString(5), resultado.getString(6), resultado.getString(7), resultado.getString(8),resultado.getString(9));
            }
            } catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main( String[] args ) {
        new Main().ejercicio14();
    }
}
