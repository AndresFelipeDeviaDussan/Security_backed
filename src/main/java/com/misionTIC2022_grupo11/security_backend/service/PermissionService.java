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
    public List<Permission> index(){
        return (List<Permission>)this.permissionRepository.findAll();

    }

    public Optional<Permission> show(int id){
        return this.permissionRepository.findById(id);

    }

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

    public boolean delete(int id){
        Boolean success = this.show(id).map(permission -> {
            this.permissionRepository.delete(permission);
            return true;
        }).orElse(false);
        return success;

    }
}
