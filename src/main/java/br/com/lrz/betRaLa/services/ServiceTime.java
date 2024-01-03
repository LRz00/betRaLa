/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.Time;
import br.com.lrz.betRaLa.repositories.IDaoTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author lara
 */
@Service
public class ServiceTime implements IServiceTime{
    @Autowired
    IDaoTime timeDao;
    
    @Override
    public List<Time> getAll() {
       List list= timeDao.findAll();
       return list;
    }

    @Override
    public void saveTime(Time time) {
       timeDao.save(time);
    }
    
}
