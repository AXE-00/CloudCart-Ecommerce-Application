package com_selfProject_UserAuth.service;

import com_selfProject_UserAuth.exception.UserAlreadyPresentException;
import com_selfProject_UserAuth.exception.UserNotFound;
import com_selfProject_UserAuth.model.User;

public interface IAuthService {
    User addUser(User user) throws UserAlreadyPresentException;
    User login(User user) throws UserNotFound;
    User updateUser(String email,User user) throws UserNotFound;
    void removeUser(String email) throws UserNotFound;
}
