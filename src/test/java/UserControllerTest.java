import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import com.personal.petcare_backend.users.controllers.UserController;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
    }

    @Test
    public void testLogin() {
        // Configura el mock para retornar el nombre de usuario y el rol
        when(authentication.getName()).thenReturn("testUser");
    
        // Crea un Set de GrantedAuthority con los roles deseados
        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        
        // Usa thenReturn con el tipo correcto
        when(authentication.getAuthorities()).thenReturn((Collection) authorities);
    
        // Llama al método login
        ResponseEntity<Map<String, String>> response = userController.login();
    
        // Verifica el resultado
        assertThat(response.getStatusCode(), is(HttpStatus.ACCEPTED));
        assertThat(response.getBody().get("Message"), is("Logged"));
        assertThat(response.getBody().get("Username"), is("testUser"));
        assertThat(response.getBody().get("role"), is("ROLE_USER"));
    }
}