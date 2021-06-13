package com.example.demo.repositoryEF;


import org.springframework.data.repository.CrudRepository;
import com.example.demo.entityEF.Cita;

public interface AppointmentRepository extends CrudRepository<Cita, Long>{

}
