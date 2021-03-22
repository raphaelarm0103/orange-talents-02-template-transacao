package br.com.api.transacao.controller;

import br.com.api.transacao.model.Cartao;
import br.com.api.transacao.model.Transacao;
import br.com.api.transacao.repository.CartaoRepository;
import br.com.api.transacao.repository.TransacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/cartoes")
public class TransacaoController {

    @Autowired
    TransacaoRepository transacaoRepository;

    @Autowired
    CartaoRepository cartaoRepository;

    @GetMapping("/transacoes/{id}")
    public ResponseEntity<Page<Transacao>> listar(@PathVariable Long id, @PageableDefault(size = 10)Pageable pagina){

        Optional<Long> cartao = cartaoRepository.findById(id);

        if(!cartao.isPresent()){
            return ResponseEntity.badRequest().build();
        }

      Page<Transacao> transacao = transacaoRepository.findByCartao(id, pagina);

        return ResponseEntity.ok().body(transacao);
    }

}
