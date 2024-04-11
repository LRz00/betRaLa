/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.lrz.betRaLa.models.DTO;
import lombok.Getter;
/**
 *
 * @author lara
 */
@Getter
public class EndGameDTO{
    private Long id;
    private String winner;
    private String score;

    public EndGameDTO(Long id, String winner, String score) {
        this.id = id;
        this.winner = winner;
        this.score = score;
    }
    
}
