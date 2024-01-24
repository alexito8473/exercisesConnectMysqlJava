package ejercicio2;

import java.sql.*;

public class Main {
    private void ejercicio2() {
        Connection conexion;
        DatabaseMetaData dbmd;
        System.out.println("Mostrar información sobre las columnas de una tabla, sus claves primarias, las claves ajenas\nexistentes en la tabla y las claves ajenas que utilizan la clave primaria de esta tabla.");
        System.out.println("-----------------------------------");
        try {
            conexion = Constantes.connectServer( Constantes.URL, Constantes.USUARIO, Constantes.CONTRASEÑA);
            dbmd = conexion.getMetaData();
            mostrarDatosServidor(dbmd);
            for (NombreTabla tabla : NombreTabla.values()) {
                mostrarColumnaTabla(dbmd,tabla.getValor());
                mostrarPrimaryKeyTabla(dbmd,tabla.getValor());
                mostrarExportTabla(dbmd,tabla.getValor());
                mostrarImportTabla(dbmd,tabla.getValor());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void mostrarDatosServidor(DatabaseMetaData dbmd) throws SQLException {
        System.out.println("INFORMACIÓN SOBRE LA BASE DE DATOS:");
        System.out.printf("Nombre %s %n", dbmd.getDatabaseProductName());
        System.out.printf("Driver : %s %n", dbmd.getDriverName());
        System.out.printf("URL %s %n", dbmd.getURL());
        System.out.printf("Usuario: %s %n", dbmd.getUserName());
        System.out.println("-----------------------------------");
    }

    private void mostrarPrimaryKeyTabla(DatabaseMetaData dbmd, String tabla) throws SQLException {
        ResultSet pk = dbmd.getPrimaryKeys(null, "horario", tabla);
        String pkDep = "", separador = "";
        while (pk.next()) {
            pkDep = pkDep + separador +
                    pk.getString("COLUMN_NAME");//getString(4)
            separador = "+";
        }
        System.out.println("Clave Primaria: " + pkDep);
        System.out.println("-----------------------------------");
    }

    private void mostrarImportTabla(DatabaseMetaData dbmd, String tabla) throws SQLException {
        ResultSet fk = dbmd.getImportedKeys(null, "horario", tabla);
        boolean isData = false;
        String fk_name;
        String pk_name;
        String pk_tablename;
        String fk_tablename;
        String fk_name_re="";
        String pk_name_re="";
        String pk_tablename_re="";
        String fk_tablename_re="";
        while (fk.next()) {
            if (!isData) {
                System.out.println("Esta tabla importa :");
            }
             fk_name = fk.getString("FKCOLUMN_NAME");
             pk_name = fk.getString("PKCOLUMN_NAME");
             pk_tablename = fk.getString("PKTABLE_NAME");
             fk_tablename = fk.getString("FKTABLE_NAME");
            if(!fk_name.equals(fk_name_re)&&!pk_name.equals(pk_name_re)&&!pk_tablename.equals(pk_tablename_re)&&!fk_tablename.equals(fk_tablename_re)){
                System.out.printf("Tabla que tiene la clave primaria: %s, Clave Primaria: %s %n",
                        pk_tablename, pk_name);
                System.out.printf("Tabla que recibe la clave primaria: %s, Clave Ajena: %s %n",
                        fk_tablename, fk_name);
                fk_name_re=fk_name;
                pk_name_re=pk_name;
                pk_tablename_re=pk_tablename;
                fk_tablename_re=fk_tablename;
            }else{
                fk_name_re="";
                pk_name_re="";
                pk_tablename_re="";
                fk_tablename_re="";
            }
            isData=true;
        }
        if (!isData) {
            System.out.println("Esta tabla no importa nada");
        }
        System.out.println("-----------------------------------");
    }

    private void mostrarColumnaTabla(DatabaseMetaData dbmd, String tabla) throws SQLException {
        System.out.println("-----------Tabla " + tabla + "---------------");
        ResultSet columnas = dbmd.getColumns(null, "horario", tabla, null);
        while (columnas.next()) {
            System.out.printf("Columna: %s, Tipo: %s, Tamaño: %s,¿Puede ser Nula:? %s %n", columnas.getString("COLUMN_NAME"), columnas.getString("TYPE_NAME"), columnas.getString("COLUMN_SIZE"), columnas.getString("IS_NULLABLE"));
        }
        System.out.println("-----------------------------------");
    }

    private void mostrarExportTabla(DatabaseMetaData dbmd, String tabla) throws SQLException {
        ResultSet fk = dbmd.getExportedKeys(null, "horario", tabla);
        boolean isData = false;
        String fk_name;
        String pk_name;
        String pk_tablename;
        String fk_tablename;
        String fk_name_re="";
        String pk_name_re="";
        String pk_tablename_re="";
        String fk_tablename_re="";
        while (fk.next()) {
            if (!isData) {
                System.out.println("Esta tabla exporta :");
            }
             fk_name = fk.getString("FKCOLUMN_NAME");
             pk_name = fk.getString("PKCOLUMN_NAME");
             pk_tablename = fk.getString("PKTABLE_NAME");
             fk_tablename = fk.getString("FKTABLE_NAME");
            if(!fk_name.equals(fk_name_re)&&!pk_name.equals(pk_name_re)&&!pk_tablename.equals(pk_tablename_re)&&!fk_tablename.equals(fk_tablename_re)){
                System.out.printf("Tabla que tiene la clave primaria: %s, Clave Primaria: %s %n",
                        pk_tablename, pk_name);
                System.out.printf("Tabla que recibe la clave primaria: %s, Clave Ajena: %s %n",
                        fk_tablename, fk_name);
                fk_name_re=fk_name;
                pk_name_re=pk_name;
                pk_tablename_re=pk_tablename;
                fk_tablename_re=fk_tablename;
            }else{
                fk_name_re="";
                pk_name_re="";
                pk_tablename_re="";
                fk_tablename_re="";
            }
            isData=true;
        }
        if (!isData) {
            System.out.println("Esta tabla no exporta nada");
        }
        System.out.println("-----------------------------------");
    }

    public static void main(String[] args) {
        new Main().ejercicio2();
    }
}
