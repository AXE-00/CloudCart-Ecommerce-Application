package com_selfProject_UserService.controller;


import com_selfProject_UserService.domain.FavItems;
import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.exception.UserAlreadyExist;
import com_selfProject_UserService.exception.UserNotFoundException;
import com_selfProject_UserService.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/userService")
public class UserController {

    private UserService userService;
    @Autowired

    public UserController(UserService iUserService) {
        this.userService = iUserService;
    }

    @PostMapping("/register/user")
    public ResponseEntity<?> addUser(@RequestParam("file") MultipartFile file,@RequestParam("userData")String user) throws IOException, UserAlreadyExist {
        System.out.println("request reached to register");
        User user1 = new ObjectMapper().readValue(user, User.class);
        user1.setUserImage(file.getBytes());
        System.out.println(user);
        String fileName = file.getOriginalFilename();
        String newFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
        user1.setImageName(newFileName);
        System.out.println(newFileName);
           return new ResponseEntity<>(userService.addUser(user1), HttpStatus.CREATED);
    }


    @PutMapping("/update/user")
    public ResponseEntity<?> updateUser(HttpServletRequest request,@RequestParam(value = "file",required = false) MultipartFile file,@RequestParam("userData")String user) throws IOException, UserNotFoundException {
        String email = (String)request.getAttribute("attr1"); //attr1=email
        User user1 = new ObjectMapper().readValue(user,User.class);
        System.out.println(email);
        System.out.println(user1);
        if(file!=null && !file.isEmpty()){
            user1.setUserImage(file.getBytes());
            String fileName = file.getOriginalFilename();
            String newFileName = FilenameUtils.getBaseName(fileName)+"_"+System.currentTimeMillis()+"."+FilenameUtils.getExtension(fileName);
            user1.setImageName(newFileName);
        }
        return new ResponseEntity<>(userService.updateUser(email,user1),HttpStatus.OK);
    }

    @PostMapping("/addFav/Item")
    public ResponseEntity<?>addFavItem(HttpServletRequest request, FavItems favItems){
        String email = (String)request.getAttribute("attr1");
        if(email.isEmpty()){
            System.out.println("Email is empty");
            return new ResponseEntity<>("No Value we are getting",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(userService.addItemInList(email,favItems),HttpStatus.OK);
        }
    }

    @GetMapping("/item/check")
    public ResponseEntity<?>checkItemExist(HttpServletRequest servletRequest,@RequestParam int itemId){
        String email = (String)servletRequest.getAttribute("attr1");
        if(email.isEmpty()){
            return new ResponseEntity<>("Email is empty, check it again..!",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(userService.itemExist(email,itemId),HttpStatus.OK);
        }
    }

    @DeleteMapping("/remove/Item")
    public ResponseEntity<?> removeItem(HttpServletRequest request, @RequestParam int itemId){
        String email = (String)request.getAttribute("attr1");
        if(email.isEmpty()){
            return new ResponseEntity<>("check your email again..!",HttpStatus.BAD_REQUEST);
        }
        else {
            userService.removeItemFromFav(email,itemId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @GetMapping("/get/userImage")
    public ResponseEntity<?> getUserImage(HttpServletRequest request) throws UserNotFoundException {
        String email = (String)request.getAttribute("attr1");
        byte[] imageData = userService.getUserImage(email);
        if (imageData==null || email==null || email.isEmpty()){
            return new ResponseEntity<>("check your email again..!",HttpStatus.NOT_FOUND);
        }
        String base64Image = Base64.getEncoder().encodeToString(imageData);
        Map<String,Object> response = new HashMap<>();
        response.put("imageData",base64Image);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/get/userName")
    public ResponseEntity<?> getUserName(HttpServletRequest request) throws UserNotFoundException {
        String email = (String)request.getAttribute("attr1");
        if(email.isEmpty()){
            return new ResponseEntity<>("check your email again..!",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(userService.getUserName(email),HttpStatus.OK);
        }
    }

    @GetMapping("/get/userData")
    public ResponseEntity<?> getUserData(HttpServletRequest request) throws UserNotFoundException {
        String email = (String)request.getAttribute("attr1");
        if(email.isEmpty()){
            return new ResponseEntity<>("check your email again..!",HttpStatus.BAD_REQUEST);
        }
        else {
            return new ResponseEntity<>(userService.getUserData(email),HttpStatus.OK);
        }
    }
}
