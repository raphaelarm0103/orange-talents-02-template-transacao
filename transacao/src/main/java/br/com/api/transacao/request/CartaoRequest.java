package br.com.api.transacao.request;

import br.com.api.transacao.model.Cartao;

public class CartaoRequest {

    private Long id;

    private String email;

    public Cartao toModel(){
        return new Cartao(id, email);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }
}
