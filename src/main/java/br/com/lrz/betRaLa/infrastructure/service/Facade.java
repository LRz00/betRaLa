/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.infrastructure.service;

import br.com.lrz.betRaLa.admin.model.Admin;
import br.com.lrz.betRaLa.admin.service.IServiceAdmin;
import br.com.lrz.betRaLa.time.model.Time;
import br.com.lrz.betRaLa.time.service.IServiceTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller
public class Facade implements IFacade {
    //###################TIME###################
    @Autowired
    IServiceTime serviceTime;

    @Override
    public void salvarTime(Time time) {
        serviceTime.saveTime(time);
    }

    
        //###################ADMIN###################
    @Autowired
    IServiceAdmin serviceAdmin;
    @Override
    public Admin isAdmin(Long code) {
    return serviceAdmin.isAdmin(code);
    }
    
    

}
