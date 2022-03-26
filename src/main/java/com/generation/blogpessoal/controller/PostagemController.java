package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import ch.qos.logback.core.status.Status;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostagemController {
	
	@Autowired //injeção de dependência . Para funcionar em qualquer classe, tenho que colocar  bin
	private PostagemRepository repository;
	// usei o list porque eu tenho que retornar muitos valores, por isso foi usado o GetAll também.
	@GetMapping
	public ResponseEntity<List<Postagem>> GetAll (){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")//id entre colchetes é o valor como sendo variável. só posso utilizar essa estratégia com valores númericos
	public ResponseEntity <Postagem> GetById (@PathVariable long id){
	return repository.findById(id)
			.map(resp -> ResponseEntity.ok(resp))
			.orElse(ResponseEntity.notFound().build());
	
	//esse método acima é conhecido como lamba um método sem função que devo personalizar. 
	//todo esse comando é equivalente a um if como vemos no exemplo abaixo		
//	Optional <Postagem> resposta = postagemRepository.findById(id);
//	if (resposta.isPresent()) {
//		return ResponseEntity.ok(resposta);
//	} else {
//		return ResponseEntity.notFound().build();
	}
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Postagem>> GetByTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	// esse método abaixo é uma requsição do tipo post, ou seja adiciona algo
	@PostMapping
	public ResponseEntity<Postagem> postPostagem (@Valid @RequestBody Postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	//O RequestBody serve para retornar o corpo, normalmente, dro front end.
	// o responseEnity retorna um código de resposta que são 200,300,etc.
	//body foi usado porque a resposta vai vir do corpo da requisição.
	// o método save cria um novo dado na tabela do banco de dados.
	// basicamente esse método acima funciona como o insert que fazemos no mysql.
	// @valid é um método específico do validation e retorna o erro mais especificamente.

	@PutMapping
	public ResponseEntity<Postagem>putPostagem(@Valid @RequestBody Postagem postagem) {
		return repository.findById(postagem.getId()).map(resp -> ResponseEntity.status(HttpStatus.OK).body(
						repository.save(postagem))).orElse(ResponseEntity.notFound().build());}
	

// o comando acima evita que o put adicione dados, caso o id digitado não exista.	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletePostagem (@PathVariable Long id) {
		return repository.findById(id)
				.map(resposta ->{
					repository.deleteById(id);
					return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
				})
		.orElse(ResponseEntity.notFound().build());
	}}
	


	// como o delete não retorna nada, ele sempre será void
	//preciso arrumar o erro de cima para não da erro quase o id não exista.
	// preciso checar se o id existe e retornar om status 204 caso apague.
	
