/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.jogo.model;

import java.util.Date;
import java.util.Set;
import javax.persistence.*;
import lombok.Data;

/**
 *
 * @author lara
 */
@Data
@Entity
@Table(name = "jogo")
public class Jogo {
    @Id
    private Long id;
    
    @Temporal(TemporalType.DATE)
    private Date data;
    
    private String resultado;
    
    @OneToMany(mappedBy = "jogo")
    private Set<Participacao> participacoes;
}
