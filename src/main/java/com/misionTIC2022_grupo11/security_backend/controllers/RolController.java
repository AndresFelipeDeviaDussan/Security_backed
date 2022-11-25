package com.misionTIC2022_grupo11.security_backend.controllers;


import com.misionTIC2022_grupo11.security_backend.models.Permission;
import com.misionTIC2022_grupo11.security_backend.models.Rol;
import com.misionTIC2022_grupo11.security_backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rol")
public class RolController {
    @Autowired
    private RolService rolService;

    @GetMapping("/all")
    public List<Rol> getAllRolls(){
        return this.rolService.index();
    }

    @GetMapping("/{id}")
    public Optional<Rol> getRolById(@PathVariable("id") int id){
        return this.rolService.show(id);
    }

    @GetMapping("/validate/{idRol}")
    public ResponseEntity<Boolean> validatePermission(@PathVariable("idRol") int idRol, @RequestBody Permission permission) {
        return this.rolService.validateGrant(idRol, permission);
    }

    @PostMapping("/insert")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol insertRol(@RequestBody Rol rol){
        return this.rolService.create(rol);
    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public Rol updateRol(@PathVariable("id") int id,@RequestBody Rol rol){
        return this.rolService.update(id, rol);
    }

    @PutMapping("/update/{idRol}/add_permission/{idPermission}")
    public ResponseEntity<Rol> updateAddPermission(@PathVariable("idRol") int idRol, @PathVariable("idPermission") int idPermission){
        return this.rolService.updateAddPermission(idRol, idPermission);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteRol(@PathVariable("id") int id){
        return this.rolService.delete(id);
    }
}
//HOla