package com.br.appchamadas.Controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
            return "/telaInicial";
        } else {
            return "erroLogin";
        }

    }

    @GetMapping("/cadastroUsuario")
    public String cadastro(Model model) {
        List<Tipo> tipos = tRepository.findAll();
        model.addAttribute("tipos", tipos);

        return "/cadastroUsuario";
    }

    @PostMapping("/cadastroUsuario")
    public String cadastroUsuario(Usuario usuario) {
        usuario.setStatus(false);
        uRepository.save(usuario);
        return "redirect:/listaUsuario";
    }

    @GetMapping("/listaUsuario")
    public ModelAndView lista(Usuario usuario) {
        ModelAndView mv = new ModelAndView("listaUsuario");
        ArrayList<Usuario> usuarios = new ArrayList<>();
        ArrayList<Chamada> chamados = new ArrayList<>();
        usuarios = (ArrayList<Usuario>) uRepository.findAll();
        chamados = (ArrayList<Chamada>) cRepository.findAll();
        mv.addObject("usuario", usuarios);
        mv.addObject("chamados", chamados);
        return mv;
    }

    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable("id") Long id) {
        uRepository.deleteById(id);
        return "redirect:/listaUsuario";
    }

    @GetMapping("/cadastroUsuario/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("cadastroUsuario");
        Usuario usuarioAntigo = new Usuario();
        usuarioAntigo = uRepository.findById(id).get();
        mv.addObject("usuarioAntigo", usuarioAntigo);
        return mv;
    }

}
