package com.example.dbHackathon.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.validator.parameternameprovider.ParanamerParameterNameProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;

import facebook4j.Facebook;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
@RestController
@RequestMapping("/status")
public class PublishStatusApi {
	
	
	static String consumerKeyStr = "D4ikKCBqP1WpG1goGl5TQx6fu";
	static String consumerSecretStr = "UqawfPx7TBMKeK6UFWgqrfJZ4NMbT6wmv1O5cFkHF3aScR7sNq";
	static String accessTokenStr = "1180176260510535680-KfXrLtv9tDtb7oIlwX7ExzS7RHCy94";
	static String accessTokenSecretStr = "YIvVSIYshUrCbOTeLwHKuVesGaj3qh4wARrKNWBCOI9uh";

	@GetMapping("/twitter")
	public String updateTwitterStatus() {
		try {
			Twitter twitter = new TwitterFactory().getInstance();

			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);
			AccessToken accessToken = new AccessToken(accessTokenStr,
					accessTokenSecretStr);

			twitter.setOAuthAccessToken(accessToken);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();

			twitter.updateStatus("Post using Java code on " + dateFormat.format(date));

			System.out.println("Successfully updated the status in Twitter.");
		} catch (TwitterException te) {
			te.printStackTrace();
		}
		System.out.println("Status ");
		return "Twitter  Status Updated";
				
	}
	
	@GetMapping("/facebook")
	public String updateFacebookStatus() {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		
		FacebookClient fb = new DefaultFacebookClient("EAAHqdKrY9UABANW8E4xEOwwupd7MqGlXtIZAsjTiZBsuCJtLysWpEwq3a6slVSjVZA2eZC0FWHogZBxHp0OGCBWow0ZCmWipsEo7YDi1FuEOGZBFZCoVK8bF2MWjzZAvwrDg9ekwybkEzLGZBHMmmSHFcRagZCOZAVQubZAgKiCAKCxoM9IPl5YNjEd51KdBNhlqYuwZCN4aYbpJt62wZDZD" 
				, Version.VERSION_4_0);
		fb.publish("me/feed", FacebookType.class , Parameter.with("message", "Post using Java code on " + dateFormat.format(date)));

		System.out.println("Status ");
		return "Facebook Status Updated";
				
	}

}
