package com.example.demo.repositoryEF;


import org.springframework.data.repository.CrudRepository;


import com.example.demo.entityEF.Paciente;

public interface PatientRepository extends CrudRepository<Paciente, Long>{

}
