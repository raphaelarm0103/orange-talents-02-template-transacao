package br.com.api.transacao.kafka;

import br.com.api.transacao.model.Transacao;
import br.com.api.transacao.request.TransacaoRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class ConsumidorTransacao {

    @PersistenceContext
    private EntityManager manager;

    @Transactional
    @KafkaListener(topics = "${spring.kafka.topic.transactions}")
    public void ouvir(TransacaoRequest request) {
        System.out.println("======================");
        System.out.println("Mensagem chegou");
        Transacao transacao = request.toModel();
        System.out.println(transacao.toString());
        manager.merge(transacao);


        System.out.println("Mensagem terminou");
        System.out.println("======================");

    }
}
