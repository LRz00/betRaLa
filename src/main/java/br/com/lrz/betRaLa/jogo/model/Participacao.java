/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.jogo.model;
import br.com.lrz.betRaLa.time.model.*;
import javax.persistence.*;
/**
 *
 * @author lara
 */
@Entity
public class Participacao {
 @Id 
 private Long id;
 @ManyToOne
 @JoinColumn(name = "time_id")
 private Time time;
 
 @ManyToOne
 @JoinColumn(name = "jogo_id")
 private Jogo jogo;
 
 @Enumerated(EnumType.STRING)
 @Column(name = "papel_time")
 private PapelTime papelTime;
}
