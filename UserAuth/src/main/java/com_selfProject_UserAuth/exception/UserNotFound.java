package com_selfProject_UserAuth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code =HttpStatus.NOT_FOUND ,reason = "User Not Found")
public class UserNotFound extends Exception{
}
