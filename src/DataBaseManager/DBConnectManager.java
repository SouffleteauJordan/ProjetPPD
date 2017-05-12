package DataBaseManager;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;

public class DBConnectManager {
	
	private static Connection connDb;
	
	public static void InitConnexion(){	
		try {
        	DBInitConnection.INIT_DB();
			connDb = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3307/projetppd?user=root&password=");
			DBService.INIT_DB();
		} catch (SQLException ex) {
		    // handle any errors
		    System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}
	}
	
	public static Connection getConnectionDB(){
		return connDb;
	}
	
}
