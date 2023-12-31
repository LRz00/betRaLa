/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;
import br.com.lrz.betRaLa.models.User;
import br.com.lrz.betRaLa.repositories.IDaoUser;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author lara
 */
@Service
public class ServiceUser implements IServiceUser{
    @Autowired
    private IDaoUser daoUser;
    

    @Override
    public User create(User usuario) {
        //to do: service layer checkin of unique constraints
        //to do: password encryption
        usuario.setId(null);
        
        usuario = daoUser.save(usuario);
        
        return usuario;
    }
    
    @Override
    public List<User> getAll() {
        return daoUser.findAll();
    }

    @Override
    public void delete(User user) {
        if(user.getSaldo().compareTo(BigDecimal.ZERO) < 0){
            throw new RuntimeException("User has pending debt and cannot be deleted");
        }
        else if(user.getSaldo().compareTo(BigDecimal.ZERO) > 0){
            throw new RuntimeException("User has a positive balance, withdrawal required prior to deletion.");
        }else{
            this.daoUser.delete(user);
        }
        
    }

    @Override
    public User findByCpf(Long cpf) {
        return this.daoUser.findByCpf(cpf);
    }
    
    @Override
    public void updateSaldo(BigDecimal value, Long cpf){
        User user = findByCpf(cpf);
        //check if its a withdrawl and if yes if there is enough money in the account
        if(value.compareTo(BigDecimal.ZERO) < 0){
            if(user.getSaldo().subtract(value).compareTo(BigDecimal.ZERO) < 0){
                throw new RuntimeException("Account does not have requested withdraw value");
            }
            else{
                user.setSaldo(user.getSaldo().subtract(value));
            }
        }
        
        //check if its a deposit
        if(value.compareTo(BigDecimal.ZERO) > 0){
            user.setSaldo(user.getSaldo().add(value));
        }
        
    }
    
}
