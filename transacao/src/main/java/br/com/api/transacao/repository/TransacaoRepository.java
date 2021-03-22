package br.com.api.transacao.repository;

import br.com.api.transacao.model.Transacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransacaoRepository extends JpaRepository<Long, Transacao> {

    Page<Transacao> findByCartao(Long id, Pageable pagina);
}
