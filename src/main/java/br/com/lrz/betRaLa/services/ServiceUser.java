/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.IDaoUser;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class ServiceUser implements IServiceUser {
    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private IDaoUser daoUser;

    @Override
    public User create(User usuario) {
        //to do: password encryption
        // Check if email and cpf are unique before creating a new user
        if (daoUser.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (daoUser.existsByCpf(usuario.getCpf())) {
            throw new RuntimeException("CPF already exists");
        }

        // Additional checks or validations can be added here
        usuario.setId(null);
        usuario.setSenha(usuario.getSenha());
        usuario = daoUser.save(usuario);

        return usuario;
    }

    @Override
    public List<User> getAll() {
        return daoUser.findAll();
    }

    @Override
    public void delete(User user) {
        Float currentSaldo = user.getSaldo();

        if (currentSaldo < 0) {
            throw new RuntimeException("User has pending debt and cannot be deleted");
        } else if (currentSaldo > 0) {
            throw new RuntimeException("User has a positive balance, withdrawal required prior to deletion.");
        } else {
            this.daoUser.delete(user);
        }

    }

    @Override
    public User findByCpf(Long cpf) {
        return this.daoUser.findByCpf(cpf);
    }

    @Override
    public void updateSaldo(Float value, Long cpf) {

        User user = findByCpf(cpf);

        Float newSaldo = user.getSaldo() + value;
        
         // Check if it's a withdrawal and if there is enough money in the account
        if (newSaldo < 0) {
            throw new RuntimeException("Account does not have requested withdraw value");
        } else {
            user.setSaldo(newSaldo);
        }

        // Update the user object in the database
        update(user);
    }

    @Override
    public void update(User user) {
        // Additional checks or validations before updating, if needed
        this.daoUser.save(user);
    }

}
