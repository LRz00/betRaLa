/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.controllers;

import br.com.lrz.betRaLa.models.Time;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.services.ServiceTime;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author lara
 */
@Validated
@RequestMapping("/time")
@RestController
public class TimeController {

    @Autowired
    ServiceTime timeService;

    @GetMapping("{id}")
    public ResponseEntity findById(Long Id) {
        Time time = this.timeService.findById(Id);

        return ResponseEntity.ok().body(time);
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Time time) {
        Time newTime = this.timeService.saveTime(time);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(newTime.getId()).toUri();

        return ResponseEntity.created(uri).build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Time deletable = this.timeService.findById(id);
        this.timeService.delete(deletable);
        return ResponseEntity.noContent().build();
    }

}
