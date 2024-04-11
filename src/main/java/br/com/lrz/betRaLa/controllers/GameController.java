/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.controllers;

import br.com.lrz.betRaLa.models.Game;
import br.com.lrz.betRaLa.services.BetService;
import br.com.lrz.betRaLa.services.GameService;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import br.com.lrz.betRaLa.models.DTO.EndGameDTO;

/**
 *
 * @author lara
 */
@RequestMapping("/game")
@RestController
public class GameController {
    @Autowired
    GameService gameService;
    @Autowired 
    BetService betService;
    /*
    Creates a new match
    */
    @PostMapping
    public ResponseEntity<Void> newGame(@Valid @RequestBody Game game){
       Game newGame = this.gameService.createMatch(game);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newGame.getId()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    /*
    Returns a match by id
    */
    @GetMapping("/{id}")
    public ResponseEntity findGameById(@PathVariable Long id){
        Game game = this.gameService.getMatch(id);
        
        return ResponseEntity.ok().body(game);
    }
    
    @PutMapping("/endGame")
    public ResponseEntity<Void> addWinner(@RequestBody EndGameDTO game){
       this.gameService.definirResultado(game.getId(), game.getScore(), game.getWinner());
       this.betService.settleAllBets(this.gameService.getMatch(game.getId()));
       return ResponseEntity.noContent().build();
    }
}
