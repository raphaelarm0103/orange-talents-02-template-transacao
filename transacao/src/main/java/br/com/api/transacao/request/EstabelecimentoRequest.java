package br.com.api.transacao.request;

import br.com.api.transacao.model.Estabelecimento;

public class EstabelecimentoRequest {

    private Long id;

    private String nome;

    private String endereco;

    public Estabelecimento toModel() {
       return new Estabelecimento(id, nome, endereco);
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }
}
