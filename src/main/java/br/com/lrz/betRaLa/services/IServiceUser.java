/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lara
 */
public interface IServiceUser {
    public abstract User create(User usuario);
    public abstract List<User> getAll();
    public abstract void delete (User user);
    public abstract Optional<User> findByCpf(Long cpf);
}
