package com.personal.petcare_backend.token;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;

import jakarta.servlet.http.HttpServletRequest;

public class FirebaseAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String idToken = header.substring(7);
            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String email = decodedToken.getEmail();

                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                return userDetails;
            } catch (FirebaseAuthException e) {
                logger.warn("FirebaseToken verification failed: " + e.getMessage(), e);
                return null;
            } catch (UsernameNotFoundException e) {
                logger.warn("User not found for email: " + e.getMessage(), e);
                return null;
            }
        }
        return null;
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

}