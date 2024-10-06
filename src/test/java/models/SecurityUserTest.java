package models;


import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.users.models.SecurityUser;
import com.personal.petcare_backend.users.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;
import java.util.HashSet;
import java.util.Set;

public class SecurityUserTest {

    @Test
    public void testGetAuthorities() {
        Role role = mock(Role.class);
        when(role.getName()).thenReturn("ROLE_USER");
        
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        
        User user = new User();
        user.setRoles(roles);
        
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.getAuthorities().size(), equalTo(1));
        assertThat(securityUser.getAuthorities().iterator().next().getAuthority(), equalTo("ROLE_USER"));
    }

    @Test
    public void testGetPassword() {
        User user = new User();
        user.setPassword("securePassword");
        
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.getPassword(), equalTo("securePassword"));
    }

    @Test
    public void testGetUsername() {
        User user = new User();
        user.setUsername("username");
        
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.getUsername(), equalTo("username"));
    }

    @Test
    public void testIsAccountNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.isAccountNonExpired(), equalTo(true));
    }

    @Test
    public void testIsAccountNonLocked() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.isAccountNonLocked(), equalTo(true));
    }

    @Test
    public void testIsCredentialsNonExpired() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.isCredentialsNonExpired(), equalTo(true));
    }

    @Test
    public void testIsEnabled() {
        User user = new User();
        SecurityUser securityUser = new SecurityUser(user);
        
        assertThat(securityUser.isEnabled(), equalTo(true));
    }
}