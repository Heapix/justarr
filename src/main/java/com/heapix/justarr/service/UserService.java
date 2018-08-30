package com.heapix.justarr.service;

import com.heapix.justarr.controller.bo.CreateResponseBo;
import com.heapix.justarr.controller.bo.DeleteResponseBo;
import com.heapix.justarr.controller.bo.UserAdminBo;
import com.heapix.justarr.controller.dto.ChangePasswordDto;
import com.heapix.justarr.controller.dto.RegistrationDto;
import com.heapix.justarr.controller.dto.UserUpdateDto;
import com.heapix.justarr.persistence.model.User;

import java.util.List;

/**
 * @author mgergalov
 */
public interface UserService {

    CreateResponseBo addUser(RegistrationDto user, Long role) throws Exception;
    List<User> getAll();
    UserAdminBo findUser(Long id);
    UserAdminBo findUser(String email);
    void update(UserUpdateDto userUpdateDto, Long userId, Long role);
    DeleteResponseBo delete(Long id);
    DeleteResponseBo blockUser(Long id);
    List<UserAdminBo> getUnregisteredUsers();
    List<UserAdminBo> getRegisteredUsers();
    User saveUserAdditionalInfo(String info, Long id);
    void allowRegistration(Long userId);
    User changePassword(ChangePasswordDto password, Long userId) throws Exception;

}
