package com.br.appchamadas.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Fila {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @OneToMany(mappedBy = "fila", fetch = FetchType.EAGER)
    private List<Chamada> chamadas;

    private Fila() {

    }

    public Fila(Long id, String descricao, List<Chamada> chamadas) {
        this.id = id;
        this.descricao = descricao;
        this.chamadas = chamadas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<Chamada> getChamadas() {
        return chamadas;
    }

    public void setChamadas(List<Chamada> chamadas) {
        this.chamadas = chamadas;
    }

}
