package com.br.appchamadas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.appchamadas.Model.Fila;
import com.br.appchamadas.Repository.FilaRepository;

@Controller
public class FilaController {

    @Autowired
    FilaRepository fRepository;

    @GetMapping("/cadastroFila")
    public String cadastro() {
        return "/cadastroFila";
    }

    @PostMapping("/cadastroFila")
    public String cadastroFila(Fila fila) {
        fRepository.save(fila);
        return "redirect:/telaInicial";
    }
}
