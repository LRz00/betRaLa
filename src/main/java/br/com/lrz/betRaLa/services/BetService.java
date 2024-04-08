/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.exceptions.MatchOverException;
import br.com.lrz.betRaLa.exceptions.TeamNotPlayingException;
import br.com.lrz.betRaLa.exceptions.UserInsufficientBalanceException;
import br.com.lrz.betRaLa.exceptions.BetNotFoundException;
import br.com.lrz.betRaLa.models.Bet;
import br.com.lrz.betRaLa.models.Match;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class BetService {
    @Autowired
    BetRepository betRepo;
    
    @Autowired
    MatchService matchService;
    
    @Autowired 
    UserService userService;

public Bet createNewBet(Long matchId, Long userId, String result, String winner, float value){
    Match match = this.matchService.getMatch(matchId);
    User user = this.userService.getUser(userId);
    
    if(this.matchService.isMatchOver(match.getId())){
        throw new MatchOverException("Match is over, no more bets allowed");
    }
    if(user.getSaldo()<value){
        throw new UserInsufficientBalanceException("User does not have enough balance for desired bet");
    }
    if(!(winner.equals(match.getTeamA()) || winner.equals(match.getTeamB()))){
        throw new TeamNotPlayingException("The selected winning team is not playing in this match");
    }    
    Bet newBet =  new Bet();
    
    newBet.setBetAmount(value);
    newBet.setMatch(match);
    newBet.setWinner(winner);
    newBet.setResult(result);

    this.betRepo.save(newBet);  
    
    return newBet;
}    

public Bet getBet(Long betId){
    return this.betRepo.findById(betId).orElseThrow(()-> new BetNotFoundException("Bet not found"));
}

public boolean isBetWon(Long betId){
    Bet bet = this.getBet(betId);
    
    if(bet.getWinner().equals(bet.getMatch().getWinner())){
        if(bet.getResult().equals(bet.getMatch().getResult())){
            return true;
        }
    }
    return false;
}
}


