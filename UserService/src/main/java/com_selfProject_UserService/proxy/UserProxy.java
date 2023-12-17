package com_selfProject_UserService.proxy;


import com_selfProject_UserService.domain.User;
import com_selfProject_UserService.domain.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "authService",url = "http://localhost:8900")
public interface UserProxy {
    @PostMapping("api/vi/authService/addUser")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto);

    @PostMapping("api/vi/authService/user/login")
    public ResponseEntity<?> loginUser(@RequestBody User user);
}
