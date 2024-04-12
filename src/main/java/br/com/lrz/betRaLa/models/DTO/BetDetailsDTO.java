/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.models.DTO;

import lombok.Getter;

/**
 *
 * @author lara
 */
@Getter
public class BetDetailsDTO {
    Long matchId;
    Long userId;
    String winner;
    float amount;
    String score;
    
    public BetDetailsDTO(Long matchId, Long userId, String winner, float value) {
        this.matchId = matchId;
        this.userId = userId;
        this.winner = winner;
        this.amount = value;
    }
    
}
