package com.github.statys;

import java.io.IOException;

import com.github.statys.io.FileReader;

import twitter4j.DirectMessage;
import twitter4j.FilterQuery;
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
import twitter4j.UserList;
import twitter4j.UserStreamListener;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class Launcher {

	private static Launcher instance;
	private static TwitterStream twitterStream;
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
	    twitterStream = tf.getInstance();
	    twitter = new TwitterFactory(config).getInstance();
		
	    /*twitterStream.addListener(new StatusListener() {

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
				String text = status.getText();
				User user = status.getUser();
				if (text.contains("@"+ keywords[0])) {
					String name = user.getScreenName();
					System.out.println("User " + name + " has send : "+ status.getText());
					if (!name.equals(keywords[0])) {
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
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
			}
	    	
	    });
	    */
	    twitterStream.addListener(new UserStreamListener() {

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
				String text = status.getText();
				User user = status.getUser();
				if (text.contains("@"+ keywords[0])) {
					String name = user.getScreenName();
					System.out.println("User " + name + " has send : "+ status.getText());
					if (!name.equals(keywords[0])) {
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
			}

			@Override
			public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onException(Exception arg0) {
				arg0.printStackTrace();
			}

			@Override
			public void onBlock(User source, User blockedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDeletionNotice(long directMessageId, long userId) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onDirectMessage(DirectMessage directMessage) {
				System.out.println(directMessage.getText());
				String name = directMessage.getSenderScreenName();
				if (!directMessage.getSenderScreenName().equals(keywords[0])) {
					try 
					{
						twitter.sendDirectMessage(name, "Que puis-je faire pour vous ?");
					} catch (TwitterException e) {
						e.printStackTrace();
					}
				}
			}

			@Override
			public void onFavorite(User source, User target, Status favoritedStatus) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFavoritedRetweet(User source, User target, Status favoritedRetweeet) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFollow(User source, User followedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onFriendList(long[] friendIds) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onQuotedTweet(User source, User target, Status quotingTweet) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onRetweetedRetweet(User source, User target, Status retweetedStatus) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnblock(User source, User unblockedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnfavorite(User source, User target, Status unfavoritedStatus) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUnfollow(User source, User unfollowedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserDeletion(long deletedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListCreation(User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListDeletion(User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListMemberAddition(User addedMember, User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListMemberDeletion(User deletedMember, User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListSubscription(User subscriber, User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListUnsubscription(User subscriber, User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserListUpdate(User listOwner, UserList list) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserProfileUpdate(User updatedUser) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onUserSuspension(long suspendedUser) {
				// TODO Auto-generated method stub
				
			}
	    });
	    
	    twitterStream.user();
	}
}
