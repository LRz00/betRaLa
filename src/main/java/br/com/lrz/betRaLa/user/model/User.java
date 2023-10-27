/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *
 * @author lara
 */

@Entity
@Table(name = "pessoa")
@Data
@EqualsAndHashCode(callSuper = false)
public class User {
    @Id @Column(nullable = false)
    private long cpf;
    @Column(nullable = false)
    private String nome; 
    private Double saldo;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
}
