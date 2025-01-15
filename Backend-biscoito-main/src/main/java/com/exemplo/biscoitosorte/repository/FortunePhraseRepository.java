package com.exemplo.biscoitosorte.repository;

import com.exemplo.biscoitosorte.entity.FortunePhrase;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FortunePhraseRepository extends JpaRepository<FortunePhrase, Long> {
    Optional<FortunePhrase> findByConteudo(String conteudo);
}
