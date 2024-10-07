package services;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.personal.petcare_backend.roles.exceptions.RoleNotFoundException;
import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.roles.repository.RoleRepository;
import com.personal.petcare_backend.roles.services.RoleService;
import org.junit.jupiter.api.Assertions; 


import java.util.Optional;

public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetById_WhenRoleExists() {
        Long roleId = 1L;
        Role expectedRole = new Role(roleId, "ROLE_USER");
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(expectedRole));

        Role actualRole = roleService.getById(roleId);

        assertThat(actualRole, is(notNullValue()));
        assertThat(actualRole.getId(), is(equalTo(expectedRole.getId())));
        assertThat(actualRole.getName(), is(equalTo(expectedRole.getName())));
    }

    @Test
    public void testGetById_WhenRoleDoesNotExist() {
        Long roleId = 1L;
        when(roleRepository.findById(roleId)).thenReturn(Optional.empty());

        RoleNotFoundException exception = Assertions.assertThrows(RoleNotFoundException.class, () -> {
            roleService.getById(roleId);
        });

        assertThat(exception.getMessage(), is(equalTo("Role not found")));
    }
}