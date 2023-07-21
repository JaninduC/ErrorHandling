package com.example.shop.service;

import com.example.shop.model.UserModel;

public interface UserService extends SuperService<UserModel> {

    public boolean changePassword(int userId, String oldPassword, String newPassword) throws Exception;


}
