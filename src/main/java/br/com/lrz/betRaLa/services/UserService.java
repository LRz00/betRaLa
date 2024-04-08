/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.lrz.betRaLa.repositories.UserRepository;

/**
 *
 * @author lara
 */
@Service
public class UserService {
    //@Autowired
    //private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    public User create(User usuario) {
        //to do: password encryption
        // Check if email and cpf are unique before creating a new user
        if (userRepo.existsByEmail(usuario.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        if (userRepo.existsByCpf(usuario.getCpf())) {
            throw new RuntimeException("CPF already exists");
        }

        // Additional checks or validations can be added here
        usuario.setId(null);
        usuario.setSenha(usuario.getSenha());
        usuario = userRepo.save(usuario);

        return usuario;
    }

    public List<User> getAll() {
        return userRepo.findAll();
    }


    public void delete(User user) {
        Float currentSaldo = user.getSaldo();

        if (currentSaldo < 0) {
            throw new RuntimeException("User has pending debt and cannot be deleted");
        } else if (currentSaldo > 0) {
            throw new RuntimeException("User has a positive balance, withdrawal required prior to deletion.");
        } else {
            this.userRepo.delete(user);
        }

    }


    public User findByCpf(Long cpf) {
        return this.userRepo.findByCpf(cpf);
    }


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

    public void update(User user) {
        // Additional checks or validations before updating, if needed
        this.userRepo.save(user);
    }
    
    public User getUser(Long id){
        return this.userRepo.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
