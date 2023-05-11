import java.sql.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TransactionMySQL {

	Connection conn = null;
	Statement stmt = null;
	BufferedReader in = null;

	static final String URL = "jdbc:mysql://localhost/";
	static final String BD = "Proyecto";			// especificar: el nombre de la BD,
	static final String USER = "Daniel";		// el nombre de usuario
	static final String PASSWD = "ProyectoBases";// el password del usuario

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

	private void dumpResultSet( ResultSet rset ) throws SQLException { //segmento para imprimir tablas

		ResultSetMetaData rsetmd = rset.getMetaData();
		int i = rsetmd.getColumnCount();
		while( rset.next() ) {

			for( int j = 1; j <= i; j++ ) {
				System.out.print( rset.getString(j) + "\t" );
			}
			System.out.println();
		}
	}

	private void query( String statement ) throws SQLException { //segmento para ejecutar la querie

		ResultSet rset = stmt.executeQuery( statement );
		System.out.println( "Results:" );
		dumpResultSet( rset );

		System.out.println();
		rset.close();
	}

	private boolean habitacionDisponible(String starttime, String endtime, int weekday) throws SQLException {
		LocalTime startime= LocalTime.parse(starttime);
		LocalTime endtimee= LocalTime.parse(endtime);

		ResultSet rset = stmt.executeQuery("select starttime, endtime, weekday, ROOMID from schedule_;");
		while( rset.next() )
		{
			LocalTime baseStartTime = LocalTime.parse(rset.getString(1));
			LocalTime baseEndTime = LocalTime.parse(rset.getString(2));
			int baseweekday = Integer.parseInt(rset.getString(3));

				if (baseweekday == weekday) {
					if (startime.isAfter(baseStartTime) && startime.isBefore(baseEndTime) || startime.equals(baseStartTime) || startime.equals(baseEndTime)) {
						System.out.println("La tupla no se puede insertar porque se empalma con otra");
						return false;
					} else if (endtimee.isAfter(baseStartTime) && endtimee.isBefore(baseEndTime) || endtimee.equals(baseStartTime) || endtimee.equals(baseEndTime)) {
						System.out.println("La tupla no se puede insertar porque se empalma con otra");
						return false;

					}
				}
		}
		return true;
	}

	/*private boolean sePuedeInsertar(String starttime, String endtime, int weekday, String roomid) throws SQLException {
		LocalTime startime= LocalTime.parse(starttime);
		LocalTime endtimee= LocalTime.parse(endtime);

		ResultSet rset = stmt.executeQuery("select starttime, endtime, weekday, ROOMID from schedule_;");
		while( rset.next() )
		{
			LocalTime baseStartTime = LocalTime.parse(rset.getString(1));
			LocalTime baseEndTime = LocalTime.parse(rset.getString(2));
			int baseweekday = Integer.parseInt(rset.getString(3));
			String baseroomid = rset.getString(4);
			if(baseroomid.equals(roomid))
			{
				if (baseweekday == weekday) {
					if (startime.isAfter(baseStartTime) && startime.isBefore(baseEndTime) || startime.equals(baseStartTime) || startime.equals(baseEndTime)) {
						System.out.println("La tupla no se puede insertar porque se empalma con otra");
						return false;
					} else if (endtimee.isAfter(baseStartTime) && endtimee.isBefore(baseEndTime) || endtimee.equals(baseStartTime) || endtimee.equals(baseEndTime)) {
						System.out.println("La tupla no se puede insertar porque se empalma con otra");
						return false;

					}
				}
			}
		}
		return true;
	}*/ // funcion que verifica que no se empalmen

	private void close() throws SQLException {

		stmt.close();
		conn.close();
	}

	private int diaDeLaSemana(String fechastr){

		// Convertir la fecha a un objeto LocalDate
		LocalDate fecha = LocalDate.parse(fechastr, DateTimeFormatter.ISO_LOCAL_DATE);

		// Obtener el nombre del día de la semana en español
		String diaDeLaSemana = String.valueOf(fecha.getDayOfWeek());
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

	private boolean menu() throws SQLException, IOException
	{ //segmento para crear instrucciónes
		String statement;
		System.out.println( "(0) Register SEASON" );
		System.out.println( "(1) Register COURSE" );
		System.out.println( "(2) Register ROOM" );
		System.out.println( "(3) Room Check" );
		System.out.println( "(4) Room Reservation" );
		System.out.println( "(5) Schedule" );
		System.out.println( "(6) Aceptar todas las operaciones realizadas" );
		System.out.println( "(7) Salir\n" );
		System.out.print( "Option: " );

		switch(Integer.parseInt( "0" + in.readLine())) {
			case 0: //register season
			{
				System.out.println( "Escriba la temporada (PRIMAVERA, VERANO, OTOÑO)" );
				String season = in.readLine();
				System.out.println( "Escriba año" );
				int year = Integer.parseInt(in.readLine());
				System.out.println( "Escriba fecha de inicio del periodo (YYYY-MM-DD)");
				String startdate = in.readLine();
				System.out.println( "Escriba fecha de fin del periodo (YYYY-MM-DD)");
				String enddate = in.readLine();
				if(diaDeLaSemana(startdate)==1) stmt.executeUpdate("insert into SEASON(SEASON, YEAR_, START_DATE, END_DATE)   values('"+season+"', "+year+", "+"'"+startdate+"', '"+enddate+"');");
				else System.out.println("La temporada debe iniciar el lunes");
			}
			break;
			case 1: //register room
			{
				System.out.println( "Escriba la clave del curso" );
				String coursekey = in.readLine();
				System.out.println( "Escriba la seccion" );
				int section = Integer.parseInt(in.readLine());
				System.out.println( "Escriba el titulo del curso" );
				String title = in.readLine();
				System.out.println( "Escriba el nombre del profesor" );
				String teacher = in.readLine();
				stmt.executeUpdate("insert into course(COURSEKEY, SECTION, TITLE, TEACHER)  values('"+coursekey+"', "+section+", "+"'"+title+"', '"+teacher+"');");
			}
			break;

			case 2: // register salon
			{
				System.out.println( "Escriba la clave del salon" );
				String roomid = in.readLine();
				System.out.println( "Escriba el edificio del salon (IA, CS, SL, HU, CN)" );
				String building = in.readLine();
				System.out.println( "Escriba el tipo de salon (C,L,A)" );
				String roomtype = in.readLine();
				System.out.println( "Escriba la capacidad del salon" );
				int capacity = Integer.parseInt(in.readLine());
				stmt.executeUpdate("insert into room(roomid, building, roomtype, capacity) values('"+roomid+"', "+"'"+building+"',"+"'"+roomtype+"',"+capacity+");");
			}
			break;
			case 3: // room check
			{
				System.out.println("(1) Toda la informacion de todas las habitaciones");
				System.out.println("(2) Capacidad de todas las habitaciones");
				System.out.println("(3) Tipo de todas las habitaciones");
				System.out.println("(4) Informacion de una habitacion en especifico");
				System.out.print( "Option: " );
				switch (Integer.parseInt("0" + in.readLine())) {
					case 1:
						query("select * from room"); //colocar querie para room check
						break;
					case 2:
						query("select roomid, capacity from room");
						break;
					case 3:
						query("select roomid, roomtype from room");
						break;
					case 4: {
						System.out.println("Escriba la habitacion de la que requiere saber informacion");
						String room = in.readLine();
						query("select * from room where roomid=" + "'" + room + "'");
					}
					break;
				}
			}
				break;

			case 4: // room reservation
			{
				System.out.println("(1) Habitaciones ocupadas");
				System.out.println("(2) Habitaciones libres en un dia y hora especificos");
				System.out.println("(3) Reserve una habitación en una fecha y hora específicas");
				System.out.println("(4) Cancelar reservacion con fecha y hora especificos");
				System.out.print( "Option: " );
				switch (Integer.parseInt("0" + in.readLine())) {
					case 1:
						query("select roomid from reservation");
						break;
					case 2: {
						System.out.println("Escriba la fecha en la que quiere ver las habitaciones disponibles (YYYY-MM-DD)");
						String Date_Time = in.readLine();
						System.out.println("Escriba la hora en la que quiere ver las habitaciones disponibles (HH:MM:SS)");
						LocalTime startime = LocalTime.parse(in.readLine());
						query("(select roomid from room) except (select r.roomid from reservation r, schedule_ s where Date_Time='" + Date_Time + "' and STARTTIME='" + startime + "');");

					}
					break;
					case 3: {
						System.out.println("Escriba la clave del curso");
						String coursekey = in.readLine();
						System.out.println("Escriba la fecha de la reservacion (YYYY-MM-DD)");
						String Date_Time = in.readLine();
						System.out.println("Escriba el nombre del que realiza la reservacion");
						String Name_ = in.readLine();
						System.out.println("Escriba el id del salon");
						String ROOMID = in.readLine();
						int weekday = diaDeLaSemana(Date_Time);
						System.out.println("Escriba la hora de inicio de la reservacion (hh:mm:ss)");
						LocalTime startTime = LocalTime.parse(in.readLine());
						System.out.println("Escriba la duracion de la reservacion");
						int duration = Integer.parseInt(in.readLine());
						LocalTime endTime = startTime.plusMinutes(duration);
						stmt.executeUpdate("INSERT INTO RESERVATION(COURSEKEY,DATE_,STARTTIME,ENDTIME,NAME_,ROOMID,DURATION) VALUES(" + "'" + coursekey + "'," + "'" + Date_Time + "',"+ "'" + startTime + "',"+"'" + endTime + "'," + "'" + Name_ + "'," + "'" + ROOMID + "'," + duration + ");");
						/*if (sePuedeInsertar(startTime.toString(), endTime.toString(), weekday, ROOMID)) {
							stmt.executeUpdate("INSERT INTO RESERVATION(COURSEKEY,DATE_,STARTTIME,ENDTIME,NAME_,ROOMID,DURATION) VALUES(" + "'" + coursekey + "'," + "'" + Date_Time + "',"+ "'" + startTime + "',"+"'" + endTime + "'," + "'" + Name_ + "'," + "'" + ROOMID + "'," + duration + ");");
						}*/
					}
					break;
					case 4:
					{

						System.out.println("Ingrese la clave del curso de la reservacion que desea borrar");
						String coursekey = in.readLine();
						System.out.println("Ingrese la fecha de la reservacion que desea borrar (YYYY-MM-DD)");
						String Date_Time = in.readLine();
						System.out.println("Ingrese el nombre del que realizo la reservacion");
						String Name_ = in.readLine();
						System.out.println("Ingrese el id del salon de la reservacion que desa borrar");
						String roomid = in.readLine();
						System.out.println("Ingrese la duracion de la reservacion que desea borrar");
						String duration = in.readLine();
						stmt.executeUpdate("Delete from reservation where coursekey='" + coursekey + "'" + " and date_='" + Date_Time + "'" + " and name_='" + Name_ + "'" + " and roomid='" + roomid + "'" + " and duration=" + duration + ";");

					}


				}
			}
				break;

			case 5: { //schedule
				System.out.println("(1) Agregar un Horario\n");
				System.out.println("(2) Modificar la programación de un curso\n");
				System.out.println("(3) Eliminar un curso\n");
				System.out.println("(4) Horarios\n");
				switch (Integer.parseInt("0" + in.readLine())) {
					case 1: {
						System.out.println("Escriba el id del curso");
							String coursekey = in.readLine();
							System.out.println("Escriba la seccion del curso");
							int section = Integer.parseInt(in.readLine());
							System.out.println("Escriba el id del salon");
							String ROOMID = in.readLine();
							System.out.println("Que dia de la semana es el horario (1 es lunes 7 es domingo)");
							int weekday = Integer.parseInt(in.readLine());
							System.out.println("Escriba la hora de inicio de la reservacion (hh:mm:ss)");
							LocalTime startTime = LocalTime.parse(in.readLine());
							System.out.println("Escriba la duracion de la reservacion en minutos");
							int duration = Integer.parseInt(in.readLine());
							LocalTime endTime = startTime.plusMinutes(duration);
							System.out.println("Escriba el semestre de los estudiantes que se les imparte el curso");
							int semester = Integer.parseInt(in.readLine());
							System.out.println("Escriba la temporada del horario (PRIMAVERA, VERANO, OTOÑO)");
							String season = in.readLine();
							System.out.println("Escriba el año del horario");
							int year = Integer.parseInt(in.readLine());
							stmt.executeUpdate("INSERT INTO SCHEDULE_(COURSEKEY,ROOMID,SECTION,WEEKDAY,STARTTIME,ENDTIME,SEMESTER, SEASON, YEAR_) VALUES(" + "'" + coursekey + "'," + "'" + ROOMID + "'," + section + "," + weekday + "," + "'" + startTime + "'," + "'" + endTime + "'," + semester + ",'" + season + "'," + year + ");");
							/*if (sePuedeInsertar(startTime.toString(), endTime.toString(), weekday, ROOMID)) {
								stmt.executeUpdate("INSERT INTO SCHEDULE_(COURSEKEY,ROOMID,SECTION,WEEKDAY,STARTTIME,ENDTIME,SEMESTER, SEASON, YEAR_) VALUES(" + "'" + coursekey + "'," + "'" + ROOMID + "'," + section + "," + weekday + "," + "'" + startTime + "'," + "'" + endTime + "'," + semester + ",'" + season + "'," + year + ");");

							}*/
							break;

					}
					case 2:
					{
						System.out.println("Escriba la clave del curso que desea cambiar");
						String coursekey = in.readLine(); //coursekey
						System.out.println("Escriba la seccion del curso que desea cambiar");
						int section = Integer.parseInt(in.readLine()); // section
						System.out.println("Escriba el id del nuevo salon");
						String ROOMID = in.readLine(); //ROOMID
						System.out.println("Escriba el nuevo día que quiere reservar  (va de 1 a 7 siendo 1 lunes y 7 domingo)");
						int weekday = Integer.parseInt(in.readLine()); //weekday
						System.out.println("Escriba la nueva hora de inicio de la reservacion (hh:mm:ss)");
						String startTime = in.readLine(); //startTime
						System.out.println("Escriba la nueva hora de fin de la reservacion (hh:mm:ss)");
						String endTime = in.readLine(); // endTime
						System.out.println("Escriba el semestre de los estudiantes que se les imparte el curso");
						int semester = Integer.parseInt(in.readLine()); //semester
						System.out.println("Escriba la temporada del curso (Primavera, Verano, Otoño)");
						String season = in.readLine();
						System.out.println("Escriba el año del curso");
						int year = Integer.parseInt(in.readLine());
						stmt.executeUpdate("UPDATE SCHEDULE_ SET WEEKDAY = '" + weekday + "', ROOMID = '" + ROOMID + "', SECTION = '" + section + "', STARTTIME =  '" + startTime + "', ENDTIME =  '" + endTime + "', SEMESTER =" + semester +",SEASON = '"+season+"',"+ "YEAR_="+year+" WHERE COURSEKEY='" + coursekey + "';");
						/*if (sePuedeInsertar(startTime, endTime, weekday, ROOMID)) {
							stmt.executeUpdate("UPDATE SCHEDULE_ SET WEEKDAY = '" + weekday + "', ROOMID = '" + ROOMID + "', SECTION = '" + section + "', STARTTIME =  '" + startTime + "', ENDTIME =  '" + endTime + "', SEMESTER =" + semester +",SEASON = '"+season+"',"+ "YEAR_="+year+" WHERE COURSEKEY='" + coursekey + "';");
						}*/
					}
					break;
					case 3:
					{
						System.out.println("Escriba la clave del curso que desea eliminar");
						String coursekey = in.readLine(); //coursekey
						System.out.println("Escriba el id del salon que desea eliminar");
						String ROOMID = in.readLine(); //ROOMID
						System.out.println("Escriba la hora de inicio de la reservacion (hh:mm:ss) que desea eliminar");
						String startTime = in.readLine(); //startTime
						stmt.executeUpdate("DELETE FROM SCHEDULE_ WHERE coursekey='" + coursekey + "' AND STARTTIME='" + startTime + "' AND roomid='" + ROOMID + "';");
					}
					break;
					case 4: {
						System.out.println("(1) Ver todos los horarios posibles");
						System.out.println("(2) Ver todos los horarios por temporada");
						System.out.println("(3) Ver todos los horarios por curso");
						System.out.println("(4) Ver todos los horarios por salon");
						System.out.print( "Option: " );

						switch(Integer.parseInt( "0" + in.readLine()))
						{
							case 1:
							{
								System.out.println("Estos son los horarios establecidos:");
								query("select * from SCHEDULE_ ;"); //
							}
							break;

							case 2:
							{
								System.out.println("Ingrese la temporada de la cual desea saber los horarios");
								String season = in.readLine();
								System.out.println("Estos son los horarios de la temporada que pidio:");
								query("select * from SCHEDULE_ where season='"+season+ "'"+" ;"); //
							}
							break;
							case 3:
							{
								System.out.println("Ingrese el id del curso de la cual desea saber los horarios");
								String coursekey = in.readLine();
								System.out.println("Estos son los horarios de la temporada que pidio:");
								query("select * from SCHEDULE_ where coursekey='"+coursekey+ "'"+" ;"); //
							}
							break;
							case 4:
							{
								System.out.println("Ingrese el id del salon de la cual desea saber los horarios");
								String roomid = in.readLine();
								System.out.println("Estos son los horarios de la temporada que pidio:");
								query("select * from SCHEDULE_ where roomid='"+roomid+ "'"+" ;"); //
							}
							break;


						}
					}
					break;
				}
			}
			break;

			case 6:	conn.commit();      // fin de la transacción e inicio de la siguiente
				break;

			case 7:	return false;



		}
		return true;
	}



	public static void main( String arg[] ) throws SQLException, Exception {

		if( arg.length != 0 ) {

			System.err.println( "Use: java TransactionMySQL" );
			System.exit( 1 );
		}

		TransactionMySQL transaction = new TransactionMySQL();

		while( true )

			try {
				if( ! transaction.menu() )
					break;

			} catch( Exception e ) {

				System.err.println( "failed" );
				e.printStackTrace( System.err );
			}

		transaction.close();
	}
}