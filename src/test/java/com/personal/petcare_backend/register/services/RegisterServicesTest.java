package com.personal.petcare_backend.register.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Set;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.personal.petcare_backend.implementatios.IEncryptFacade;
import com.personal.petcare_backend.register.exceptions.SaveUserException;
import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.roles.services.RoleService;
import com.personal.petcare_backend.users.dtos.UserDto;
import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.users.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

class RegisterServicesTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private RoleService roleService;

    @Mock
    private IEncryptFacade encryptFacade;

    @Mock
    private FirebaseAuth firebaseAuth;

    @InjectMocks
    private RegisterServices registerServices;

    @Value("${google-app-credentials}")
    private String googleCredentialsPath;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        if (FirebaseApp.getApps().isEmpty()) {
            FileInputStream serviceAccount = new FileInputStream("C:/Users/Arturo/personal/personalproject-backend/.vscode/secrets/Settings.json");
            FileInputStream refreshToken = new FileInputStream(googleCredentialsPath);

            FirebaseOptions options = FirebaseOptions.builder()
                .setCredentials(GoogleCredentials.fromStream(refreshToken))
                .setStorageBucket("petcares-b1b58.appspot.com")
                .build();

            FirebaseApp.initializeApp(options);
        }
    }

    @Test
    void assignAdminRole_ShouldReturnAdminRole() {
        Role adminRole = new Role(1L, "ADMIN");
        when(roleService.getById(1L)).thenReturn(adminRole);

        Set<Role> roles = registerServices.assignAdminRole();

        assertTrue(roles.contains(adminRole));
        verify(roleService, times(1)).getById(1L);
    }

    @Test
    void assignDefaultRole_ShouldReturnDefaultUserRole() {
        Role userRole = new Role(2L, "USER");
        when(roleService.getById(2L)).thenReturn(userRole);

        Set<Role> roles = registerServices.assignDefaultRole();

        assertTrue(roles.contains(userRole));
        verify(roleService, times(1)).getById(2L);
    }

    @Test
    void registerUser_ShouldThrowFirebaseAuthException() throws FirebaseAuthException {
        UserDto userDto = new UserDto("test@gmail.com", "password");

        FirebaseAuth firebaseAuth = mock(FirebaseAuth.class);
        doThrow(FirebaseAuthException.class).when(firebaseAuth)
                .createUser(any(UserRecord.CreateRequest.class));

        SaveUserException exception = assertThrows(SaveUserException.class, () -> {
            registerServices.registerUser(userDto);
        });

        assertEquals("Error registering user in Firebase", exception.getMessage());
    }

    @Test
    void registerUser_ShouldSaveUserSuccessfully() throws FirebaseAuthException {
        UserDto userDto = new UserDto("test@gmail.com", "password");
        
        UserRecord userRecord = mock(UserRecord.class);
        when(userRecord.getUid()).thenReturn("firebase-uid");
    
        UserRecord.CreateRequest createRequest = new UserRecord.CreateRequest()
                .setEmail(userDto.getUsername())
                .setPassword(userDto.getPassword());
    
        when(FirebaseAuth.getInstance().createUser(any(UserRecord.CreateRequest.class)))
                .thenReturn(userRecord);
    
        when(encryptFacade.decode("base64", "password")).thenReturn("decodedPassword");
        when(encryptFacade.encode("bcrypt", "decodedPassword")).thenReturn("encodedPassword");
    
        String result = registerServices.registerUser(userDto);
    
        assertEquals("test@gmail.com", result);
        verify(userRepository, times(1)).save(any(User.class));
    }
    
    @Test
    void save_ShouldThrowException_WhenEncodingFails() {
        UserDto userDto = new UserDto("test@gmail.com", "password");
        when(encryptFacade.decode("base64", "password")).thenThrow(new RuntimeException("Decode error"));

        SaveUserException exception = assertThrows(SaveUserException.class, () -> {
            registerServices.save(userDto, "firebase-uid");
        });

        assertEquals("Cannot save the user!", exception.getMessage());
    }

    @Test
    void save_ShouldSaveUserSuccessfully() {
        UserDto userDto = new UserDto("test@gmail.com", "password");
        when(encryptFacade.decode("base64", "password")).thenReturn("decodedPassword");
        when(encryptFacade.encode("bcrypt", "decodedPassword")).thenReturn("encodedPassword");

        User mockUser = new User(); 
        mockUser.setUsername("test@gmail.com");
        mockUser.setPassword("encodedPassword"); 

        String result = registerServices.save(userDto, "firebase-uid");

        assertEquals("test@gmail.com", result);
        verify(userRepository, times(1)).save(any(User.class)); 
    }
}