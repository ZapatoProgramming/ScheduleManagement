import java.sql.*;
import java.io.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionMySQL {
    static final String URL = "jdbc:mysql://localhost/";
    static final String BD = "Proyecto";			// especificar: el nombre de la BD,
    static final String USER = "Daniel";		// el nombre de usuario
    static final String PASSWD = "ProyectoBases";// el password del usuario
    static Connection conn = null;
    static Statement stmt = null;
    static BufferedReader in = null;

    public static int diaDeLaSemana(String fechastr){

        // Convertir la fecha a un objeto LocalDate
        LocalDate fecha = LocalDate.parse(fechastr, DateTimeFormatter.ISO_LOCAL_DATE);

        // Obtener el nombre del día de la semana en español
        String diaDeLaSemana = String.valueOf(fecha.getDayOfWeek());
        System.out.println(diaDeLaSemana);
        switch (diaDeLaSemana) {
            case "MONDAY": return 1;
            case "TUESDAY": return 2;
            case "WEDNESDAY": return 3;
            case "THURSDAY": return 4;
            case "FRIDAY": return 5;
            case "SATURDAY": return 6;
            case "SUNDAY": return 7;
            default: return 1;
        }

    }


    public TransactionMySQL() throws SQLException, Exception {  //segmento para habilitar el conector

        // this will load the MySQL driver, each DB has its own driver
        Class.forName( "com.mysql.cj.jdbc.Driver" );
        System.out.println( "Conectando a la base de datos " );

        // set up the connection with the DB
        conn = DriverManager.getConnection( URL+BD, USER, PASSWD );
        System.out.println( "Conectado!" );

        conn.setAutoCommit( false );         // inicio de la 1a transacción
        stmt = conn.createStatement();
        in = new BufferedReader( new InputStreamReader(System.in) );

    }

    public static void main(String[] args) throws SQLException,Exception{

        new TransactionMySQL();
        MM mm = new MM();


        if( args.length != 0 ) {

            System.err.println( "Use: java TransactionMySQL" );
            System.exit( 1 );
        }






    }

}
