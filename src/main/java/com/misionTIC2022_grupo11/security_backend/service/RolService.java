package com.misionTIC2022_grupo11.security_backend.service;

import com.misionTIC2022_grupo11.security_backend.models.Permission;
import com.misionTIC2022_grupo11.security_backend.models.Rol;
import com.misionTIC2022_grupo11.security_backend.repositories.PermissionRepository;
import com.misionTIC2022_grupo11.security_backend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;
    @Autowired
    private PermissionRepository permissionRepository;

    /**
     * Using the list call all the BD of Rol
     *
     * @return BD of Rol
     */
    public List<Rol> index() {
        return (List<Rol>) this.rolRepository.findAll();

    }

    /**
     * Using the list call specific information of BD
     *
     * @param id you need this param for call the information
     * @return specific information of a one Rol
     */
    public Optional<Rol> show(int id) {
        return this.rolRepository.findById(id);

    }

    /**
     * In this command you can create new User id with mandatory information
     *
     * @param newRol here we establish the mandatory information, it is where we will store it
     * @return new information to the BD
     */
    public Rol create(Rol newRol) {
        if (newRol.getIdRol() == null) {
            if (newRol.getName() != null)
                return this.rolRepository.save(newRol);
            else {
                //TODO 400 BadRequest
                return newRol;
            }
        } else {
            //TODO validate if id exist, 400 BadRequest
            return newRol;
        }

    }

    /**
     * In this command you can update/change specific information of Rol in the BD
     *
     * @param id        you need this param for search the information
     * @param updateRol in this parameter we save the information and validate that said content can be updated
     * @return update information of specific Rol
     */
    public Rol update(int id, Rol updateRol) {
        if (id > 0) {
            Optional<Rol> tempRol = this.show(id);
            if (tempRol.isPresent()) {
                if (updateRol.getName() != null)
                    tempRol.get().setName(updateRol.getName());
                if (updateRol.getDescription() != null)
                    tempRol.get().setDescription(updateRol.getDescription());
                return this.rolRepository.save(tempRol.get());
            } else {
                //TODO 400 NotFound
                return updateRol;
            }
        } else {
            //TODO 400 BadRequest id <= 0
            return updateRol;
        }
    }

    /**
     * In this command use a Boolean to perform a mapping in the BD for search and validate specific information to Rol
     * for delete this
     *
     * @param id you need this param for search in the mapping
     * @return The information delete
     */
    public boolean delete(int id) {
        Boolean success = this.show(id).map(rol -> {
            this.rolRepository.delete(rol);
            return true;
        }).orElse(false);
        return success;

    }

    /**
     * Command in charge of receiving, validating and verifying the permissions in order to link them to a respective
     * role
     *
     * @param idRol
     * @param idPermission
     * @return
     */
    public ResponseEntity<Rol> updateAddPermission(int idRol, int idPermission) {
        Optional<Rol> rol = this.rolRepository.findById(idRol);
        if (rol.isPresent()) {
            Optional<Permission> permission = this.permissionRepository.findById(idPermission);
            if (permission.isPresent()) {
                Set<Permission> tempPermissions = rol.get().getPermissions();
                if (tempPermissions.contains(permission.get()))
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                            "Rol has yet the permission");
                else {
                    tempPermissions.add(permission.get());
                    rol.get().setPermissions(tempPermissions);
                    return new ResponseEntity<>(this.rolRepository.save(rol.get()), HttpStatus.CREATED);
                }
            } else
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Provide permission.id does not exist in the DB");
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Provide rol.id does not exist in the DB");

    }

    /**
     * Command in charge of receiving, validating and verifying the permissions in order to link them to a respective
     * role, verifying that they are not repeated and are not the same
     * @param idRol
     * @param permission
     * @return
     */
    public ResponseEntity<Boolean> validateGrant(int idRol, Permission permission) {
        boolean isGrant = false;
        Optional<Rol> rol = this.rolRepository.findById(idRol);
        if (rol.isPresent()) {
            for (Permission rolPermission : rol.get().getPermissions())
                if (rolPermission.getUrl().equals(permission.getUrl()) &&
                        rolPermission.getMethod().equals(permission.getMethod())) {
                    isGrant = true;
                    break;
                }
            if (isGrant)
                return new ResponseEntity<>(true, HttpStatus.OK);
            else
                return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Provide rol.id does not exist in the DB");
        }
    }



