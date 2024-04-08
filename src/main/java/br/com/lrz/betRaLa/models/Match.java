/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.models;

import java.util.Date;

import javax.persistence.*;
import lombok.*;

/**
 *
 * @author lara
 */
@Getter
@Setter
@Entity
@Table(name = "match")
public class Match {
    @Id
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date data;
    
    private String result;
    
    private String teamA;
    
    private String teamB;
    
    private String winner;
}
