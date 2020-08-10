package com.sonda.scu.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sonda.scu.domain.Usuario;
import com.sonda.scu.dto.UsuarioDTO;
import com.sonda.scu.exceptions.LoginNameInvalidException;
import com.sonda.scu.exceptions.ObjectNotFoundException;
import com.sonda.scu.repositories.UsuarioRepository;


@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findById(Long id) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ObjectNotFoundException("não encontrado"));
    }

    public void delete(Long id) {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("não encontrado")));
        usuarioRepository.deleteById(usuario.get().getId());
    }

    public Usuario save(Usuario usuario) {
    	Usuario usuarioLogin = usuarioRepository.findBylogin(usuario.getLogin());
    	if (Objects.nonNull(usuarioLogin)) {
			throw new LoginNameInvalidException("Nome de login já utilizado");
		}
        usuario.setId(null);
        Usuario u = usuarioRepository.save(usuario);
        return u;
    }

    public Usuario update(Usuario u) {
    	Optional<Usuario> usuario = usuarioRepository.findById(u.getId());
    	if (!usuario.isPresent()) {
			throw new ObjectNotFoundException("Usuário não encontrado");
		}
        return usuarioRepository.save(u);
    }

    public List<Usuario> findAll() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios;
    }

    public Usuario fromDTO(UsuarioDTO obj, Long id) {
        Usuario usuario = new Usuario(null, obj.getNome(), obj.getLogin(), obj.getSenha());
        usuario.setId(id);
        return usuario;
    }

    public Usuario fromDTO(UsuarioDTO obj) {
    	Usuario usuario = new Usuario(null, obj.getNome(), obj.getLogin(), obj.getSenha());
        return usuario;
    }

 
}
