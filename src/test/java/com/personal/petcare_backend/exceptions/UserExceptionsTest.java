package com.personal.petcare_backend.exceptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.personal.petcare_backend.users.exceptions.UserExceptions;

public class UserExceptionsTest {

    @Test
    void testUserExceptionsWithMessage() {
        String message = "This is an error message";
        UserExceptions exception = assertThrows(UserExceptions.class, () -> {
            throw new UserExceptions(message);
        });
        assertEquals(message, exception.getMessage());
    }

    @Test
    void testUserExceptionsWithMessageAndCause() {
        String message = "This is an error message";
        Throwable cause = new IllegalArgumentException("Cause of the exception");

        UserExceptions exception = assertThrows(UserExceptions.class, () -> {
            throw new UserExceptions(message, cause);
        });
        assertEquals(message, exception.getMessage());
        assertEquals(cause, exception.getCause());
    }
}