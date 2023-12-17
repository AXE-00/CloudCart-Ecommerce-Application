package com_selfProject_UserService.service;


import com_selfProject_UserService.domain.FavItems;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserAlreadyExist;
import com_selfProject_UserService.exception.UserNotFoundException;

public interface IUserService {
  User addUser(User user) throws UserAlreadyExist;
  User updateUser(String email,User user) throws UserNotFoundException;
  User addItemInList(String email, FavItems favItems);
  boolean itemExist(String email,int itemId);
  void removeItemFromFav(String email,int itemId);
  byte[] getUserImage(String email)throws UserNotFoundException;
  User getUserName(String email)throws UserNotFoundException;
}
