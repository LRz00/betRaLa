/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lrz.betRaLa.models.User;
import java.util.Optional;
import org.springframework.stereotype.Repository;
/**
 *
 * @author lara
 */
@Repository
public interface IDaoUser extends JpaRepository<User, Long>{
    public Optional<User> findByCpf(Long cpf);
}
