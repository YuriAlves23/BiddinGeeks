package br.com.syndesigroup.biddingeeks.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.syndesigroup.biddingeeks.models.Produto;
import br.com.syndesigroup.biddingeeks.models.Usuario;
import br.com.syndesigroup.biddingeeks.repositories.ProdutoRepository;
import br.com.syndesigroup.biddingeeks.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;

	/* Buscar Por ID */
	@GetMapping(value = "/buscarPorId")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorId(@RequestParam(name = "id") Long id) {
		if (id == null) {
			return new ResponseEntity<String>("ID não Informado", HttpStatus.OK);
		}

		Produto produto = produtoRepository.findById(id).get();

		return new ResponseEntity<Produto>(produto, HttpStatus.OK);
	}

	/* Buscar Por ID */
	@GetMapping(value = "/buscarPorNome")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorNome(@RequestParam(name = "nome") String nome) {
		if (nome.equals("")) {
			return new ResponseEntity<String>("Nome não Informado", HttpStatus.OK);
		}

		List<Produto> produtos = produtoRepository.buscarPorNome(nome.trim());

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}
	
	/* Buscar Por Usuario */
	@GetMapping(value = "/{idUsuario}/listar")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> findByUsuario(@PathVariable("idUsuario") Long idUsuario) {
		
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);

		List<Produto> produtos = produtoRepository.findByUsuario(usuario.get());

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	/* Listar Todos */
	@GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Produto>> listaProdutos() {
		List<Produto> produtos = produtoRepository.findAll();

		return new ResponseEntity<List<Produto>>(produtos, HttpStatus.OK);
	}

	/* Salvar */
	@PostMapping(value = "/{idUsuario}/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Produto> salvar(@PathVariable("idUsuario") Long idUsuario,
			@RequestBody Produto produto) { /* Recebe os dados para salvar */
		Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
		produto.setUsuario(usuario.get());
		Produto produtoCadastrado = produtoRepository.save(produto);
		return new ResponseEntity<Produto>(produtoCadastrado, HttpStatus.CREATED);
	}

	/* Atualizar */
	@PutMapping(value = "/{idUsuario}/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@PathVariable("idUsuario")Long idUsuario, @RequestBody Produto produto) { /* Recebe os dados para salvar */
		if (produto.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {
			
			Optional<Usuario> usuario = usuarioRepository.findById(idUsuario);
			produto.setUsuario(usuario.get());
			Produto produtoCadastrado = produtoRepository.saveAndFlush(produto);

			return new ResponseEntity<Produto>(produtoCadastrado, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deletar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long id) { /* Recebe os dados para salvar */
		produtoRepository.deleteById(id);

		return new ResponseEntity<String>("Produto Deletado com Sucesso!", HttpStatus.OK);
	}
}
