/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.exceptions;

/**
 *
 * @author lara
 */
public class UserInsufficientBalanceException extends RuntimeException{
    public UserInsufficientBalanceException(String message){
        super(message);
    }
}
