/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.IDaoUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class ServiceUser implements IServiceUser{
    @Autowired
    private IDaoUser daoUser;

    @Override
    public void saveUser(User usuario) {
        daoUser.save(usuario);
    }
    
    @Override 
    public List<User> getAllUsers(){
       return daoUser.findAll();
    }
    
}
