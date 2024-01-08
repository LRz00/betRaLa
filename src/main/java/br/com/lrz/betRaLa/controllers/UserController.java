/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.controllers;

import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.services.IServiceUser;
import java.net.URI;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IServiceUser userService;
    
    @GetMapping("/{cpf}")
    public ResponseEntity findByCpf(Long cpf){
        User user = this.userService.findByCpf(cpf);
        return ResponseEntity.ok().body(user);
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody User user) {
        User newUser = this.userService.create(user);
        
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{cpf}").buildAndExpand(newUser.getCpf()).toUri();
        
        return ResponseEntity.created(uri).build();
    }
    
    //TO-DO: OTHER MAPPINGS
    
    //delete
    @DeleteMapping("/{cpf}")
    public void delete (User user){
        User deletableUser = (User) this.userService.findByCpf(user.getCpf());
    }
    //update
    
    
}
