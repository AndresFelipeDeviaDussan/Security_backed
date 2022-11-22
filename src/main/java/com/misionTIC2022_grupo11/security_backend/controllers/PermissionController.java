package com.misionTIC2022_grupo11.security_backend.controllers;

import com.misionTIC2022_grupo11.security_backend.models.Permission;
import com.misionTIC2022_grupo11.security_backend.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/all")
    public List<Permission> getAllPermissions() {
        return this.permissionService.index();
    }

    @GetMapping("/{id}")
    public Optional<Permission> getPermissionById(@PathVariable("id") int id){
        return this.permissionService.show(id);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission insertPermission(@RequestBody Permission permission){
        return this.permissionService.create(permission);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Permission updatePermission(@PathVariable("id") int id,@RequestBody Permission permission){
        return this.permissionService.update(id, permission);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deletePermission(@PathVariable("id") int id) {
        return this.permissionService.delete(id);
    }
}
