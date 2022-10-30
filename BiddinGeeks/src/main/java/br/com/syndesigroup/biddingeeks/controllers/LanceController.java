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

import br.com.syndesigroup.biddingeeks.models.Lance;
import br.com.syndesigroup.biddingeeks.repositories.LanceRepository;

@RestController
@RequestMapping(value = "/lances")
public class LanceController {

	@Autowired
	private LanceRepository lanceRepository;

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

	/* Listar Todos */
	@GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Lance>> listaLances() {
		List<Lance> lances = lanceRepository.findAll();

		return new ResponseEntity<List<Lance>>(lances, HttpStatus.OK);
	}

	/* Salvar */
	@PostMapping(value = "/salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Lance> salvar(@RequestBody Lance lance) { /* Recebe os dados para salvar */
		Lance lanceCadastrado = lanceRepository.save(lance);

		return new ResponseEntity<Lance>(lanceCadastrado, HttpStatus.CREATED);
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
