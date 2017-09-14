/**
 * 
 */
package com.avijit.together.server.util;

/**
 * @author avijit
 *
 */
public class URIConstant {
	/* Rest API URI */
	public static final String API_BASE_URI = "/api";
	public static final String API_USERS_URI = API_BASE_URI + "/users";
	public static final String API_AUTHENTICATE_URI = API_BASE_URI + "/authentications";
	public static final String API_AUTHENTICATE_LOGOUT_URI = API_AUTHENTICATE_URI + "/logout";
	public static final String API_CONVERSATIONS_URI = API_BASE_URI + "/conversations";

	/* Chat URI */
	public static final String URI_BROKER_ENDPOINT = "/together/websocket";
	public static final String URI_DESTINATION_PREFIX = "/app";
	public static final String URI_BROKER_TOPIC = "/topic";
	public static final String URI_BROKER_QUEUE = "/queue";
	public static final String URI_GROUP_CHAT = "/groupchats";
	public static final String URI_PERSONAL_CHAT = "/personalchats";
	public static final String URI_TOPIC_GROUP_CHAT_RESPONSE = "/topic/groupchats.";
	public static final String URI_QUEUE_PERSONAL_CHAT_RESPONSE = "/queue/personalchats.";
}
