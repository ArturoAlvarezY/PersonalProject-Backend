package com.personal.petcare_backend.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.users.exceptions.UserNameNotFoundException;

public class UserNameNotFoundExceptionTest {

    @Test
    void testUserNameNotFoundExceptionWithMessage() {
        String message = "User not found";
        UserNameNotFoundException exception = assertThrows(UserNameNotFoundException.class, () -> {
            throw new UserNameNotFoundException(message);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testUserNameNotFoundExceptionWithMessageAndCause() {
        String message = "User not found";
        Throwable cause = new IllegalArgumentException("Cause of the exception");

        UserNameNotFoundException exception = assertThrows(UserNameNotFoundException.class, () -> {
            throw new UserNameNotFoundException(message, cause);
        });
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}