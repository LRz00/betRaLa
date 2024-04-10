/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.exceptions.CreateMatchException;
import br.com.lrz.betRaLa.exceptions.MatchNotFoundException;
import br.com.lrz.betRaLa.exceptions.SetMatchResultException;
import br.com.lrz.betRaLa.models.Game;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.lrz.betRaLa.repositories.GameRepository;

/**
 *
 * @author lara
 */
@Service
public class GameService{
    @Autowired
    private GameRepository matchRepo;

    
    public Game createMatch(Game game) {
       try{
           matchRepo.save(game);
       }catch(Exception e){
           throw new CreateMatchException("Erro creating new match");
       }
       
       return game;
    }
    


    public Game definirResultado(Long jogoId, String resultado, String winner) {
       try{
           Game match = this.getMatch(jogoId);
           
           match.setScore(resultado);
           if(winner.equals(match.getTeamA()) || winner.equals(match.getTeamB())){
               match.setWinner(winner);
           }else{
               throw new SetMatchResultException("Error defining match result");
           }
           
       }catch(Exception e){
           throw new SetMatchResultException("Error definint match result");
       }
       
       return this.matchRepo.findById(jogoId).orElseThrow(() -> new SetMatchResultException("Error defining match result"));
    }
    
    public boolean isMatchOver(Long matchId){
        Game match = this.getMatch(matchId);
        if(match.getScore().isEmpty()){
            return false;
        }
        return true;
    }

    
    public Game getMatch(Long id){
        return this.matchRepo.findById(id).orElseThrow(() -> new MatchNotFoundException("No match find with this ID"));
    }
    
}