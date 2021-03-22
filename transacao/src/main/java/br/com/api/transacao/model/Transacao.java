package br.com.api.transacao.model;


import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transacoes")
public class Transacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;

    @OneToOne(cascade = CascadeType.MERGE)
    private Estabelecimento estabelecimento;

    @OneToOne(cascade = CascadeType.MERGE)
    private Cartao cartao;

    private LocalDateTime feitaEm = LocalDateTime.now();

    @Deprecated
    public Transacao(){

    }


    public Transacao(Long id, BigDecimal valor, Estabelecimento estabelecimento, Cartao cartao) {
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

    public Estabelecimento getEstabelecimento() {
        return estabelecimento;
    }

    public Cartao getCartao() {
        return cartao;
    }


}
