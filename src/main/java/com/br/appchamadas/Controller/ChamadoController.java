package com.br.appchamadas.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.br.appchamadas.Model.Chamada;
import com.br.appchamadas.Model.Fila;
import com.br.appchamadas.Model.Produto;
import com.br.appchamadas.Model.Tipo;
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
        return "/cadastroChamada";
    }

    @PostMapping("/cadastroChamada")
    public String cadastroChamado(Chamada chamada) {
        if (chamada.getHistorico() == null) {
            chamada.setStatus(false);
            cRepository.save(chamada);
        } else {
            chamada.setStatus(true);
            cRepository.save(chamada);
        }

        return "redirect:/listaChamadas";
    }

    @GetMapping("/cadastroChamada/responder/{id}")
    public ModelAndView responder(@PathVariable("id") Long id, Model model) {
        ModelAndView mv = new ModelAndView("cadastroChamada");
        Usuario usuarioLogado = new Usuario();
        List<Produto> produtos = pRepository.findAll();
        List<Fila> filas = fRepository.findAll();
        List<Usuario> usuarios = uRepository.findAll();

        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                usuarioLogado = u;
            }
        }

        Chamada chamadoAntigo = new Chamada();
        chamadoAntigo = cRepository.findById(id).get();

        mv.addObject("chamadoAntigo", chamadoAntigo);
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("produtos", produtos);
        mv.addObject("filas", filas);
        return mv;
    }

    @GetMapping("/listaChamadas")
    public ModelAndView listar() {
        Usuario usuarioLogado = new Usuario();
        List<Usuario> usuarios = uRepository.findAll();
        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                usuarioLogado = u;
            }
        }

        ModelAndView mv = new ModelAndView("listaChamadas");
        ArrayList<Chamada> chamados = new ArrayList<>();
        ArrayList<Fila> filasFiltro = new ArrayList<>();
        chamados = (ArrayList<Chamada>) cRepository.findAll();
        filasFiltro = (ArrayList<Fila>) fRepository.findAll();
        mv.addObject("chamados", chamados);
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("filasFiltro", filasFiltro);
        return mv;
    }

    @PostMapping("/listaChamadas")
    public ModelAndView listarFiltro(@RequestParam(name = "filasFiltro") Long fila, Model model) {
        ModelAndView mv = new ModelAndView("listaChamadas");
        Usuario usuarioLogado = new Usuario();
        List<Usuario> usuarios = uRepository.findAll();
        ArrayList<Chamada> chamados = new ArrayList<>();
        ArrayList<Chamada> chamadosFiltro = new ArrayList<>();
        ArrayList<Fila> filasFiltro = new ArrayList<>();
        filasFiltro = (ArrayList<Fila>) fRepository.findAll();
        chamados = (ArrayList<Chamada>) cRepository.findAll();

        for (Chamada c : chamados) {
            if (c.getFila().getId().equals(fila)) {
                chamadosFiltro.add(c);
            }
        }

        for (Usuario u : usuarios) {
            if (u.getStatus().equals(true)) {
                usuarioLogado = u;
            }
        }

        mv.addObject("chamadosFiltro", chamadosFiltro);
        mv.addObject("usuario", usuarioLogado);
        mv.addObject("filasFiltro", filasFiltro);
        return mv;
    }

}
