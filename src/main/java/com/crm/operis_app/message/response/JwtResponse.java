package com.crm.operis_app.message.response;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

    public class JwtResponse {
        private String token;
        private String type = "Bearer";
        private String username;
        private Long personnelId;
        private String language;
        private Boolean blocked;
        private Collection<? extends GrantedAuthority> authorities;

        public JwtResponse(String accessToken, String username,Long personnelId,String language,Boolean blocked, Collection<? extends GrantedAuthority> authorities) {
            this.token = accessToken;
            this.username = username;
            this.personnelId = personnelId;
            this.language = language;
            this.blocked = blocked;
            this.authorities = authorities;
        }

        public String getAccessToken() {
            return token;
        }

        public void setAccessToken(String accessToken) {
            this.token = accessToken;
        }

        public String getTokenType() {
            return type;
        }

        public void setTokenType(String tokenType) {
            this.type = tokenType;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Long getPersonnelId() {
            return personnelId;
        }

        public void setPersonnelId(Long personnelId) {
            this.personnelId = personnelId;
        }

        public String getLanguage() {
            return language;
        }

        public void setLanguage(String language) {
            this.language = language;
        }

        public Collection<? extends GrantedAuthority> getAuthorities() {
            return authorities;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }
    }

//    private String token;
//    private String type = "Bearer";
//
//    public JwtResponse(String accessToken) {
//        this.token = accessToken;
//    }
//
//    public String getAccessToken() {
//        return token;
//    }
//
//    public void setAccessToken(String accessToken) {
//        this.token = accessToken;
//    }
//
//    public String getTokenType() {
//        return type;
//    }
//
//    public void setTokenType(String tokenType) {
//        this.type = tokenType;
//    }
