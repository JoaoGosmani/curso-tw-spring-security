package br.com.joaogosmani.jgprojetos.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

@Entity
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "cliente")
    private List<Projeto> projetos;

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

}
