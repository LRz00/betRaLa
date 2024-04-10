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
import br.com.lrz.betRaLa.models.Game;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.BetRepository;
import java.util.ArrayList;
import java.util.List;
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
    GameService matchService;
    
    @Autowired 
    UserService userService;
    
    /*
        THIS METHOD CREATES A NEW BET WITH THE INFORMATION PROVIDED.
        IT CHECKS IF THE MATCH IS OVER(BETS CANNOT BE MADE ON MATCHES WITH WINNERS SET).
        IT CHECKS IF THE USER TRYING TO BET HAS THE VALUE HE'S TRYING TO INPUT.
        IT CHECKS IS THE WINNER SELECTED BY THE WINNER IS A VALID OPTION(MUST CHOOSE BETWEEN TEAMA AND TEAMB)
    */    
public Bet createNewBet(Long matchId, Long userId, String winner, float value){
    Game match = this.matchService.getMatch(matchId);
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
    
    newBet.setAmount(value);
    newBet.setGame(match);
    newBet.setWinner(winner);

    this.betRepo.save(newBet);  
    
    return newBet;
}    

    /*  THIS METHOD RETURNS A BET BASED ON ITS ID.
        IF BET DOES NOT EXIST IT THROWS AN EXCEPTION.
    */
public Bet getBet(Long betId){
    return this.betRepo.findById(betId).orElseThrow(()-> new BetNotFoundException("Bet not found"));
}


    /*
        THIS METHOD CHECK IF A BET WAS WON BY COMPARING THE USER CHOICE WITH THE MATCH WINNER
    */
public boolean isBetWon(Long betId){
    Bet bet = this.getBet(betId);
    
    if(bet.getWinner().equals(bet.getGame().getWinner())){
            return true;        
    }
    return false;
}


    /*
        THIS METHOD IS TO BE CALLED WHEN A MATCH IS OVER AND A LOSER HAS BEEN DECIDED.
        IT SEPARATES ALL THE BETS OF THAT MATCH BETWEEN WINNERS AND LOSES.        
    */
public void settleAllBets(Game match){
    List<Bet> betsMade = this.betRepo.findByGameId(match.getId());
    
    List<Bet> winners = new ArrayList<>();
    List<Bet> losers = new ArrayList<>();
    
    for(Bet bet :betsMade){
        if(this.isBetWon(bet.getId())){
            winners.add(bet);
        }else{
            losers.add(bet);
        }
    }
    
    this.redistributeMoney(winners, losers);
    this.substractFromLosers(losers);
}

    /*
        THIS METHOD REDISTRIBUATE A % AMMOUNT OF THE LOSERS TOTAL MONEY TO THE WINNERS
    */
private void redistributeMoney(List<Bet> winners, List<Bet> losers){
    double totalMoneyLost = losers.stream().mapToDouble(Bet::getAmount).sum();
    double totalWon = winners.stream().mapToDouble(Bet::getAmount).sum();
    
    double percentWon = this.calculatePercentangeWon(totalWon, totalMoneyLost);
    
    for(Bet winner : winners){
        float amountWonByWinner = (float) ((float) winner.getAmount() + (percentWon));        
        winner.getUser().setSaldo(winner.getUser().getSaldo() + amountWonByWinner);
    }
    
}

/*
  THIS METHOD SUBSTRACT THE AMMOUNT THAT WAS BET FROM ALL THE USERS' WHO LOST ACCOUNT  
*/
private void substractFromLosers(List<Bet> losers){
    for(Bet loser : losers){
        User user = loser.getUser();
        float moneyLost = loser.getAmount();
        this.userService.updateSaldo(-moneyLost, user.getCpf());
    }
}

private double calculatePercentangeWon(double totalWon, double totalLost){
    double ratio = totalWon / totalLost;
    
    double adjustedPercent = 0.5 * ratio;
    
    return Math.min(adjustedPercent, 0.2);
}

}


