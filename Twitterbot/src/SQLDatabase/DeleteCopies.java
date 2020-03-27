package SQLDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DeleteCopies {
	
	public static void delete() {

		String url = "jdbc:postgresql://localhost:5432/students";
        String user = "postgres";
        String password = "4562";
                	
		try (Connection c = DriverManager.getConnection(url, user, password);
		   Statement stmt = c.createStatement()) {
	            
	            c.prepareStatement("DELETE FROM twitter a USING (\r\n" + 
	            		"      SELECT MIN(ctid) as ctid, link\r\n" + 
	            		"        FROM twitter \r\n" + 
	            		"        GROUP BY link HAVING COUNT(*) > 1\r\n" + 
	            		"      ) b\r\n" + 
	            		"      WHERE a.link = b.link \r\n" + 
	            		"      AND a.ctid <> b.ctid").executeUpdate();
	            
	            c.prepareStatement("DELETE FROM twitter a USING (\r\n" + 
	            		"      SELECT MIN(ctid) as ctid, description\r\n" + 
	            		"        FROM twitter \r\n" + 
	            		"        GROUP BY description HAVING COUNT(*) > 1\r\n" + 
	            		"      ) b\r\n" + 
	            		"      WHERE a.description = b.description \r\n" + 
	            		"      AND a.ctid <> b.ctid").executeUpdate();
	            
			        
        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(DeleteCopies.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
           
       		}
		}
	}
	        