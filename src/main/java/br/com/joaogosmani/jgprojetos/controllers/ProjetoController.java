package br.com.joaogosmani.jgprojetos.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.joaogosmani.jgprojetos.models.Projeto;
import br.com.joaogosmani.jgprojetos.repositories.ClienteRepository;
import br.com.joaogosmani.jgprojetos.repositories.FuncionarioRepository;
import br.com.joaogosmani.jgprojetos.repositories.ProjetoRepository;

@Controller
@RequestMapping("/projetos")
public class ProjetoController {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @GetMapping
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView("projeto/home");

        modelAndView.addObject("projetos", projetoRepository.findAll());

        return modelAndView;
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projeto/detalhes");

        modelAndView.addObject("projeto", projetoRepository.getOne(id));

        return modelAndView;
    }

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("projeto/formulario");

        modelAndView.addObject("projeto", new Projeto());
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("lideres", funcionarioRepository.findByCargoNome("Gerente"));
        modelAndView.addObject("funcionarios", funcionarioRepository.findByCargoNomeNot("Gerente"));

        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("projeto/formulario");

        modelAndView.addObject("projeto", projetoRepository.getOne(id));
        modelAndView.addObject("clientes", clienteRepository.findAll());
        modelAndView.addObject("lideres", funcionarioRepository.findByCargoNome("Gerente"));
        modelAndView.addObject("funcionarios", funcionarioRepository.findByCargoNomeNot("Gerente"));

        return modelAndView;        
    }

    @PostMapping({"/cadastrar", "/{id}/editar"})
    public String salvar(Projeto projeto) {
        projetoRepository.save(projeto);

        return "redirect:/projetos";
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        projetoRepository.deleteById(id);

        return "redirect:/projetos";
    }

}
