package com.github.statys;

import java.io.IOException;
import java.util.Scanner;

import com.github.statys.io.FileReader;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.UserMentionEntity;
import twitter4j.conf.ConfigurationBuilder;

public class Launcher {

	private static Launcher instance;
	
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
		
		TwitterStreamFactory tf = new TwitterStreamFactory(cb.build());
	    TwitterStream ts = tf.getInstance();
		
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
				boolean foundMyself = false;
				
				for(UserMentionEntity e : status.getUserMentionEntities()) {
					if(e.getName().equalsIgnoreCase("@StatysUnicode")) {
						foundMyself = true;
					}
				}
				
				if(foundMyself) {
					System.out.println(status);
				}
				
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    ts.sample();
	    System.out.println(ts);
	    
	    Scanner sc = new Scanner(System.in);
	    sc.next();
	    
	    /*
	    try {
	    	while(true) {
	    		
	    	}
	    } catch (TwitterException e) {
			e.printStackTrace();
		}*/
		
	    
	}
}
