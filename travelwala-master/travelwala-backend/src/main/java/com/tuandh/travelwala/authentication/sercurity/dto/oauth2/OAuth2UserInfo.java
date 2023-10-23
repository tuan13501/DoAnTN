package com.tuandh.travelwala.authentication.sercurity.dto.oauth2;

import com.tuandh.travelwala.authentication.sercurity.entity.Provider;

import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String, Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract Provider getProvider();
    public abstract String getFirstName();
    public abstract String getLastName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}