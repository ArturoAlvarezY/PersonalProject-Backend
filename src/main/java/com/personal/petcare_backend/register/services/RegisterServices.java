package com.personal.petcare_backend.register.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.personal.petcare_backend.implementatios.IEncryptFacade;
import com.personal.petcare_backend.profiles.models.Profile;
import com.personal.petcare_backend.register.exceptions.SaveUserException;
import com.personal.petcare_backend.roles.models.Role;
import com.personal.petcare_backend.roles.services.RoleService;
import com.personal.petcare_backend.users.dtos.UserDto;
import com.personal.petcare_backend.users.models.User;
import com.personal.petcare_backend.users.repository.UserRepository;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;

@Service
public class RegisterServices {

    private final UserRepository repository;
    private final RoleService roleService;
    private final IEncryptFacade encoderFacade;

    @Autowired
    public RegisterServices(UserRepository repository, RoleService roleService, IEncryptFacade encoderFacade) {
        this.repository = repository;
        this.roleService = roleService;
        this.encoderFacade = encoderFacade;
    }
    

    public Set<Role> assignAdminRole() {
        Role adminRole = roleService.getById(1L);  
        Set<Role> roles = new HashSet<>();
        roles.add(adminRole);
        return roles;
    }

    public Set<Role> assignDefaultRole() {
        Role userRole = roleService.getById(2L);  
        Set<Role> roles = new HashSet<>();
        roles.add(userRole);
        return roles;
    }
    
    @PostMapping
    public String registerUser(@RequestBody UserDto newUserDto) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(newUserDto.getUsername())
                    .setPassword(newUserDto.getPassword());

            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            return save(newUserDto, userRecord.getUid());

        } catch (FirebaseAuthException e) {
            System.out.println("There was an error registering the user: " + e.getMessage());
            throw new SaveUserException("Error registering user in Firebase");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new SaveUserException("The user can't be saved.");
        }
    }


    public String save(UserDto newUserDto, String firebaseUid) {
        try {
            String passwordDecoded = encoderFacade.decode("base64", newUserDto.getPassword());
            String passwordEncoded = encoderFacade.encode("bcrypt", passwordDecoded);

            User user = new User(newUserDto.getUsername(), passwordEncoded);
            if (newUserDto.getUsername().equalsIgnoreCase("adminLucas@gmail.com")) { 
                user.setRoles(assignAdminRole());
            } else {
                user.setRoles(assignDefaultRole());
            }

            Profile profile = new Profile();
            profile.setUser(user);
            user.setProfile(profile);

            repository.save(user);

            return user.getUsername();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            throw new SaveUserException("Cannot save the user!");
        }
    }
}
