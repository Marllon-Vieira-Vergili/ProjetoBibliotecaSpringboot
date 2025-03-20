package com.marllon.vieira.vergili.GerenciamentoDeBiblioteca.ControllersFrontView;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class LoginAcessController {



    @GetMapping("/login")
    public String login(){
        return "login_page";
    }
}
