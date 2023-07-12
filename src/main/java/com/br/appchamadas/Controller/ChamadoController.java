package com.br.appchamadas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.br.appchamadas.Model.Chamada;
import com.br.appchamadas.Model.Fila;
import com.br.appchamadas.Model.Produto;
import com.br.appchamadas.Model.Usuario;
import com.br.appchamadas.Repository.ChamadaRepository;
import com.br.appchamadas.Repository.FilaRepository;
import com.br.appchamadas.Repository.ProdutoRepository;
import com.br.appchamadas.Repository.UsuarioRepository;

import org.springframework.ui.Model;

@Controller
public class ChamadoController {

    @Autowired
    UsuarioRepository uRepository;

    @Autowired
    ChamadaRepository cRepository;

    @Autowired
    ProdutoRepository pRepository;

    @Autowired
    FilaRepository fRepository;

    @GetMapping("/cadastroChamada")
    public String cadastro(Model model) {
        Usuario usuarioLogado = new Usuario();
        List<Produto> produtos = pRepository.findAll();
        List<Fila> filas = fRepository.findAll();
        List<Usuario> usuarios = uRepository.findAll();

        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                usuarioLogado = u;
            }
        }
        model.addAttribute("produtos", produtos);
        model.addAttribute("filas", filas);
        model.addAttribute("usuario", usuarioLogado);
        System.out.println("Dados do usuario logado:" + toString());
        return "/cadastroChamada";
    }

    @PostMapping("/cadastroChamada")
    public String cadastroChamado(Chamada chamada) {
        chamada.setStatus(false);
        System.out.println("Usuario da chamada" + chamada.getUsuario());
        cRepository.save(chamada);
        return "redirect:/listaUsuario";
    }
}
