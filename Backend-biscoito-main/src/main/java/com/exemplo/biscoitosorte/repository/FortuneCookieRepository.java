package com.exemplo.biscoitosorte.repository;

import com.exemplo.biscoitosorte.entity.FortuneCookie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FortuneCookieRepository extends JpaRepository<FortuneCookie, Long> {
    // Métodos personalizados, se necessário
}
