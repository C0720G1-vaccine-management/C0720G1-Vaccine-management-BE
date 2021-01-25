package com.project.service;

import com.project.entity.Role;

import java.util.List;

public interface RoleService {
 List<String> getAllRoles(int id);

 void setDefaultRole(int accountId, Integer roleId);


}
