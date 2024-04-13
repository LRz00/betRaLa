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
public class AuthenticationDTO {
String email;
String password;

    public AuthenticationDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
}
