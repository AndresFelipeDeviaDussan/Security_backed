package com.misionTIC2022_grupo11.security_backend.service;

import com.misionTIC2022_grupo11.security_backend.models.Rol;
import com.misionTIC2022_grupo11.security_backend.repositories.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    /**
     * Using the list call all the BD of Rol
     * @return BD of Rol
     */
    public List<Rol> index(){
        return (List<Rol>)this.rolRepository.findAll();

    }

    /**
     * Using the list call specific information of BD
     * @param id you need this param for call the information
     * @return specific information of a one Rol
     */
    public Optional<Rol> show(int id){
        return this.rolRepository.findById(id);

    }

    /**
     * In this command you can create new User id with mandatory information
     * @param newRol here we establish the mandatory information, it is where we will store it
     * @return new information to the BD
     */
    public Rol create(Rol newRol){
        if (newRol.getId() == null) {
            if (newRol.getName() != null)
                return this.rolRepository.save(newRol);
            else {
                //TODO 400 BadRequest
                return newRol;
            }
        }
        else {
            //TODO validate if id exist, 400 BadRequest
            return newRol;
        }

    }

    /**
     * In this command you can update/change specific information of Rol in the BD
     * @param id you need this param for search the information
     * @param updateRol in this parameter we save the information and validate that said content can be updated
     * @return update information of specific Rol
     */
    public Rol update(int id, Rol updateRol){
        if (id > 0){
            Optional<Rol> tempRol = this.show(id);
            if (tempRol.isPresent()){
                if (updateRol.getName() != null)
                    tempRol.get().setName(updateRol.getName());
                if (updateRol.getDescription() != null)
                    tempRol.get().setDescription(updateRol.getDescription());
                return this.rolRepository.save(tempRol.get());
            }
            else{
                //TODO 400 NotFound
                return updateRol;
            }
        }
        else {
            //TODO 400 BadRequest id <= 0
            return updateRol;
        }
    }

    /**
     * In this command use a Boolean to perform a mapping in the BD for search and validate specific information to Rol
     * for delete this
     * @param id you need this param for search in the mapping
     * @return The information delete
     */
    public boolean delete(int id){
        Boolean success = this.show(id).map(rol -> {
            this.rolRepository.delete(rol);
            return true;
        }).orElse(false);
        return success;

    }
}


