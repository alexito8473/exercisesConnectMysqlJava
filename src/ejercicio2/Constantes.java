package ejercicio2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Constantes {
    public static final String USUARIO= "root";
    public static final String CONTRASEÑA ="root";
    public static final String URL ="jdbc:mysql://localhost:3306/horario?useSSL=false";
    public static final String TEXT_UBI_SCRIPT ="C:\\Users\\aleja\\OneDrive\\Escritorio\\2º\\Acceso_a_datos\\horario.sql";
    public static final String URL_MULTIQUERI ="jdbc:mysql://localhost:3306?allowMultiQueries=true";
    public static final String CLASS_NAME ="com.mysql.cj.jdbc.Driver";

    public static
    Connection connectServer(String url, String usuario, String contraseña) throws ClassNotFoundException, SQLException {
        Class.forName(Constantes.CLASS_NAME);
        return DriverManager.getConnection(url, usuario, contraseña);
    }
}
