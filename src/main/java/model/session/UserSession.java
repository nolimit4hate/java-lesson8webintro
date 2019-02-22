package model.session;

import model.UserRole;

public class UserSession {
    private final String sessionID;
    private final String username;
    private UserRole userRole;
    private final String userIP;
    private String destroyTime;

    public UserSession(String sessionID, String username, UserRole userRole, String userIP) {
        this.sessionID = sessionID;
        this.username = username;
        this.userRole = userRole;
        this.userIP = userIP;
    }

    public String getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(String destroyTime) {
        this.destroyTime = destroyTime;
    }

    public String getSessionID() {
        return sessionID;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionID='" + sessionID + '\'' +
                ", username='" + username + '\'' +
                ", userRole=" + userRole +
                ", userIP='" + userIP + '\'' +
                ", destroyTime='" + destroyTime + '\'' +
                '}';
    }
}
