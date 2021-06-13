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
import com.example.demo.entityEF.Cita;
import com.example.demo.repositoryEF.AppointmentRepository;

//Se crea el controlador 
@RestController
@RequestMapping(value = "/citas")
public class AppointmentControl {
	
	@Autowired
	AppointmentRepository repository;
	
	
	//Para poder mostrar la lista
	@GetMapping
	@ResponseStatus(code = HttpStatus.OK)
	public Collection<Cita> getListCitas(){
		 Iterable<Cita> listCitas = repository.findAll();
		return (Collection<Cita>) listCitas;
	}
	
	//Para poder mostrar un elemento en especifico de la lista
	@GetMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public Cita getOneCita(@PathVariable(name = "id") Long id) {
		Optional<Cita> cita = repository.findById(id);
		Cita last = null;
		if(cita.isPresent()) {
			last = cita.get();
		}
		return last;
	}
	
	//Para crer un nuevo elemento en la lista
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public Cita addCita(@RequestBody Cita cita) {
		Cita newCita = repository.save(cita);
		return newCita;
	}
	
	//Para eleiminar un elemento de la lista
	@DeleteMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public void removeCita(@PathVariable(name = "id") Long id) {
		repository.deleteById(id);
	}
	
	 //Para actualizar/ cambiar datos de un elemento
	@PutMapping(value = "/{id}")
	@ResponseStatus(code = HttpStatus.ACCEPTED)
	public Cita giveAndtakeCita(@PathVariable(name = "id")Long id, 
			@RequestBody Cita cita) {
		Optional<Cita> oCita = repository.findById(id);
		if(oCita.isPresent()) {
			Cita latest = oCita.get();
			latest.setId(id);
			latest.setDate(cita.getDate());
			latest.setTime(cita.getTime());
			latest.setPatient(cita.getPatient());
			latest.setStatus(cita.getStatus());	
			latest.setNotes(cita.getNotes());
			Cita giveAndtakedCita = repository.save(latest);
			return giveAndtakedCita;
		}
		return null;
	}
}