package com.example.demo.controlEF;
import java.util.Collection;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entityEF.Paciente;
import com.example.demo.repositoryEF.PatientRepository;

//Se crea el controlador 
@RestController
@RequestMapping(value = "/pacientes")
public class PatientControl {
	
	@Autowired
	PatientRepository repository;
	
	
	//Para poder mostrar la lista
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Paciente> getListPacientes(){
		 Iterable<Paciente> listPacientes = repository.findAll();
		return (Collection<Paciente>) listPacientes;
	}
	
	//Para poder mostrar un elemento en especifico de la lista
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Paciente getOnePaciente(@PathVariable(name = "id") Long id) {
		Optional<Paciente> paciente = repository.findById(id);
		Paciente last = null;
		if(paciente.isPresent()) {
			last = paciente.get();
		}
		return last;
	}
	
	//Para crer un nuevo elemento en la lista
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Paciente addPaciente(@RequestBody Paciente paciente) {
		Paciente newPaciente = repository.save(paciente);
		return newPaciente;
	}
	
	//Para eleiminar un elemento de la lista
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removePaciente(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	 //Para actualizar/ cambiar datos de un elemento
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Paciente giveAndtakePaciente(@PathVariable(name = "id")Long id, 
			@RequestBody Paciente paciente) {
		Optional<Paciente> oPaciente = repository.findById(id);
		if(oPaciente.isPresent()) {
			Paciente latest = oPaciente.get();
			latest.setId(id);
			latest.setName1(paciente.getName1());
			latest.setName2(paciente.getName2());
			latest.setLname1(paciente.getLname1());
			latest.setLname2(paciente.getLname2());	
			latest.setAge(paciente.getAge());
			Paciente giveAndtakedPaciente = repository.save(latest);
			return giveAndtakedPaciente;
		}
		return null;
	}
}