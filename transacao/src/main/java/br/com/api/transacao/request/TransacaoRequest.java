package br.com.api.transacao.request;

import br.com.api.transacao.model.Cartao;
import br.com.api.transacao.model.Estabelecimento;
import br.com.api.transacao.model.Transacao;

import javax.persistence.CascadeType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class TransacaoRequest {

    private Long id;

    @NotNull
    private BigDecimal valor;

    @NotBlank
    private EstabelecimentoRequest estabelecimento;

    @NotBlank
    private CartaoRequest cartao;

    public TransacaoRequest(Long id, @NotNull BigDecimal valor, @NotBlank EstabelecimentoRequest estabelecimento, @NotBlank CartaoRequest cartao) {
        this.id = id;
        this.valor = valor;
        this.estabelecimento = estabelecimento;
        this.cartao = cartao;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public EstabelecimentoRequest getEstabelecimento() {
        return estabelecimento;
    }

    public CartaoRequest getCartao() {
        return cartao;
    }

    public Transacao toModel() {
        return new Transacao(id, valor, estabelecimento.toModel(), cartao.toModel());
    }
}
