package com.br.appchamadas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.appchamadas.Model.Produto;
import com.br.appchamadas.Repository.ProdutoRepository;

@Controller
public class ProdutoController {

    @Autowired
    ProdutoRepository pRepository;

    @GetMapping("/cadastroProduto")
    public String cadastro() {
        return "cadastroProduto";
    }

    @PostMapping("/cadastroProduto")
    public String cadastroProduto(Produto produto) {
        pRepository.save(produto);
        return "redirect:/telaInicial";
    }
}
