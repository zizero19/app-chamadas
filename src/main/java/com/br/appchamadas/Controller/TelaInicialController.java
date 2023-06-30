package com.br.appchamadas.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TelaInicialController {

    @GetMapping("/telaInicial")
    public String home() {
        return "telaInicial";
    }
}
