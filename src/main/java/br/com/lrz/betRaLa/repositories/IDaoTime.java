/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.repositories;

import br.com.lrz.betRaLa.models.Time;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lara
 */
@Repository
public interface IDaoTime extends JpaRepository<Time, Long>{
    
    public boolean existsByNome(String nome);
    
    public Optional<Time> findById(Long id);
    public Time findByNome(String nome);
    
}
