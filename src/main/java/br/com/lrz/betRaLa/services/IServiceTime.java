/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.services;
import br.com.lrz.betRaLa.models.Time;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lara
 */
public interface IServiceTime {
    public abstract List<Time> getAll();
    
    public abstract Time saveTime(Time time);
    
    public abstract void delete(Time time);
    
    public abstract Optional<Time> findById(Long id);
}
