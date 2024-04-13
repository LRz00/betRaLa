/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.exceptions.NotValidCredentialsException;
import br.com.lrz.betRaLa.exceptions.UserInsufficientBalanceException;
import br.com.lrz.betRaLa.exceptions.UserNotFoundException;
import br.com.lrz.betRaLa.models.DTO.CreateUserDTO;
import br.com.lrz.betRaLa.models.UserRoles;
import br.com.lrz.betRaLa.models.Users;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.lrz.betRaLa.repositories.UserRepository;
import java.util.Objects;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author lara
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public Users create(CreateUserDTO user) {        
        //to do: password encryption
        Users newUser = new Users();
        
        if (userRepo.existsByEmail(user.email())) {
            throw new NotValidCredentialsException("Email already exists");
        }

        if (userRepo.existsByCpf(user.cpf())) {
            throw new NotValidCredentialsException("CPF already exists");
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(user.password());
        // Additional checks or validations can be added here
        newUser.setId(null);
        newUser.setPassword(encryptedPassword);
        newUser.setBalance(user.balance());
        newUser.setCpf(user.cpf());
        newUser.setEmail(user.email());
        newUser.setName(user.name());
        newUser.setRole(UserRoles.USER); //todo usuario criado é um 
        userRepo.save(newUser);

        return newUser;
    }

    public List<Users> getAll() {
        return userRepo.findAll();
    }


    public void delete(Users user) {
        Float currentSaldo = user.getBalance();

        if (currentSaldo < 0) {
            throw new UserInsufficientBalanceException("User has pending debt and cannot be deleted");
        } else if (currentSaldo > 0) {
            throw new UserInsufficientBalanceException("User has a positive balance, withdrawal required prior to deletion.");
        } else {
            this.userRepo.delete(user);
        }

    }


    public Users findByCpf(Long cpf) {
        return this.userRepo.findByCpf(cpf);
    }


    public void updateBalance(Float value, Long cpf) {

        Users user = findByCpf(cpf);

        Float newBalance = user.getBalance() + value;
        
         // Check if it's a withdrawal and if there is enough money in the account
        if (newBalance < 0) {
            throw new UserInsufficientBalanceException("Account does not have requested withdraw value");
        } else {
            user.setBalance(newBalance);
        }

        // Update the user object in the database
        update(user);
    }

    public void update(Users user) {        
        Users updateUser = this.getUser(user.getId());
        
        if(!Objects.equals(updateUser.getBalance(), user.getBalance())){
            updateUser.setBalance(user.getBalance());
        }
        if(!updateUser.getPassword().equals(user.getPassword())){
            updateUser.setPassword(user.getPassword());
        }
        if(!updateUser.getCpf().equals(user.getCpf())){
            throw new NotValidCredentialsException("USER CPF CANNOT BE CHANGED");
        }
        this.userRepo.save(updateUser);
    }
    
    public Users getUser(Long id){
        return this.userRepo.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

}
