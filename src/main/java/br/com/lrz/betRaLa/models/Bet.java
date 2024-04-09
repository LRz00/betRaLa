/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.models;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author lara
 */
@Getter
@Setter
@Entity
@Table(name = "bet")
public class Bet {
    @Id
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;
    
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    
    @Column(nullable = false)
    private Float value;
    
    @Column(nullable = false)
    private String winner;
    
}
