package com.misionTIC2022_grupo11.security_backend.service;

import com.misionTIC2022_grupo11.security_backend.models.Permission;
import com.misionTIC2022_grupo11.security_backend.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Using the list call all the BD of Permission
     * @return BD of Permissions
     */
    public List<Permission> index(){
        return (List<Permission>)this.permissionRepository.findAll();

    }

    /**
     * Using the list call specific information of BD
     * @param id you need this param for call the information
     * @return specific information of a one Permission
     */

    public Optional<Permission> show(int id){
        return this.permissionRepository.findById(id);

    }

    /**
     * In this command you can create new User id with mandatory information
     * @param newPermission here we establish the mandatory information, it is where we will store it
     * @return new information to the BD
     */

    public Permission create(Permission newPermission) {
        if (newPermission.getId() == null) {
            if (newPermission.getMethod() != null && newPermission.getUrl() != null)
                return this.permissionRepository.save(newPermission);

            else {
                //TODO 400 BadRequest
                return newPermission;
            }
        } else {
            //TODO validate if id exist, 400 BadRequest
            return newPermission;
        }
    }

    /**
     * In this command you can update/change specific information of Rol in the BD
     * @param id you need this param for search the information
     * @param updatePermission in this parameter we save the information and validate that said content can be updated
     * @return update information of specific Permission
     */

    public Permission update(int id, Permission updatePermission){
        if (id > 0){
            Optional<Permission> tempPermission = this.show(id);
            if (tempPermission.isPresent()){
                if (updatePermission.getMethod() != null)
                    tempPermission.get().setMethod(updatePermission.getMethod());
                if (updatePermission.getUrl() != null)
                    tempPermission.get().setUrl(updatePermission.getUrl());
                return this.permissionRepository.save(tempPermission.get());
            }
            else {
                //TODO 400 NotFound
                return updatePermission;
        }
        }
        else {
            //TODO 400 BadRequest id <= 0
            return updatePermission;
        }
    }

    /**
     * In this command use a Boolean to perform a mapping in the BD for search and validate specific information to Rol
     * for delete this
     * @param id you need this param for search in the mapping
     * @return The information delete
     */

    public boolean delete(int id){
        Boolean success = this.show(id).map(permission -> {
            this.permissionRepository.delete(permission);
            return true;
        }).orElse(false);
        return success;

    }
}

