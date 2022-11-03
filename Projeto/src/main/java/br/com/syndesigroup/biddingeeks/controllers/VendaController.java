package br.com.syndesigroup.biddingeeks.controllers;

import java.time.LocalDateTime;
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
import br.com.syndesigroup.biddingeeks.models.Venda;
import br.com.syndesigroup.biddingeeks.repositories.LanceRepository;
import br.com.syndesigroup.biddingeeks.repositories.VendaRepository;

@RestController
@RequestMapping(value = "/vendas")
public class VendaController {

	@Autowired
	private VendaRepository vendaRepository;
	@Autowired
	private LanceRepository lanceRepository;

	/* Buscar Por ID */
	@GetMapping(value = "/buscarPorId")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<?> buscarPorId(@RequestParam(name = "id") Long id) {
		if (id == null) {
			return new ResponseEntity<String>("ID não Informado", HttpStatus.OK);
		}

		Venda venda = vendaRepository.findById(id).get();

		return new ResponseEntity<Venda>(venda, HttpStatus.OK);
	}

	/* Listar Todos */
	@GetMapping(value = "/listarTodos")
	@ResponseBody /* retorna o JSON com os dados */
	public ResponseEntity<List<Venda>> listaVendas() {
		List<Venda> vendas = vendaRepository.findAll();

		return new ResponseEntity<List<Venda>>(vendas, HttpStatus.OK);
	}

	/* Salvar */
	@PostMapping(value = "/{idLance}//salvar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<Venda> salvar(@PathVariable("idLance") Long idLance) { /* Recebe os dados para salvar */
		
		Optional<Lance> lance = lanceRepository.findById(idLance);
		Venda venda = new Venda();
		
		venda.setDataHora(LocalDateTime.now());
		venda.setPreco(lance.get().getPreco());
		venda.setComprador(lance.get().getComprador());
		venda.setProduto(lance.get().getProduto());
		
		Venda vendaCadastrada = vendaRepository.save(venda);
		return new ResponseEntity<Venda>(vendaCadastrada, HttpStatus.CREATED);
	}

	/* Atualizar */
	@PutMapping(value = "/atualizar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<?> atualizar(@RequestBody Venda venda) { /* Recebe os dados para salvar */
		if (venda.getId() == null) {
			return new ResponseEntity<String>("O ID não foi informado!", HttpStatus.OK);
		} else {

			Venda vendaCadastrada = vendaRepository.saveAndFlush(venda);

			return new ResponseEntity<Venda>(vendaCadastrada, HttpStatus.OK);
		}
	}

	@DeleteMapping(value = "/deletar")
	@ResponseBody /* descricao da resposta */
	public ResponseEntity<String> deletar(@RequestParam Long id) { /* Recebe os dados para salvar */
		vendaRepository.deleteById(id);

		return new ResponseEntity<String>("Venda Deletado com Sucesso!", HttpStatus.OK);
	}
}
