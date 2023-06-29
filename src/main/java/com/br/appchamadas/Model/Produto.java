package com.br.appchamadas.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;

    @OneToMany(mappedBy = "produto", fetch = FetchType.EAGER)
    private List<Chamada> chamadas;

    public Produto() {

    }

    public Produto(Long id, String nomeProduto, List<Chamada> chamados) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.chamadas = chamados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public List<Chamada> getChamados() {
        return chamadas;
    }

    public void setChamados(List<Chamada> chamadas) {
        this.chamadas = chamadas;
    }

}
