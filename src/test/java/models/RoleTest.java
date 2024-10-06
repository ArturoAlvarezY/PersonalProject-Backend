package models;


import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.users.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

public class RoleTest {

    @Test
    public void testRoleConstructorAndGetters() {
        Role role = new Role(1L, "ROLE_USER");
        
        assertThat(role.getId(), equalTo(1L));
        assertThat(role.getName(), equalTo("ROLE_USER"));
    }

    @Test
    public void testSetId() {
        Role role = new Role(1L, "ROLE_USER");
        role.setId(2L);
        
        assertThat(role.getId(), equalTo(2L));
    }

    @Test
    public void testSetName() {
        Role role = new Role(1L, "ROLE_USER");
        role.setName("ROLE_ADMIN");
        
        assertThat(role.getName(), equalTo("ROLE_ADMIN"));
    }

    @Test
    public void testSetUsers() {
        Role role = new Role(1L, "ROLE_USER");
        Set<User> users = new HashSet<>();
        User user = mock(User.class);
        users.add(user);
        
        role.setUsers(users);
        
        assertThat(role.getUsers().size(), equalTo(1));
        assertThat(role.getUsers().contains(user), equalTo(true));
    }

    @Test
    public void testGetUsers() {
        Role role = new Role(1L, "ROLE_USER");
        Set<User> users = new HashSet<>();
        User user = mock(User.class);
        users.add(user);
        role.setUsers(users);
        
        assertThat(role.getUsers().size(), equalTo(1));
        assertThat(role.getUsers().iterator().next(), equalTo(user));
    }
}