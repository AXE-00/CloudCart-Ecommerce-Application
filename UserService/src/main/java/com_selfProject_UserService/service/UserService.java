package com_selfProject_UserService.service;

import com_selfProject_UserService.domain.FavItems;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.domain.UserDto;
import com_selfProject_UserService.exception.UserAlreadyExist;
import com_selfProject_UserService.exception.UserNotFoundException;
import com_selfProject_UserService.proxy.UserProxy;
import com_selfProject_UserService.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService{

    private final UserRepo userRepo;
    private UserProxy userProxy;

    @Autowired
    public UserService(UserRepo userRepo, UserProxy userProxy) {
        this.userRepo = userRepo;
        this.userProxy = userProxy;
    }

    @Override
    public User addUser(User user) throws UserAlreadyExist {
        System.out.println("add user in service");
        if(userRepo.existsById(user.getUserEmail())){
            System.out.println("User already exist!!"); // replaced by exception
            throw new UserAlreadyExist();
        }
        UserDto userDto = new UserDto();
        userDto.setUserEmail(user.getUserEmail());
        userDto.setPassword(user.getPassword());
        userDto.setUserName(user.getUserName());
        userDto.setPhoneNo(user.getPhoneNo());
        userDto.setImageName(user.getImageName());
        userDto.setRole(user.getRole());

        System.out.println(userDto);

        userProxy.registerUser(userDto);
        return userRepo.save(user);
    }

    @Override
    public User updateUser(String email, User user) throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("user not found");
            throw new UserNotFoundException();
        }
        User existingUser = userRepo.findById(email).get();
        UserDto userDto = new UserDto();

        if(user.getUserImage() != null){
            existingUser.setUserImage(user.getUserImage());
            existingUser.setImageName(user.getImageName());
            userDto.setImageName(user.getImageName());
        }
        if(user.getUserName() != null){
            existingUser.setUserName(user.getUserName());
            userDto.setUserName(user.getUserName());
        }
        if(user.getPhoneNo() != 0){
            existingUser.setPhoneNo(user.getPhoneNo());
            userDto.setPhoneNo(user.getPhoneNo());
        }
        userProxy.updateUser(userDto,email);
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
        if(userRepo.findById(email).isPresent()){
            List<FavItems> favList = userRepo.findById(email).get().getFavItems();
            for(FavItems items : favList){
                if(items.getItemId()==itemId){
                    return true;
                }
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
    public String getUserName(String email)throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("User not exist"); // user defined exception
            throw new UserNotFoundException();
        }
        User user = userRepo.findById(email).get();
        return user.getUserName();
    }

    @Override
    public User getUserData(String email)throws UserNotFoundException {
        if(userRepo.findById(email).isEmpty()){
            System.out.println("User not exist"); // user defined exception
            throw new UserNotFoundException();
        }
        User user = userRepo.findById(email).get();
        return user;
    }
}
