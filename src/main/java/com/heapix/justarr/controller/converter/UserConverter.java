package com.heapix.justarr.controller.converter;

import com.heapix.justarr.controller.bo.UserAdminBo;
import com.heapix.justarr.persistence.model.User;

import java.util.List;

/**
 * @author mgergalov
 */
public interface UserConverter {

    UserAdminBo toUserAdminBo(User user);
    List<UserAdminBo> toUserAdminBos(List<User> users);

}
