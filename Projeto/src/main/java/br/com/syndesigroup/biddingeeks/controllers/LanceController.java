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

import br.com.syndesigroup.biddingeeks.models.Lance;
import br.com.syndesigroup.biddingeeks.models.Produto;
import br.com.syndesigroup.biddingeeks.models.Usuario;
import br.com.syndesigroup.biddingeeks.repositories.LanceRepository;
import br.com.syndesigroup.biddingeeks.repositories.ProdutoRepository;
import br.com.syndesigroup.biddingeeks.repositories.UsuarioRepository;

@RestController
@RequestMapping(value = "/lances")
public class LanceController {

	@Autowired
	private LanceRepository lanceRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private ProdutoRepository produtoRepository;

	/* Buscar Por ID */
	@GetMapping(value = "/buscarPorId")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorId(@RequestParam(name = "id") Long id) {
		if (id == null) {
			return new ResponseEntity<String>("ID não Informado", HttpStatus.OK);
		}

		Lance lance = lanceRepository.findById(id).get();

		return new ResponseEntity<Lance>(lance, HttpStatus.OK);
	}
	
	/* Buscar Por Usuario */
	@GetMapping(value = "/{idProduto}/listar")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> findByProduto(@PathVariable("idProduto") Long idProduto) {
		
		Optional<Produto> produto = produtoRepository.findById(idProduto);

		List<Lance>lances = lanceRepository.findByProduto(produto.get());

		return new ResponseEntity<List<Lance>>(lances, HttpStatus.OK);
	}

	/* Listar Todos */
	@GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Lance>> listaLances() {
		List<Lance> lances = lanceRepository.findAll();

		return new ResponseEntity<List<Lance>>(lances, HttpStatus.OK);
	}

	/* Salvar */
	@PostMapping(value = "/{idComprador}/{idProduto}/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> salvar(@PathVariable("idComprador") Long idComprador, @PathVariable("idProduto") Long idProduto, @RequestBody Lance lance) { /* Recebe os dados para salvar */
		Optional<Usuario> comprador = usuarioRepository.findById(idComprador);
		
		Optional<Produto> produto = produtoRepository.findById(idProduto);
		
		lance.setComprador(comprador.get());
		lance.setProduto(produto.get());
		
		List<Lance> lances = lanceRepository.findAll();
		
		for (Lance l : lances) {
			if(lance.getPreco() <= l.getPreco() && lance.getProduto().getId() == l.getProduto().getId()) {
				return new ResponseEntity<String>("O valor do seu lance precisa ser maior que lance atual", HttpStatus.OK);
			}
		}
				
		lanceRepository.save(lance);

		return new ResponseEntity<String>("Registro Salvo com sucesso", HttpStatus.OK);
	}

	/* Atualizar */
	@PutMapping(value = "/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Lance lance) { /* Recebe os dados para salvar */
		if (lance.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {

			Lance lanceCadastrado = lanceRepository.saveAndFlush(lance);

			return new ResponseEntity<Lance>(lanceCadastrado, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deletar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long id) { /* Recebe os dados para salvar */
		lanceRepository.deleteById(id);

		return new ResponseEntity<String>("Lance Deletado com Sucesso!", HttpStatus.OK);
	}
}
