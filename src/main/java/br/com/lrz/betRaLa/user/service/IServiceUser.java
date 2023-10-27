/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.user.service;

import br.com.lrz.betRaLa.user.model.User;
import java.util.List;

/**
 *
 * @author lara
 */
public interface IServiceUser {
    public abstract void saveUser(User usuario);
    public abstract List<User> getAllUsers();
}
