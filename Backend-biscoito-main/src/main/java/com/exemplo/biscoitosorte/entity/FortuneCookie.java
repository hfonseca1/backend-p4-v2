package com.exemplo.biscoitosorte.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

@Entity
public class FortuneCookie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_frase", referencedColumnName = "id")
    @JsonManagedReference // Evita serialização recursiva
    private FortunePhrase frase;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FortunePhrase getFrase() {
        return frase;
    }

    public void setFrase(FortunePhrase frase) {
        this.frase = frase;
    }
}
