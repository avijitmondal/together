/**
 * 
 */
package com.avijit.together.core.data;

/**
 * @author avijit
 *
 */
public abstract class Constants {

	/* Rest API URI */
	/**
	 * 
	 */
	public static final String API_BASE = "/api";
	public static final String API_VERSION = "/v1";
	public static final String API_PREFIX = API_BASE + API_VERSION;
	/**
	 * 
	 */
	public static final String API_USERS = API_PREFIX + "/users";
	/**
	 * 
	 */
	public static final String API_AUTHENTICATIONS = API_PREFIX + "/authentications";
	/**
	 * 
	 */
	public static final String API_CONVERSATIONS = API_PREFIX + "/conversations";
	/*
	*
	*
	*/
	public static final String API_PROFILE_PICTURES = API_PREFIX + "/profile_pictures";
	/**
	 * 
	 */
	public static final String API_CONVERSATION_PARTICIPANTS = API_CONVERSATIONS + "/{conversation_id}/participants";

	public static final String API_MESSAGES = API_CONVERSATIONS + "/{conversation_id}/messages";

	public static final String API_CONVERSATION_FILES = API_PREFIX + "/ftp/{conversation_id}/files";

	/* Chat URI */
	/**
	 * 
	 */
	public static final String URI_BROKER_ENDPOINT = "/together/websocket";
	/**
	 * 
	 */
	public static final String URI_DESTINATION_PREFIX = "/app";
	/**
	 * 
	 */
	public static final String URI_BROKER_TOPIC = "/topic";
	/**
	 * 
	 */
	public static final String URI_BROKER_QUEUE = "/queue";
	/**
	 * 
	 */
	public static final String URI_GROUP_CHAT = "/groupchats";
	/**
	 * 
	 */
	public static final String URI_PERSONAL_CHAT = "/personalchats";
	/**
	 * 
	 */
	public static final String URI_TOPIC_GROUP_CHAT_RESPONSE = "/topic/groupchats.";
	/**
	 * 
	 */
	public static final String URI_QUEUE_PERSONAL_CHAT_RESPONSE = "/queue/personalchats.";

	public static final String FILES_LOCATION = "C://together//db//files//";

	public static final String TEMPORARY_FILES_LOCATION = "C://together//db//files//temporary//";

	public static final String FILE_URI = "/%s/files?file_name=%s&s_id=%s";

	public static final String AUTH_TOKEN = "AUTH_TOKEN";
	public static final String AUTHORIZATION = "Authorization";

	// Rest URL
	public static final String URI_HTTP = "http://";
	public static final String URI_HTTPS = "https://";

	// Service names
	public static final String SERVICE_TOGETHER_CONFIG = "together-config";
	public static final String SERVICE_TOGETHER_CONVERSATION = "together-conversation";
	public static final String SERVICE_TOGETHER_DATABASE = "together-databse";
	public static final String SERVICE_TOGETHER_FTP = "together-ftp";
	public static final String SERVICE_TOGETHER_GATEWAY = "together-gateway";
	public static final String SERVICE_TOGETHER_LOGIN = "together-login";
	public static final String SERVICE_TOGETHER_REGISTRATION = "together-registration";
	public static final String SERVICE_TOGETHER_STATUS = "together-status";
	public static final String SERVICE_TOGETHER_USER = "together-user";

}
