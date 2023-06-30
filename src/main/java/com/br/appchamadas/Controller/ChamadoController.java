package com.br.appchamadas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.appchamadas.Model.Chamada;
import com.br.appchamadas.Repository.ChamadaRepository;

@Controller
public class ChamadoController {

    @Autowired
    ChamadaRepository cRepository;

    @GetMapping("/cadastroChamada")
    public String cadastro() {
        return "/cadastroChamada";
    }

    @PostMapping("/cadastroChamada")
    public String cadastroChamado(Chamada chamada) {
        cRepository.save(chamada);
        return "redirect:/listaUsuario";
    }
}
