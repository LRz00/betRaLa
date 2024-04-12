/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.controllers;

import br.com.lrz.betRaLa.models.Bet;
import br.com.lrz.betRaLa.models.DTO.BetDetailsDTO;
import br.com.lrz.betRaLa.services.BetService;
import br.com.lrz.betRaLa.services.GameService;
import br.com.lrz.betRaLa.services.UserService;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author lara
 */

@RestController
@RequestMapping("/bets")
public class BetController {
    @Autowired
    BetService betService;
    
    @PostMapping
    public ResponseEntity<Void> createBet(@RequestBody BetDetailsDTO betDetails){
      Bet newBet = this.betService.createNewBet(betDetails.getMatchId(), betDetails.getUserId(),betDetails.getWinner(), betDetails.getAmount());
      
      URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newBet.getId()).toUri();
      return ResponseEntity.created(uri).build();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bet> getBet(Long id){
        Bet bet = this.betService.getBet(id);
        return ResponseEntity.ok().body(bet);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancelBet(Long id){
        Bet bet = this.betService.getBet(id);
        this.betService.cancelBet(bet);
        return ResponseEntity.noContent().build();
    }
}
