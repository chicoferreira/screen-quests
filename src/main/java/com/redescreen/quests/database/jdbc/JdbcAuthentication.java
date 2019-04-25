package com.redescreen.quests.database.jdbc;

public class JdbcAuthentication {

    private String url;
    private String username;
    private String password;

    private JdbcAuthentication() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static class Builder {

        private JdbcAuthentication authentication;

        public Builder() {
            this.authentication = new JdbcAuthentication();
        }

        public Builder withUrl(String url) {
            authentication.setUrl(url);
            return this;
        }

        public Builder withUsername(String username) {
            authentication.setUsername(username);
            return this;
        }

        public Builder withPassword(String password) {
            authentication.setPassword(password);
            return this;
        }

        public JdbcAuthentication build() {
            return authentication;
        }

    }
}
