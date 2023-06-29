package com.br.appchamadas.Controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.br.appchamadas.Model.Usuario;
import com.br.appchamadas.Repository.UsuarioRepository;

@Controller
public class UsuarioController {

    @Autowired
    UsuarioRepository uRepository;

    @GetMapping("/cadastroUsuario")
    public String cadastro() {
        return "/cadastroUsuario";
    }

    @PostMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario usuario) {
        uRepository.save(usuario);
        return "redirect:/listaUsuario";
    }

    @GetMapping("/listaUsuario")
    public ModelAndView lista(Usuario usuario) {
        ModelAndView mv = new ModelAndView("listaUsuario");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) uRepository.findAll();
        mv.addObject("usuario", usuarios);
        return mv;
    }

}
