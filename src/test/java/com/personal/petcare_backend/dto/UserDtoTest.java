package com.personal.petcare_backend.dto;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.users.dtos.UserDto;

public class UserDtoTest {
    
    private UserDto userDto;

    @BeforeEach
    public void setUp() {
        // Inicializar UserDto antes de cada prueba
        userDto = new UserDto("test@gmail.com", "password");
    }

    @Test
    public void testGetUsername() {
        // Verificar que el método getUsername devuelve el valor correcto
        assertEquals("test@gmail.com", userDto.getUsername());
    }

    @Test
    public void testGetPassword() {
        // Verificar que el método getPassword devuelve el valor correcto
        assertEquals("password", userDto.getPassword());
    }

    @Test
    public void testSetUsername() {
        // Cambiar el nombre de usuario y verificar que se actualiza correctamente
        userDto.setUsername("newemail@gmail.com");
        assertEquals("newemail@gmail.com", userDto.getUsername());
    }

    @Test
    public void testSetPassword() {
        // Cambiar la contraseña y verificar que se actualiza correctamente
        userDto.setPassword("newpassword");
        assertEquals("newpassword", userDto.getPassword());
    }

    @Test
    public void testConstructor() {
        // Verificar que el constructor inicializa los valores correctamente
        UserDto newUserDto = new UserDto("anotheremail@gmail.com", "anotherpassword");
        assertEquals("anotheremail@gmail.com", newUserDto.getUsername());
        assertEquals("anotherpassword", newUserDto.getPassword());
    }
}