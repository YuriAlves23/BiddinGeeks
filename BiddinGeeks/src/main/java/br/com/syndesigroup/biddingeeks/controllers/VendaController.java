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

import br.com.syndesigroup.biddingeeks.models.Venda;
import br.com.syndesigroup.biddingeeks.repositories.VendaRepository;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;

	/* Buscar Por ID */
	@GetMapping(value = "/buscarPorId")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorId(@RequestParam(name = "id") Long id) {
		if (id == null) {
			return new ResponseEntity<String>("ID não Informado", HttpStatus.OK);
		}

		Venda usuario = vendaRepository.findById(id).get();

		return new ResponseEntity<Venda>(usuario, HttpStatus.OK);
	}

	/* Listar Todos */
	@GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Venda>> listaVendas() {
		List<Venda> vendas = vendaRepository.findAll();

		return new ResponseEntity<List<Venda>>(vendas, HttpStatus.OK);
	}

	/* Salvar */
	@PostMapping(value = "/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Venda> salvar(@RequestBody Venda usuario) { /* Recebe os dados para salvar */
		Venda usuarioCadastrado = vendaRepository.save(usuario);

		return new ResponseEntity<Venda>(usuarioCadastrado, HttpStatus.CREATED);
	}

	/* Atualizar */
	@PutMapping(value = "/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Venda usuario) { /* Recebe os dados para salvar */
		if (usuario.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {

			Venda usuarioCadastrado = vendaRepository.saveAndFlush(usuario);

			return new ResponseEntity<Venda>(usuarioCadastrado, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deletar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long id) { /* Recebe os dados para salvar */
		vendaRepository.deleteById(id);

		return new ResponseEntity<String>("Venda Deletado com Sucesso!", HttpStatus.OK);
	}
}
