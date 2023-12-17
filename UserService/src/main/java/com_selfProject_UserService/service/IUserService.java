package com_selfProject_UserService.service;


import com_selfProject_UserService.domain.FavItems;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserAlreadyExist;
import com_selfProject_UserService.exception.UserNotFoundException;

public interface IUserService {
  public User addUser(User user) throws UserAlreadyExist;
  public User updateUser(String email,User user) throws UserNotFoundException;
  public User addItemInList(String email, FavItems favItems);
  public boolean itemExist(String email,int itemId);
  public void removeItemFromFav(String email,int itemId);
  public byte[] getUserImage(String email)throws UserNotFoundException;
  public User getUserName(String email)throws UserNotFoundException;
}
