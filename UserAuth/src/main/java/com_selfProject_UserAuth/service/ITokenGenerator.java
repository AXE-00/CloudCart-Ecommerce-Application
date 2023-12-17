package com_selfProject_UserAuth.service;

import com_selfProject_UserAuth.model.User;

import java.util.Map;

public interface ITokenGenerator {
    Map<String,String>storeToken(User user);
}
