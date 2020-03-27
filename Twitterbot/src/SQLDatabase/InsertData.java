package SQLDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.PreparedStatement;

import java.util.logging.Level;
import java.util.logging.Logger;

import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

public class InsertData {
	
	public static void main(String[] args) throws SQLException, TwitterException {

		String url = "jdbc:postgresql://localhost:5432/students";
        String user = "postgres";
        String password = "4562";
        
        ConfigurationBuilder cf = new ConfigurationBuilder();
		
		cf.setDebugEnabled(true)
			.setOAuthConsumerKey("rp1NbyDrSbBhsKMTQlFElgj4u")
			.setOAuthConsumerSecret("JPt6YyNsU4dbU03VLor1SM3o9EzzNc8DKYy4iXIVsnuEzkZUsD")
			.setOAuthAccessToken("2635529756-fddu2ft1lJyHBvKdS281eaK0kZRfF2cWwFC7DCa")
			.setOAuthAccessTokenSecret("W0BMGlxprr5pOjHUbWx5wIbap0WRZsRZSOgchpBiTTEWu");
		
		TwitterFactory tf = new TwitterFactory(cf.build());
		twitter4j.Twitter twitter = tf.getInstance();
        
    		java.util.List<Status> status = twitter.getHomeTimeline();
    		for (Status stat : status) 
    		{
    			String name = stat.getUser().getName();
    			String description = stat.getText();
    			String link = "https://twitter.com/" + stat.getUser().getScreenName() + "/status/" + stat.getId();
    			
    			java.util.Date date = stat.getCreatedAt();
    			DateFormat dateFormat = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");  
    			String strDate = dateFormat.format(date);  
    			Timestamp ts = new Timestamp(date.getTime());
				        	
		        String query = "INSERT INTO TWITTER(name, description, link, date, edate) VALUES(?, ?, ?, ?, ?)\n";
		        
		        try (Connection c = DriverManager.getConnection(url, user, password);
		        	PreparedStatement stmt = c.prepareStatement(query)) {
		        	
		        	//Input Data into respective Columns
		            stmt.setString(1, name);
		            stmt.setString(2, description);
		            stmt.setString(3, link);
		            stmt.setString(4, strDate);
		            stmt.setTimestamp(5, ts);
		            stmt.executeUpdate();
		            
		        
			        
        } catch (SQLException ex) {

            Logger lgr = Logger.getLogger(InsertData.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
           
       					}
			        }
    		
    				DeleteCopies.delete();
	        	}
	        }
	
	
