/**
 * 
 */
package com.avijit.together.core.data;

/**
 * @author avijit
 *
 */
public interface I_Constant {

	/* Rest API URI */
	/**
	 * 
	 */
	String API_BASE = "/api";
	String API_VERSION = "/v1";
	String API_PREFIX = API_BASE + API_VERSION;
	/**
	 * 
	 */
	String API_USERS = API_PREFIX + "/users";
	/**
	 * 
	 */
	String API_AUTHENTICATIONS = API_PREFIX + "/authentications";
	/**
	 * 
	 */
	String API_CONVERSATIONS = API_PREFIX + "/conversations";
	/*
	*
	*
	*/
	String API_PROFILE_PICTURES = API_PREFIX + "/profile_pictures";
	/**
	 * 
	 */
	String API_CONVERSATION_PARTICIPANTS = API_CONVERSATIONS + "/{conversation_id}/participants";

	String API_MESSAGES = API_CONVERSATIONS + "/{conversation_id}/messages";

	String API_CONVERSATION_FILES = API_CONVERSATIONS + "/ftp/{conversation_id}/files";

	/* Chat URI */
	/**
	 * 
	 */
	String URI_BROKER_ENDPOINT = "/together/websocket";
	/**
	 * 
	 */
	String URI_DESTINATION_PREFIX = "/app";
	/**
	 * 
	 */
	String URI_BROKER_TOPIC = "/topic";
	/**
	 * 
	 */
	String URI_BROKER_QUEUE = "/queue";
	/**
	 * 
	 */
	String URI_GROUP_CHAT = "/groupchats";
	/**
	 * 
	 */
	String URI_PERSONAL_CHAT = "/personalchats";
	/**
	 * 
	 */
	String URI_TOPIC_GROUP_CHAT_RESPONSE = "/topic/groupchats.";
	/**
	 * 
	 */
	String URI_QUEUE_PERSONAL_CHAT_RESPONSE = "/queue/personalchats.";

	String FILES_LOCATION = "C://together//db//files//";

	String TEMPORARY_FILES_LOCATION = "C://together//db//files//temporary//";

	String FILE_URI = "/%s/files?file_name=%s&s_id=%s";
}
