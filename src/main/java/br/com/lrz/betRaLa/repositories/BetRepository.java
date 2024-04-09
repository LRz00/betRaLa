/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.repositories;

import br.com.lrz.betRaLa.models.Bet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lara
 */
@Repository
public interface BetRepository extends JpaRepository<Bet, Long> {
    public List<Bet> findByMatchId(Long matchId);
}
