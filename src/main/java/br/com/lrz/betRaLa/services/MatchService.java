/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.exceptions.CreateMatchException;
import br.com.lrz.betRaLa.exceptions.MatchNotFoundException;
import br.com.lrz.betRaLa.exceptions.SetMatchResultException;
import br.com.lrz.betRaLa.models.Match;
import br.com.lrz.betRaLa.repositories.MatchRepository;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lara
 */
public class MatchService{
    @Autowired
    private MatchRepository matchRepo;

    
    public Match createMatch(Match game) {
       try{
           matchRepo.save(game);
       }catch(Exception e){
           throw new CreateMatchException("Erro creating new match");
       }
       
       return game;
    }
    


    public Match definirResultado(Long jogoId, String resultado, String winner) {
       try{
           Match match = this.getMatch(jogoId);
           
           match.setResult(resultado);
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
        Match match = this.getMatch(matchId);
        if(match.getResult().isEmpty()){
            return false;
        }
        return true;
    }

    
    public Match getMatch(Long id){
        return this.matchRepo.findById(id).orElseThrow(() -> new MatchNotFoundException("No match find with this ID"));
    }
    
}