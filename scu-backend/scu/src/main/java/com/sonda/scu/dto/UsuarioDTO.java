package com.sonda.scu.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UsuarioDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	@NotEmpty(message = "Login obrigatório")
	@Length(min = 3, max = 100, message = "Login inválido: deve conter entre 3 e 100 caracteres")
	private String login;
	
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5, max = 120, message = "Nome inválido: deve conter entre 5 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Senha obrigatória")
	@Length(min = 5, max = 120, message = "Senha inválida: deve conter entre 5 e 120 caracteres")
	private String senha;

}
