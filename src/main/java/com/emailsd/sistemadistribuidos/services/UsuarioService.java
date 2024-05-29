package com.emailsd.sistemadistribuidos.services;

import com.emailsd.sistemadistribuidos.models.Usuario;
import com.emailsd.sistemadistribuidos.repositorys.UsuarioRepository;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Transactional
    public Usuario createUser(Usuario user) {
        Usuario savedUsuario = userRepository.save(user);
        rabbitTemplate.convertAndSend("user.Exchange","user.registration.email", savedUsuario.getEmail());
        return savedUsuario;
    }

    @Transactional
    public Usuario updateUser(Long id, Usuario userDetails) {
        Usuario usuario = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        usuario.setNome(userDetails.getNome());
        usuario.setIdade(userDetails.getIdade());
        usuario.setEmail(userDetails.getEmail());
        usuario.setSenha(userDetails.getSenha());
        usuario.setEndereco(userDetails.getEndereco());

        Usuario updatedUsuario = userRepository.save(usuario);
        String mensagem = updatedUsuario.getEmail();
        rabbitTemplate.convertAndSend("user.Exchange","user.update.Details",mensagem);
        return updatedUsuario;
    }

    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Usuario getUser(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }


    @Transactional(readOnly = true)
    public List<Usuario> getAllUsers() {
        return userRepository.findAll();
    }
}


