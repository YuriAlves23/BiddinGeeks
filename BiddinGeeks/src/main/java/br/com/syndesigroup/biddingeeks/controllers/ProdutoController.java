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

import br.com.syndesigroup.biddingeeks.models.Produto;
import br.com.syndesigroup.biddingeeks.repositories.ProdutoRepository;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository produtoRepository;

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
		if(nome.equals("")) {
			return new ResponseEntity<String>("Nome não Informado", HttpStatus.OK);
		}
		
		List<Produto> produtos = produtoRepository.buscarPorNome(nome.trim());

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
	@PostMapping(value = "/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Produto> salvar(@RequestBody Produto produto) { /* Recebe os dados para salvar */
		Produto produtoCadastrado = produtoRepository.save(produto);

		return new ResponseEntity<Produto>(produtoCadastrado, HttpStatus.CREATED);
	}

	/* Atualizar */
	@PutMapping(value = "/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Produto produto) { /* Recebe os dados para salvar */
		if (produto.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {

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
