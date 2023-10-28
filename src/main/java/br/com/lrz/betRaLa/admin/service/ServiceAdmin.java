/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.admin.service;

import br.com.lrz.betRaLa.admin.dao.IDaoAdmin;
import br.com.lrz.betRaLa.admin.model.Admin;
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
