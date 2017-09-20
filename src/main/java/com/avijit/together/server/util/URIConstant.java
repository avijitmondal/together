/**
 * 
 */
package com.avijit.together.server.util;

/**
 * @author avijit
 *
 */
public interface URIConstant {
	/* Rest API URI */
	String API_BASE_URI = "/api";
	String API_USERS_URI = API_BASE_URI + "/users";
	String API_AUTHENTICATE_URI = API_BASE_URI + "/authentications";
	String API_AUTHENTICATE_LOGOUT_URI = API_AUTHENTICATE_URI + "/logout";
	String API_CONVERSATIONS_URI = API_BASE_URI + "/conversations";

	/* Chat URI */
	String URI_BROKER_ENDPOINT = "/together/websocket";
	String URI_DESTINATION_PREFIX = "/app";
	String URI_BROKER_TOPIC = "/topic";
	String URI_BROKER_QUEUE = "/queue";
	String URI_GROUP_CHAT = "/groupchats";
	String URI_PERSONAL_CHAT = "/personalchats";
	String URI_TOPIC_GROUP_CHAT_RESPONSE = "/topic/groupchats.";
	String URI_QUEUE_PERSONAL_CHAT_RESPONSE = "/queue/personalchats.";
}
