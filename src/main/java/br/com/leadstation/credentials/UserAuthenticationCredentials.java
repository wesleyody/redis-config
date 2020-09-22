package br.com.leadstation.credentials;

import java.io.Serializable;

import org.springframework.security.core.context.SecurityContextHolder;

public class UserAuthenticationCredentials implements Serializable {

    private static final long serialVersionUID = 1L;

    private final Integer subscriptionId;
    private final Integer userId;

    public UserAuthenticationCredentials ( Integer subscriptionId, Integer userId ) {
        this.subscriptionId = subscriptionId;
        this.userId = userId;
    }

    public Integer getSubscriptionId () {
        return subscriptionId;
    }

    public Integer getUserId () {
        return userId;
    }

    public static UserAuthenticationCredentials fromContext () {
        return UserAuthenticationCredentials
                .class
                .cast( SecurityContextHolder.getContext().getAuthentication().getCredentials() );
    }

    public static boolean isAdmin () {
        return SecurityContextHolder.getContext()
                .getAuthentication()
                .getAuthorities()
                .stream()
                .anyMatch( auth -> auth.getAuthority().equals( "ADMIN" ) );
    }

}
