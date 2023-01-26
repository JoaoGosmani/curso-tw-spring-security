package br.com.joaogosmani.jgprojetos.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Cargo extends Entidade {
    
    @Column(nullable = false, length = 40, unique = true)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
