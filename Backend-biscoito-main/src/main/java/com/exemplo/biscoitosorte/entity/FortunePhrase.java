package com.exemplo.biscoitosorte.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import java.util.List;

@Entity
public class FortunePhrase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String conteudo;

    private String autor;

    @OneToMany(mappedBy = "frase", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference // Impede a serialização recursiva
    private List<FortuneCookie> fortuneCookies;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public List<FortuneCookie> getFortuneCookies() {
        return fortuneCookies;
    }

    public void setFortuneCookies(List<FortuneCookie> fortuneCookies) {
        this.fortuneCookies = fortuneCookies;
    }
}
