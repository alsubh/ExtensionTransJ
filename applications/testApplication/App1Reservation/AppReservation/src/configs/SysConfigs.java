package configs;

public class SysConfigs {

	/* --- Names --- */
	public static final String name_App = "Reservation Project";

	/* --- URL --- */
	public static final String url_Host = "http://localhost:8080/";
	public static final String url_AppPath = "ReservationProject/";
	public static final String url_Assets = url_Host + url_AppPath + "resources/";

	/* --- SQL Database --- */
	public static final String db_Driver = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
	public static final String db_ConnectionString = "jdbc:sqlserver://ReservationDS";
	
	public static final String url = "jdbc:microsoft:sqlserver://";
	public static final String serverName= "localhost";
	public static final String portNumber = "1433";
	public static final String databaseName= "ReservationDS";
	
	public static final String db_Username = "";
	public static final String db_Password = "";
	
    // Informs the driver to use server a side-cursor, 
    // which permits more than one active statement 
    // on a connection.
    private final String selectMethod = "cursor"; 

}
