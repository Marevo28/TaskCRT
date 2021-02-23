package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserModel {
    @JsonProperty("domain_id")
    private String domainId;
    @JsonProperty("password")
    private String password;
    @JsonProperty("username")
    private String username;

    public UserModel(String domainId, String password, String username) {
        this.domainId = domainId;
        this.password = password;
        this.username = username;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return
                "UserModel{" +
                        "domain_id = '" + domainId + '\'' +
                        ",password = '" + password + '\'' +
                        ",username = '" + username + '\'' +
                        "}";
    }
}
