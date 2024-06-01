package com.fsrb.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fsrb.domain.Processo;
import com.fsrb.domain.record.ProcessoRecord;
import com.fsrb.service.ProcessoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/processo")
public class ProcessoResource {
	
	@Autowired
	private ProcessoService processoService;
	
	@PostMapping("/save")
	public ResponseEntity<ProcessoRecord> save(@RequestBody @Valid ProcessoRecord processoRecord) {
		processoService.save(processoRecord);
		return ResponseEntity.status(HttpStatus.CREATED).body(processoRecord);
	}
	
	@GetMapping("/")
	public ResponseEntity<Page<Processo>> findAll(Pageable pageable) {
		Page<Processo> processos = processoService.findAll(pageable);
		return ResponseEntity.ok(processos);
	}
	
	@GetMapping("/find/{id}")
	public ResponseEntity<Processo> findById(@PathVariable("id") Long id) {
		Processo processo = processoService.findById(id).get();
		return ResponseEntity.ok(processo);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ProcessoRecord> updateById(@PathVariable("id") Long id, ProcessoRecord processoRecord) {
		ProcessoRecord processoupdate = processoService.updateById(id, processoRecord);
		return ResponseEntity.ok(processoupdate);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Long id) {
		processoService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}