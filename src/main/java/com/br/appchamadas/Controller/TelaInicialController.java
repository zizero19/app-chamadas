package com.br.appchamadas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.br.appchamadas.Model.Usuario;
import com.br.appchamadas.Repository.UsuarioRepository;

@Controller
public class TelaInicialController {

    @Autowired
    UsuarioRepository uRepository;

    @GetMapping("/telaInicial")
    public String home(Model model) {
        List<Usuario> usuarios = uRepository.findAll();
        Usuario usuarioLogado = new Usuario();

        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                usuarioLogado = u;
            }
        }

        model.addAttribute("usuario", usuarioLogado);
        return "/telaInicial";
    }
}
