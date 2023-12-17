package com_selfProject_UserService.service;

import com_selfProject_UserService.domain.FavItems;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserAlreadyExist;
import com_selfProject_UserService.exception.UserNotFoundException;
import com_selfProject_UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    UserRepo userRepo;
    @Override
    public User addUser(User user) throws UserAlreadyExist {
        if(userRepo.existsById(user.getUserEmail())){
            System.out.println("User already exist!!"); // replaced by exception
            throw new UserAlreadyExist();
        }
        return userRepo.save(user);
    }

    @Override
    public User updateUser(String email, User user) throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("user not found");
            throw new UserNotFoundException();
        }
        User existingUser = userRepo.findById(email).get();

        if(user.getUserImage() != null){
            existingUser.setUserImage(user.getUserImage());
            existingUser.setImageName(user.getImageName());
        }
        if(user.getUserName() != null){
            existingUser.setUserName(user.getUserName());
        }
        if(user.getPhoneNo() != 0){
            existingUser.setPhoneNo(user.getPhoneNo());
        }
        return userRepo.save(existingUser);
    }

    @Override
    public User addItemInList(String email, FavItems favItems) {
        User existingUser = userRepo.findById(email).get();
        boolean userExist = false;
        if(existingUser.getFavItems() != null){
            for(FavItems items : existingUser.getFavItems()){
                if(items.getItemId()==favItems.getItemId()){
                    userExist = true;
                    break;
                }
            }
        }
        if(!userExist){
            if (existingUser.getFavItems()!=null){
                existingUser.setFavItems(new ArrayList<>());
            }
            existingUser.getFavItems().add(favItems);
        }
        userRepo.save(existingUser);
        return existingUser;
    }

    @Override
    public boolean itemExist(String email, int itemId) {
        List<FavItems> favList = userRepo.findById(email).get().getFavItems();
        for(FavItems items : favList){
            if(items.getItemId()==itemId){
                return true;
            }
        }
        return false;
    }

    @Override
    public void removeItemFromFav(String email, int itemId) {
        User user = userRepo.findById(email).get();
      List<FavItems> itemList = userRepo.findById(email).get().getFavItems();
      for(FavItems favItems : itemList){
          if(favItems.getItemId()==itemId){
              itemList.remove(favItems);
              System.out.println("Item Removed Successfully..!");
              userRepo.save(user);
              break;
          }
      }
    }

    @Override
    public byte[] getUserImage(String email)throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("User not exist"); // user defined exception
            throw new UserNotFoundException();
        }
        User user = userRepo.findById(email).get();
        return user.getUserImage();
    }

    @Override
    public User getUserName(String email)throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("User not exist"); // user defined exception
            throw new UserNotFoundException();
        }
        return userRepo.findById(email).get();
    }
}
