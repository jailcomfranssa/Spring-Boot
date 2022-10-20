package com.example.teste.controller;

import com.example.teste.dto.Login;
import com.example.teste.dto.Sessao;
import com.example.teste.model.Usuario;
import com.example.teste.repository.OauthRepository;
import com.example.teste.security.jwt.JWTCreator;
import com.example.teste.security.jwt.JWTObject;
import com.example.teste.security.config.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
@RestController
public class LoginController {

    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private SecurityConfig securityConfig;
    @Autowired
    private OauthRepository repository;

    @PostMapping("/login")
    public Sessao logar(@RequestBody Login login){
        Usuario user = repository.findByUsername(login.getEmail());
        if(user!=null) {
            boolean passwordOk =  encoder.matches(login.getPassword(), user.getSenha());
            if (!passwordOk) {
                throw new RuntimeException("Senha inválida para o login: " + login.getEmail());
            }
            //Estamos enviando um objeto Sessão para retornar mais informações do usuário
            Sessao sessao = new Sessao();
            sessao.setLogin(user.getEmail());

            JWTObject jwtObject = new JWTObject();
            jwtObject.setIssuedAt(new Date(System.currentTimeMillis()));
            jwtObject.setExpiration((new Date(System.currentTimeMillis() + SecurityConfig.EXPIRATION)));
            jwtObject.setRoles(user.getRoles());
            sessao.setToken(JWTCreator.create(SecurityConfig.PREFIX, SecurityConfig.KEY, jwtObject));
            return sessao;
        }else {
            throw new RuntimeException("Erro ao tentar fazer login");
        }
    }
}
