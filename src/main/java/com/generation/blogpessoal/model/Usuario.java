package com.generation.blogpessoal.model;



import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;



@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Você precisa digitar um nome")
	private String nome;
	@NotBlank(message = "Você precisa digitar um usuário")
	@Size(min =8, max =20, message = "O Número mínimo de caracteres é 8 e o máximo é 20")
	private String usuario;
	@NotBlank(message = "Você precisa digitar uma senha")
	@Size(min =8, max =20, message = "O Número mínimo de caracteres é 8 e o máximo é 20")
	private String senha;
	private String foto;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	}
	
	
	
	


