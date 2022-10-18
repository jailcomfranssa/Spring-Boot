package com.example.teste.controller;

import com.example.teste.dto.UsuarioDto;
import com.example.teste.service.UsuarioService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/")

    @ApiOperation(value = "Cadastrar um Usuário")
    public ResponseEntity<UsuarioDto> create(@RequestBody UsuarioDto usuarioDto){
        UsuarioDto createUser = this.usuarioService.salvar(usuarioDto);
        return new ResponseEntity<>(createUser, HttpStatus.CREATED);
    }
    @ApiOperation(value = "Listar todos os usuários")
    @GetMapping("/")
    public ResponseEntity<List<UsuarioDto>> getAllUser(){
        return ResponseEntity.ok(this.usuarioService.listar());
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Buscar por um Usuário")
    public ResponseEntity<UsuarioDto> getSingleUser(@PathVariable Long id){
        return ResponseEntity.ok(this.usuarioService.buscar(id));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Atualizar um usuários")
    public ResponseEntity<UsuarioDto> updateUser(@RequestBody UsuarioDto usuarioDto, @PathVariable Long id ){
        UsuarioDto update = this.usuarioService.atualizar(usuarioDto,id);
        return ResponseEntity.ok(update);

    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deletar um usuários")
    public ResponseEntity<?>deleteUser(@PathVariable Long id){
        this.usuarioService.deletar(id);
        return new ResponseEntity<>(Map.of("Messagem", "Usuario deletado "),HttpStatus.OK);
    }

}
