package com_selfProject_UserService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.NOT_FOUND,reason = "User not found in the data base")
public class UserNotFoundException extends Exception{
}
