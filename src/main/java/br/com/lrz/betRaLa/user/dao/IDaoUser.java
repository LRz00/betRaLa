/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.user.dao;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lrz.betRaLa.user.model.User;
/**
 *
 * @author lara
 */
public interface IDaoUser extends JpaRepository<User, Long>{
    
}
