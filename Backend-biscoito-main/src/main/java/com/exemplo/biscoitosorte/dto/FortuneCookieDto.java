package com.exemplo.biscoitosorte.dto;

public class FortuneCookieDto {

    private String nome;
    private Long phraseId;  // Nome do campo, que deve corresponder ao getter

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getPhraseId() {  // Método correto para acessar o campo phraseId
        return phraseId;
    }

    public void setPhraseId(Long phraseId) {
        this.phraseId = phraseId;
    }
}
