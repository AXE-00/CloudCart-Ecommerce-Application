package com_selfProject_UserAuth.service;

import com_selfProject_UserAuth.exception.UserAlreadyPresentException;
import com_selfProject_UserAuth.exception.UserNotFound;
import com_selfProject_UserAuth.model.User;
import com_selfProject_UserAuth.repository.AuthRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements IAuthService{
    @Autowired
    private AuthRepo authRepo;

    @Override
    public User addUser(User user) throws UserAlreadyPresentException {
        if(authRepo.findById(user.getUserEmail()).isPresent()){
            System.out.println("User Already Present");
            throw new UserAlreadyPresentException();
        }
            return authRepo.save(user);
    }

    @Override
    public User login(User user) throws UserNotFound {
        if(authRepo.findById(user.getUserEmail()).isEmpty()){
            System.out.println("User Not present");
            throw new UserNotFound();
        }
        return authRepo.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
    }

    @Override
    public User updateUser(String email, User user) throws UserNotFound {
        if(authRepo.findById(email).isPresent()){
            User existingUser = authRepo.findByUserEmailAndPassword(user.getUserEmail(), user.getPassword());
            if(user.getUserName()!=null){
                existingUser.setUserName(user.getUserName());
            }
            if(user.getPhoneNo() != 0L){
                existingUser.setPhoneNo(user.getPhoneNo());
            }
            if(user.getImageName() != null){
                existingUser.setImageName(user.getImageName());
            }
            return authRepo.save(existingUser);
        }
        else {
            throw new UserNotFound();
        }
    }

    @Override
    public void removeUser(String email) throws UserNotFound {
        User user  = authRepo.findById(email).get();
       if(authRepo.findById(email).isEmpty()){
         throw new UserNotFound();
       }
        authRepo.delete(user);
        System.out.println("User Removed Successfully..!");
    }
}
