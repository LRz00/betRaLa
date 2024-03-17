/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.Jogo;
import br.com.lrz.betRaLa.models.Participacao;
import br.com.lrz.betRaLa.repositories.IDaoJogo;
import br.com.lrz.betRaLa.repositories.IDaoParticipacao;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author lara
 */
public class ServiceJogo implements IServiceJogo{
    @Autowired
    private IDaoJogo daoJogo;
    @Autowired
    private IDaoParticipacao daoParticipacao;

    @Override
    public Jogo adicionarJogo(Jogo jogo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Jogo definirResultado(Long jogoId, String resultado) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Set<Participacao> getParticipacoesDoJogo(Long jogoId) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Participacao adicionarParticipacao(Participacao participacao) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
