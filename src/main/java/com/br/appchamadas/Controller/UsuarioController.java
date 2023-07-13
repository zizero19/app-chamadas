package com.br.appchamadas.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.appchamadas.Model.Chamada;
import com.br.appchamadas.Model.Tipo;
import com.br.appchamadas.Model.Usuario;
import com.br.appchamadas.Repository.ChamadaRepository;
import com.br.appchamadas.Repository.TipoRepository;
import com.br.appchamadas.Repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository uRepository;

    @Autowired
    ChamadaRepository cRepository;

    @Autowired
    TipoRepository tRepository;

    @GetMapping("/loginUsuario")
    public String login() {
        return "loginUsuario";
    }

    @PostMapping("/loginUsuario")
    public String logar(Usuario usuario) {
        Usuario usuarioAutenticacao = uRepository.findByLoginAndSenha(usuario.getLogin(), usuario.getSenha());

        if (usuarioAutenticacao != null) {
            usuarioAutenticacao.setStatus(true);
            uRepository.save(usuarioAutenticacao);

            return "redirect:/telaInicial";
        } else {
            return "redirect:/telaLogin";
        }

    }

    @GetMapping("/cadastroUsuario")
    public String cadastro(Model model) {
        List<Tipo> tipos = tRepository.findAll();
        model.addAttribute("tipos", tipos);

        return "/cadastroUsuario";
    }

    @PostMapping("/cadastroUsuario")
    public String salvar(Usuario usuario, @RequestParam("tipo") List<Tipo> tipoId) {
        System.out.println();

        usuario.setStatus(false);
        usuario.setTipos(tipoId);

        uRepository.save(usuario);
        return "redirect:/loginUsuario";
    }

    @PostMapping("/logout")
    public String deslogarUsuario() {
        List<Usuario> usuarios = uRepository.findAll();
        Long idUsuarioLogado = null;

        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                idUsuarioLogado = u.getId();
                break;
            }
        }

        if (idUsuarioLogado != null) {
            Usuario usuarioDeslogado = uRepository.findById(idUsuarioLogado).orElse(null);
            if (usuarioDeslogado != null) {
                usuarioDeslogado.setStatus(false);
                uRepository.save(usuarioDeslogado);
            }
        }

        return "redirect:/loginUsuario";
    }

}
