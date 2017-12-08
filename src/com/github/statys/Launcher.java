package com.github.statys;

import java.io.IOException;

import com.github.statys.io.FileReader;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
import twitter4j.ResponseList;
import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.User;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Launcher implements Runnable{

	private static Launcher instance;
	private static TwitterStream ts;
	private static Twitter twitter;
	private static final String[] keywords = {"StatysUnicode"};
	
	private Launcher() {
		Launcher.instance = this;
	}
	
	public static Launcher getInstance() {
		return Launcher.instance;
	}
	
	public static void main(String[] args) {
		String consumerkey = null;
		String secretkey = null;
		String accesstoken = null;
		String accesstokensecret = null;
		
		try {
			consumerkey = FileReader.getInstance().getFileContentAsString("../consumerkey");
			secretkey = FileReader.getInstance().getFileContentAsString("../secretkey");
			accesstoken = FileReader.getInstance().getFileContentAsString("../accesstoken");
			accesstokensecret = FileReader.getInstance().getFileContentAsString("../accesstokensecret");
		} catch(IOException e) {
			System.err.println("Exception : ");
			e.printStackTrace();
		}
		
		ConfigurationBuilder cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true)
		  .setOAuthConsumerKey(consumerkey)
		  .setOAuthConsumerSecret(secretkey)
		  .setOAuthAccessToken(accesstoken)
		  .setOAuthAccessTokenSecret(accesstokensecret);
		
		Configuration config = cb.build();
		
		TwitterStreamFactory tf = new TwitterStreamFactory(config);
	    ts = tf.getInstance();
	    twitter = new TwitterFactory(config).getInstance();
		
	    ts.addListener(new StatusListener() {

			@Override
			public void onException(Exception arg0) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onScrubGeo(long userId, long upToStatusId) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onStallWarning(StallWarning warning) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onStatus(Status status) {
				System.out.println(status);
				String text = status.getText();
				User user = status.getUser();
				if (text.contains("@"+ keywords[0]) && !user.getName().equals(keywords[0])) {
					String name = user.getScreenName();
					System.out.println("User " + name + " has send : "+ status.getText());
					try {
						StatusUpdate stUpdate = new StatusUpdate("@" + name + " S'il vous plaît, veuillez me suivre afin de pouvoir vous aider en DM.");
						stUpdate = stUpdate.inReplyToStatusId(status.getId());
						twitter.updateStatus(stUpdate);
						twitter.createFriendship(name);
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
			}
	    	
	    });
	    
	    FilterQuery filter = new FilterQuery();
	    filter.track(keywords);
	    Thread thread = new Thread(Launcher.instance);
	    thread.start();
	    ts.filter(filter);	
	    
	    
	}

	@Override
	public void run() 
	{
		try {
	    	ResponseList<DirectMessage> dm = twitter.getDirectMessages();
	    	for (DirectMessage message : dm)
	    	{
	    		System.out.println(message.getText());
	    	}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
