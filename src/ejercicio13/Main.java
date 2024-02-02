package ejercicio13;

import ejercicio2.Constantes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    private void ejercicio13() {
        Connection connection;
        String codOe = "DAM";
        String codCurso = "1A";
        String sql = "SELECT * from curso c join horario h on c.codCurso=h.codCurso and c.codOe=h.codOe join tramohorario t on t.codTramo=h.codTramo where c.codOe=? and c.codCurso=? order by  horaInicio,t.dia,codAsig DESC ";
        PreparedStatement sentencia;
        ResultSet resultado;
        String saltador = "";
        String tipo = "";
        try {
            connection = Constantes.connectServer(Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sentencia = connection.prepareStatement(sql);
            sentencia.setString(1, codOe);
            sentencia.setString(2, codCurso);
            resultado = sentencia.executeQuery();
            System.out.printf("%15s%12s%13s%11s%13s","Lunes","Martes","Miercoles","Jueves","Viernes");
            while (resultado.next()) {
                if ( !saltador.equals(resultado.getString(9)) ) {
                    System.out.printf("\n%-10s%s",resultado.getString(9),resultado.getString(6));
                    saltador=resultado.getString(9);
                    tipo=resultado.getString(6);
                }else{
                    if(resultado.getString(6).substring(1).contains(tipo)){
                        System.out.print("*");
                        tipo=" ";
                    }else{
                        System.out.printf("\t%10s",resultado.getString(6));
                        tipo = resultado.getString(6);
                    }
                }
            }
          //  System.out.printf("%s %s %s %s %s %s  %s", resultado.getString(1), resultado.getString(2), resultado.getString(3), resultado.getString(6), resultado.getString(7), resultado.getString(9), resultado.getString(11));
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main( String[] args ) {
        new Main().ejercicio13();
    }
}
