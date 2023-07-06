package com.br.appchamadas.Model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String login;
    private String senha;
    private String cpf;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "tipo_usuario", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "tipo_id"))
    List<Tipo> tipos;

    @OneToMany(mappedBy = "usuario")
    List<Chamada> chamadas;

    public Usuario(Long id, String nome, String login, String senha, String cpf, List<Tipo> tipos,
            List<Chamada> chamadas) {
        this.id = id;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.cpf = cpf;
        this.tipos = tipos;
        this.chamadas = chamadas;
    }

    public Usuario() {

    }

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Tipo> getTipos() {
        return tipos;
    }

    public void setTipos(List<Tipo> tipos) {
        this.tipos = tipos;
    }

    public List<Chamada> getChamadas() {
        return chamadas;
    }

    public void setChamadas(List<Chamada> chamadas) {
        this.chamadas = chamadas;
    }

}
