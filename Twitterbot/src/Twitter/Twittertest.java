package Twitter;


import twitter4j.Status;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class Twittertest {

	public static void main(String[] args) throws TwitterException {
		
		ConfigurationBuilder cf = new ConfigurationBuilder();
		
		cf.setDebugEnabled(true)
			.setOAuthConsumerKey("rp1NbyDrSbBhsKMTQlFElgj4u")
			.setOAuthConsumerSecret("JPt6YyNsU4dbU03VLor1SM3o9EzzNc8DKYy4iXIVsnuEzkZUsD")
			.setOAuthAccessToken("2635529756-fddu2ft1lJyHBvKdS281eaK0kZRfF2cWwFC7DCa")
			.setOAuthAccessTokenSecret("W0BMGlxprr5pOjHUbWx5wIbap0WRZsRZSOgchpBiTTEWu");
	
		TwitterFactory tf = new TwitterFactory(cf.build());
		twitter4j.Twitter twitter = tf.getInstance();
		
		
		java.util.List<Status> status = twitter.getHomeTimeline();
		for (Status st : status) 
		{
			String name = st.getUser().getName();
			String description = st.getText();
			String url = "https://twitter.com/" + st.getUser().getScreenName() + "/status/" + st.getId();
			
			
			System.out.println(name +"-----"+ description +"-----"+ url );
			
		}
				
	}	
	
	
}
