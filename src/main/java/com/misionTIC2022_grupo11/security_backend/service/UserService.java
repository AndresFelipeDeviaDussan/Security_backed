package com.misionTIC2022_grupo11.security_backend.service;

import com.misionTIC2022_grupo11.security_backend.models.User;
import com.misionTIC2022_grupo11.security_backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    @Autowired
    private UserRepository userRepository;  // If it starts with lowercase it is the object variable

    /**
     * Using the list call all the BD of User
     * @return BD of user
     */
    public List<User> index() {
        return (List<User>) this.userRepository.findAll();

    }

    /**
     * Using the list call specific information of BD
     * @param id you need this param for call the information
     * @return specific information of a one User
     */
    public Optional<User> show(int id) {
        return this.userRepository.findById(id);

    }

    /**
     * In this command you can create new User id with mandatory information
     * @param newUser here we establish the mandatory information, it is where we will store it
     * @return new information to the BD
     */
    public User create(User newUser) {
        if (newUser.getId() == null) {
            if (newUser.getEmail() != null && newUser.getNick_name() != null && newUser.getPassword() != null){
                newUser.setPassword(this.convertToSHA256(newUser.getPassword()));
                return this.userRepository.save(newUser);
            }
            else {
                //TODO 400 BabRequest
                return newUser;
            }
        } else {
            //TODO validate if id exist, 400 BadRequest
            return newUser;

        }

    }

    /**
     * In this command you can update/change specific information of user in the BD
     * @param id you need this param for search the information
     * @param updateUser in this parameter we save the information and validate that said content can be updated
     * @return update information of specific user
     */
    public User update(int id, User updateUser) {
        if (id > 0) {
            Optional<User> temUser = this.show(id);
            if (temUser.isPresent()) {
                if (updateUser.getNick_name() != null)
                    temUser.get().setNick_name(updateUser.getNick_name());
                if (updateUser.getPassword() != null)
                    temUser.get().setPassword(this.convertToSHA256(updateUser.getPassword()));
                return this.userRepository.save(temUser.get());
            } else {
                //TODO 400 NotFound
                return updateUser;
            }
        }else{
            //TODO 400 BadRequest id <= 0
            return updateUser;

    }

}
    /**
     * In this command use a Boolean to perform a mapping in the BD for search and validate specific information to User
     * for delete this
     * @param id you need this param for search in the mapping
     * @return The information delete
     */

    public boolean delete(int id){
        Boolean success = this.show(id).map(user -> {
            this.userRepository.delete(user);
            return true;
        }).orElse(false);
        return success;

    }

    /**
     * Classical encryption algorithm
     * @param password
     * @return
     */
    public String convertToSHA256(String password){
        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("SHA256");
        }
        catch (NoSuchAlgorithmException e){
            e.printStackTrace();
            return null;
        }
        byte[] hash = md.digest(password.getBytes());
        StringBuffer sb = new StringBuffer();
        for(byte b:hash)
            sb.append( String.format("%02x", b));
        return sb.toString();
    }
}
