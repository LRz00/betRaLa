/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.IDaoUser;
import java.util.List;
import java.util.Optional;
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
    public User create(User usuario) {
        User user = daoUser.save(usuario);
        return user;
    }
    
    @Override
    public List<User> getAll() {
        return daoUser.findAll();
    }

    @Override
    public void delete(User user) {
        this.daoUser.delete(user);
    }

    @Override
    public Optional<User> findByCpf(Long cpf) {
        return this.daoUser.findByCpf(cpf);
    }
    
}
