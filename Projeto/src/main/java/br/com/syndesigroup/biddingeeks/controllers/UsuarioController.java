package br.com.syndesigroup.biddingeeks.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.syndesigroup.biddingeeks.models.Usuario;
import br.com.syndesigroup.biddingeeks.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {
    
	@Autowired
	private UsuarioRepository usuarioRepository;
	
    /*Buscar Por ID*/
    @GetMapping(value = "/buscarPorId")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorId(@RequestParam(name = "id") Long id) {
		if(id == null) {
			return new ResponseEntity<String>("ID não Informado", HttpStatus.OK);
		}
		
		Usuario usuario = usuarioRepository.findById(id).get();

		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
    
    /*Listar Todos*/
    @GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Usuario>> listaUsuarios() {
		List<Usuario> usuarios = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);
	}
    
    /*Salvar*/
    @PostMapping(value = "/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
		Usuario usuarioCadastrado = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioCadastrado, HttpStatus.CREATED);
	}
    
    /*Atualizar*/
    @PutMapping(value = "/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {

			Usuario usuarioCadastrado = usuarioRepository.saveAndFlush(usuario);

			return new ResponseEntity<Usuario>(usuarioCadastrado, HttpStatus.OK);
		}
	}
    
    @DeleteMapping(value = "/deletar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long id) { /* Recebe os dados para salvar */
		usuarioRepository.deleteById(id);

		return new ResponseEntity<String>("Usuario Deletado com Sucesso!", HttpStatus.OK);
	}
    
    /*login*/
    @GetMapping(value = "/logar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> logar(@RequestParam String email, @RequestParam String password) { /* Recebe os dados para salvar */
		
    	Usuario usuario = usuarioRepository.fazerLogin(email, password);
    	
    	if(usuario.getId() != null) {
    		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
    	}else {
    		return new ResponseEntity<String>("Email ou Senha Invalidos!", HttpStatus.OK);
    	}    	
    	
	}
    
}
