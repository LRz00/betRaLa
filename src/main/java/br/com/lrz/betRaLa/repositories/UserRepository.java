/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.lrz.betRaLa.models.Users;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
/**
 *
 * @author lara
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{
    public Users findByCpf(Long cpf);
    boolean existsByEmail(String email);
    boolean existsByCpf(Long cpf);
    UserDetails findByEmail(String email);
}
