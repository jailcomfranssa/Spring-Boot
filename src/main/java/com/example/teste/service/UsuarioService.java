package com.example.teste.service;

import com.example.teste.dto.UsuarioDto;
import com.example.teste.handler.BusinessException;
import com.example.teste.model.Usuario;
import com.example.teste.repository.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository, ModelMapper modelMapper) {
        this.usuarioRepository = usuarioRepository;
        this.modelMapper = modelMapper;
    }

    public UsuarioDto salvar(UsuarioDto usuarioDto){
        Usuario usuario = this.dtoToUser(usuarioDto);
        Usuario saveUsuario = this.usuarioRepository.save(usuario);
        return this.userToDto(saveUsuario);

    }

    public UsuarioDto buscar(Long id){
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        return this.userToDto(usuario);

    }
    public List<UsuarioDto> listar(){
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        List<UsuarioDto> usuarioDtos = usuarios.stream().map(this::userToDto).collect(Collectors.toList());
        return usuarioDtos;

    }

    public UsuarioDto atualizar(UsuarioDto usuarioDto, Long id){
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        usuario.setNome(usuarioDto.getNome());
        usuario.setEmail(usuarioDto.getEmail());
        usuario.setLogin(usuarioDto.getLogin());

        Usuario updateUsuario = this.usuarioRepository.save(usuario);
        return this.userToDto(updateUsuario);

    }

    public void deletar(Long id){
        Usuario usuario = this.usuarioRepository.findById(id)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));
        this.usuarioRepository.delete(usuario);

    }

    public Usuario dtoToUser(UsuarioDto userDto){
        return this.modelMapper.map(userDto,Usuario.class);
    }

    public UsuarioDto userToDto(Usuario user){
        UsuarioDto userDto = this.modelMapper.map(user,UsuarioDto.class);
        return userDto;
    }


}
