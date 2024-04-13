/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package br.com.lrz.betRaLa.models.DTO;

import br.com.lrz.betRaLa.models.UserRoles;

/**
 *
 * @author lara
 */
public record CreateUserDTO(Long cpf, String name, Float balance, String email, String password) {

}
