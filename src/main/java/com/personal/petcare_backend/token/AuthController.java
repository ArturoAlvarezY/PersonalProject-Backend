package com.personal.petcare_backend.token;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/verifyToken")
    public ResponseEntity<String> verifyToken(@RequestHeader("Authorization") String idToken) {
        try {
            if (idToken.startsWith("Bearer ")) {
                idToken = idToken.substring(7);
            }

            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();

            System.out.println(decodedToken);
            return ResponseEntity.ok("Token verified! User ID: " + uid);
        } catch (FirebaseAuthException e) {
            return ResponseEntity.status(401).body("Invalid token");
        }
    }
}
