package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SessionId{
	@JsonProperty("session_id")
	private String sessionId;

	public void setSessionId(String sessionId){
		this.sessionId = sessionId;
	}

	public String getSessionId(){
		return sessionId;
	}

	@Override
 	public String toString(){
		return 
			"SessionId{" + 
			"session_id = '" + sessionId + '\'' + 
			"}";
		}
}
