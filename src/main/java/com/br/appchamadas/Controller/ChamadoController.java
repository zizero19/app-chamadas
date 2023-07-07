package com.br.appchamadas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.appchamadas.Model.Chamada;
import com.br.appchamadas.Model.Fila;
import com.br.appchamadas.Model.Produto;
import com.br.appchamadas.Repository.ChamadaRepository;
import com.br.appchamadas.Repository.FilaRepository;
import com.br.appchamadas.Repository.ProdutoRepository;

import org.springframework.ui.Model;

@Controller
public class ChamadoController {

    @Autowired
    ChamadaRepository cRepository;

    @Autowired
    ProdutoRepository pRepository;

    @Autowired
    FilaRepository fRepository;

    @GetMapping("/cadastroChamada")
    public String cadastro(Model model) {
        List<Produto> produtos = pRepository.findAll();
        List<Fila> filas = fRepository.findAll();
        model.addAttribute("produtos", produtos);
        model.addAttribute("filas", filas);
        return "/cadastroChamada";
    }

    @PostMapping("/cadastroChamada")
    public String cadastroChamado(Chamada chamada) {
        System.out.println("data chegando: " + chamada.getData());
        chamada.setStatus(false);
        cRepository.save(chamada);
        return "redirect:/listaUsuario";
    }
}
