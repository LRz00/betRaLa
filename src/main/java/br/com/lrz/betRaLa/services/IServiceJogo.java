/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.lrz.betRaLa.services;

import br.com.lrz.betRaLa.models.Jogo;
import br.com.lrz.betRaLa.models.Participacao;
import java.util.Set;

/**
 *
 * @author lara
 */
public interface IServiceJogo {
    public Jogo adicionarJogo(Jogo jogo);
    public Jogo definirResultado(Long jogoId, String resultado);
    public Set<Participacao> getParticipacoesDoJogo(Long jogoId);
     public Participacao adicionarParticipacao(Participacao participacao);
}
