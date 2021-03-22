package br.com.api.transacao.repository;

import br.com.api.transacao.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartaoRepository extends JpaRepository<Long, Cartao> {

    Optional<Long> findById(Long id);
}
