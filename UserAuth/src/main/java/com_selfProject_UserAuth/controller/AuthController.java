package com_selfProject_UserAuth.controller;

import com_selfProject_UserAuth.exception.UserAlreadyPresentException;
import com_selfProject_UserAuth.exception.UserNotFound;
import com_selfProject_UserAuth.model.User;
import com_selfProject_UserAuth.model.UserDto;
import com_selfProject_UserAuth.service.IAuthService;
import com_selfProject_UserAuth.service.ITokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/authService")
public class AuthController {

    private IAuthService authService;
    private ITokenGenerator tokenGenerator;

    @Autowired
    public AuthController(IAuthService authService, ITokenGenerator tokenGenerator) {
        this.authService = authService;
        this.tokenGenerator = tokenGenerator;
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) throws UserAlreadyPresentException {
        User user = new User(userDto.getUserEmail(),
                   userDto.getUserName(),
                   userDto.getPassword(),
                   userDto.getRole(),
                   userDto.getPhoneNo(),
                   userDto.getImageName());
        return new ResponseEntity<>(authService.addUser(user), HttpStatus.OK);
    }
    @PostMapping("/user/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) throws UserNotFound {
        User retrievedUser = authService.login(user);
        if(retrievedUser!=null){
            return new ResponseEntity<>(tokenGenerator.storeToken(user),HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Authorization Failed",HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/user/Update/{email}")
    public ResponseEntity<?> updateUser(@RequestBody UserDto userDto,@PathVariable String email) throws UserNotFound {
        User user = new User(userDto.getUserEmail(),
                             userDto.getUserName(),
                             userDto.getPassword(),
                             userDto.getRole(),
                             userDto.getPhoneNo(),
                             userDto.getImageName());
        return new ResponseEntity<>(authService.updateUser(email,user),HttpStatus.OK);
    }

    @DeleteMapping("/user/deleteUser/{email}")
    public ResponseEntity<?> removeUser(@PathVariable String email) throws UserNotFound {
        authService.removeUser(email);
        return new ResponseEntity<>("User Removed..!",HttpStatus.OK);
    }
}
