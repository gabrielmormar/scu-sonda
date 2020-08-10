package com.sonda.scu.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sonda.scu.domain.Usuario;
import com.sonda.scu.dto.UsuarioDTO;
import com.sonda.scu.services.UsuarioService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/usuarios")
@CrossOrigin(origins = "http://localhost:4200")
@Api(value = "Usuario Controller")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    /**
     * GET - Retorna lista de todos usuários cadastradas
     *
     * @return
     */
    @GetMapping
    @ApiOperation(value = "Retorna lista de todos usuários cadastradas")
    public ResponseEntity<List<Usuario>> findAll() {
        List<Usuario> listaUsuarios = service.findAll();
        return ResponseEntity.ok().body(listaUsuarios);
    }

    /**
     * GET - Busca usuário pelo id
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Busca usuário pelo id")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) {
        Usuario empresa = service.findById(id);
        return ResponseEntity.ok().body(empresa);
    }


    /**
     * POST - Criação de novo usuário
     *
     * @param UsuarioDTO
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Criação de novo usuário")
    public ResponseEntity<Usuario> createUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        Usuario obj = service.fromDTO(usuarioDTO);
        service.save(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    /**
     * PUT - Atualiza Usuario pelo id
     *
     * @param empresa
     * @param id
     * @return
     */
    @PutMapping(value = "/{id}")
    @ApiOperation(value = "Atualização de um usuário")
    public ResponseEntity<Object> updateUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Long id) {
        Usuario usuario = service.fromDTO(usuarioDTO, id);
        service.update(usuario);
        return ResponseEntity.ok().body(usuario);
    }

    /**
     * DELETE - Deleta usuário pelo id
     *
     * @param id
     * @return
     */
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Deleta usuário pelo id")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Método auxiliar para decodificar query string
     *
     * @param str
     * @return
     */
//    private static String decodeParam(String str) {
//        try {
//            return URLDecoder.decode(str, "UTF-8");
//        } catch (UnsupportedEncodingException e) {
//            return "";
//        }
//    }
}
