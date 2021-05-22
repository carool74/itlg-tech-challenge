package com.carolguin.itlg.tech.challenge.repositories;

import com.carolguin.itlg.tech.challenge.model.PersonJPA;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<PersonJPA, Long> {

  List<PersonJPA> findByOrderByLastNameAscFirstNameAsc();
}
