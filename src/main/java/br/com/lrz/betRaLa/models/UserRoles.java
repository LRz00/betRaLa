/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package br.com.lrz.betRaLa.models;

/**
 *
 * @author lara
 */
public enum UserRoles {
    ADMIN("admin"),
    
    USER("user");
    
    private String role;

    private UserRoles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

}
