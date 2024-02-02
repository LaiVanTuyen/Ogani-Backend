package com.vn.OganiBE.service;


import com.vn.OganiBE.entity.User;
import com.vn.OganiBE.model.request.ChangePasswordRequest;
import com.vn.OganiBE.model.request.CreateUserRequest;
import com.vn.OganiBE.model.request.UpdateProfileRequest;

public interface UserService {
    
    void register(CreateUserRequest request);


    User getUserByUsername(String username);

    User updateUser(UpdateProfileRequest request);

    void changePassword(ChangePasswordRequest request);

}
