package com.personal.petcare_backend.register.services;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personal.petcare_backend.implementatios.IEncryptFacade;
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

    public String registerUser(UserDto newUserDto) {
        try {
            UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                    .setEmail(newUserDto.getUsername()) 
                    .setPassword(newUserDto.getPassword());
            
            UserRecord userRecord = FirebaseAuth.getInstance().createUser(request);

            return save(newUserDto); 
        } catch (FirebaseAuthException e) {
            System.out.println("There was a error to register the user: " + e.getMessage());
            throw new SaveUserException("Error of register user in firebase");
        } catch (Exception e) {
            System.out.println(e);
            throw new SaveUserException("the user cant be saved.");
        }
    }

    public String save(UserDto newUserDto) {
        try {
            String passwordDecoded = encoderFacade.decode("base64", newUserDto.getPassword());
            String passwordEncoded = encoderFacade.encode("bcrypt", passwordDecoded);

            User user = new User(newUserDto.getUsername(), passwordEncoded);
            user.setRoles(assignDefaultRole()); 

            repository.save(user);

            return user.getUsername();
        } catch (Exception e) {
            System.out.println(e);
        }

        throw new SaveUserException("Can not save the user!");
    }

    public Set<Role> assignDefaultRole() {
        Role defaultRole = roleService.getById(1L); 

        Set<Role> roles = new HashSet<>();
        roles.add(defaultRole);

        return roles;
    }
}