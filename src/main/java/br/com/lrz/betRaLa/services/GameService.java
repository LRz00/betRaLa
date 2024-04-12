/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.exceptions.CreateGameException;
import br.com.lrz.betRaLa.exceptions.GameNotFoundException;
import br.com.lrz.betRaLa.exceptions.SetGameScoreException;
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
    private GameRepository gameRepo;
    
    public Game createGame(Game game) {
       try{
           gameRepo.save(game);
       }catch(Exception e){
           throw new CreateGameException("Erro creating new match");
       }
       
       return game;
    }
    


    public Game setGameScore(Long gameId, String score, String winner) {
       Game game = this.getGame(gameId); 
        
       try{
           game.setScore(score);
           if(winner.equals(game.getTeamA()) || winner.equals(game.getTeamB())){
               game.setWinner(winner);
           }else{
               throw new SetGameScoreException("Error defining game score");
           }
           
       }catch(Exception e){
           throw new SetGameScoreException("Error definint game score");
       }
       this.gameRepo.save(game);
       return this.gameRepo.findById(gameId).orElseThrow(() -> new SetGameScoreException("Error defining score"));
    }
    
    public boolean isGameOver(Long gameId){
        Game game = this.getGame(gameId);
        if(game.getScore() == null){
            return false;
        }
        return true;
    }

    
    public Game getGame(Long id){
        return this.gameRepo.findById(id).orElseThrow(() -> new GameNotFoundException("No game find with this ID"));
    }
    
}