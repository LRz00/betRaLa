/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.controllers;

import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.services.UserService;
import java.net.URI;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author lara
 */
@Validated
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RestController
public class UserController {
    @Autowired
    UserService userService;
       
    @GetMapping("/{cpf}")
    public ResponseEntity findByCpf(@PathVariable Long cpf){
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
    
    //delete
    @DeleteMapping("/{cpf}")
     public ResponseEntity<Void> delete(@PathVariable Long cpf){
         User deletable = this.userService.findByCpf(cpf);        
        this.userService.delete(deletable);
        return ResponseEntity.noContent().build();
    }
    //update

    @PutMapping("/updateSaldo")
    public ResponseEntity<Void> updateSaldo(@RequestBody Map<String, Object> payload) {
        Float value = Float.parseFloat(payload.get("value").toString());
        
        Long cpf = Long.parseLong(payload.get("cpf").toString());
        
        this.userService.updateSaldo(value, cpf);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = this.userService.getAll();
    return ResponseEntity.ok().body(users);
}
    
}
