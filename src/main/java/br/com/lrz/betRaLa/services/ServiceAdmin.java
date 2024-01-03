/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.Admin;
import br.com.lrz.betRaLa.repositories.IDaoAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class ServiceAdmin implements IServiceAdmin{
    @Autowired
    private IDaoAdmin daoAdmin;
    
    @Override
    public Admin isAdmin(Long Code) {
     return daoAdmin.findByCode(Code);
    }
    
}
