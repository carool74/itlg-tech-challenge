package com.carolguin.itlg.tech.challenge.repositories;

import com.carolguin.itlg.tech.challenge.model.MovieJPA;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<MovieJPA, Long> {
}
