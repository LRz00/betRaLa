/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.time.service;
import br.com.lrz.betRaLa.time.model.Time;
import java.util.List;

/**
 *
 * @author lara
 */
public interface IServiceTime {
    public abstract List<Time> getAll();
    
    public abstract void saveTime(Time time);
}
