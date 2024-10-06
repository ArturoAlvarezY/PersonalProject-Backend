package models;


import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.users.models.User;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

import java.util.HashSet;
import java.util.Set;

public class UserTest {

    @Test
    public void testUserConstructorAndGetters() {
        Profile profile = new Profile();
        User user = new User("username", "password", profile);

        assertThat(user.getUsername(), equalTo("username"));
        assertThat(user.getPassword(), equalTo("password"));
        assertThat(user.getProfile(), equalTo(profile));
    }

    @Test
    public void testUserSetters() {
        User user = new User();
        user.setUsername("newUsername");
        user.setPassword("newPassword");

        assertThat(user.getUsername(), equalTo("newUsername"));
        assertThat(user.getPassword(), equalTo("newPassword"));
    }

    @Test
    public void testUserProfileRelationship() {
        Profile mockProfile = mock(Profile.class);
        User user = new User();
        user.setProfile(mockProfile);

        assertThat(user.getProfile(), equalTo(mockProfile));
    }

    @Test
    public void testUserRoles() {
        User user = new User();
        Set<Role> roles = new HashSet<>();
        Role role = new Role();
        roles.add(role);
        user.setRoles(roles);

        assertThat(user.getRoles().size(), equalTo(1));
        assertThat(user.getRoles().contains(role), equalTo(true));
    }

    @Test
    public void testUserProfileNull() {
        User user = new User();
        user.setProfile(null);

        assertThat(user.getProfile(), equalTo(null));
    }

    @Test
    public void testUserConstructorWithoutProfile() {
        User user = new User("username", "password");

        assertThat(user.getUsername(), equalTo("username"));
        assertThat(user.getPassword(), equalTo("password"));
        assertThat(user.getProfile(), equalTo(null));
    }

    @Test
    public void testUserPassword() {
        User user = new User();
        user.setPassword("securePassword");

        assertThat(user.getPassword(), equalTo("securePassword"));
    }
}