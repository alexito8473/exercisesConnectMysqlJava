package ejercicio1;

import ejercicio2.Constantes;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
    private void ejercicio1() {
        Connection connection = null;
        File script = new File(Constantes.TEXT_UBI_SCRIPT);
        Statement sents = null;
        String consulta = null;
        int res;
        try {
            connection = Constantes.connectServer( Constantes.URL_MULTIQUERI, Constantes.USUARIO, Constantes.CONTRASEÑA);
            sents = connection.createStatement();
            consulta = obtenerScript(script);
            System.out.println(consulta);
            res = sents.executeUpdate(consulta);
            System.out.println("Script creado con exito " + res);
            connection.close();
            sents.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    private String obtenerScript(File script) throws IOException {
        BufferedReader entrada = new BufferedReader(new FileReader(script));
        String linea, salto = System.lineSeparator();
        StringBuilder stringBuilder = new StringBuilder();
        while ((linea = entrada.readLine()) != null) {
            stringBuilder.append(linea);
            stringBuilder.append(salto);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        new Main().ejercicio1();
    }
}
