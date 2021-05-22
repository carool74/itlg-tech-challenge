package com.carolguin.itlg.tech.challenge.services;

import com.carolguin.itlg.tech.challenge.dto.Person;
import com.carolguin.itlg.tech.challenge.dto.PersonToUpdate;
import com.carolguin.itlg.tech.challenge.model.PersonJPA;
import com.carolguin.itlg.tech.challenge.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonService {

  @Value("${max.movies.per.person}")
  private int maxMoviesPerPerson;

  @Autowired
  private PersonRepository personRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);


  public Optional<Person> create(Person person) {
    Optional<PersonJPA> personJPAOptional = personRepository.findById(person.getId());

    if (personJPAOptional.isPresent()) {
      LOGGER.warn("Person already exists -> {}", person.getId());
      return Optional.empty();
    }
    else if (person.getFavouriteMovies().size() > maxMoviesPerPerson) {
      LOGGER.warn("Person can't have more than {} favourite movies", maxMoviesPerPerson);
      return Optional.empty();
    }
    else {
      PersonJPA personJPA = new PersonJPA(person);
      PersonJPA personSaved = personRepository.save(personJPA);
      LOGGER.info("Person ({} - {} {}) created successfully", personSaved.getId(), personSaved.getFirstName(), personSaved.getLastName());
      return Optional.of(new Person(personSaved));
    }

  }


  public Optional<Person> update(Long id, PersonToUpdate personToUpdate) {
    Optional<PersonJPA> personJPAOptional = personRepository.findById(id);

    if (personJPAOptional.isPresent()) {
      PersonJPA personJPA = personJPAOptional.get();
      if (personToUpdate.getId()!=null) personJPA.setId(personToUpdate.getId());
      if (personToUpdate.getLastName()!=null) personJPA.setLastName(personToUpdate.getLastName());
      if (personToUpdate.getFirstName()!=null) personJPA.setFirstName(personToUpdate.getFirstName());
      if (personToUpdate.getBirthdate()!=null) personJPA.setBirthdate(personToUpdate.getBirthdate());
      if (personToUpdate.getHasInsurance()!=null) personJPA.setHasInsurance(personToUpdate.getHasInsurance());

      PersonJPA personSaved = personRepository.save(personJPA);
      LOGGER.info("Person ({} - {} {}) updated successfully", personSaved.getId(), personSaved.getFirstName(), personSaved.getLastName());
      return Optional.of(new Person(personSaved));
    }
    else {
      LOGGER.warn("Person not found -> {}", id);
      return Optional.empty();
    }
  }


  public Optional<Person> delete(Long id) {
    Optional<PersonJPA> personJPAOptional = personRepository.findById(id);

    if (personJPAOptional.isPresent()) {
      PersonJPA personJPA = personJPAOptional.get();
      personRepository.delete(personJPA);
      LOGGER.info("Person ({} - {} {}) deleted successfully", personJPA.getId(), personJPA.getFirstName(), personJPA.getLastName());
      return Optional.of(new Person(personJPA));
    }
    else {
      LOGGER.warn("Person not found -> {}", id);
      return Optional.empty();
    }
  }


  public List<Person> getAll() {
    LOGGER.info("Getting all persons sorted by lastName and firstName");
    List<PersonJPA> personJPAList = personRepository.findByOrderByLastNameAscFirstNameAsc();
    List<Person> persons = personJPAList.stream().map(pJPA -> new Person(pJPA)).collect(Collectors.toList());
    return persons;
  }


  public Optional<Person> getById(Long id) {
    LOGGER.info("Getting a person by id");
    Optional<PersonJPA> personJPA = personRepository.findById(id);
    if (personJPA.isPresent()) {
      LOGGER.info("Person found -> {}", id);
      return Optional.of(new Person(personJPA.get()));
    }
    else {
      LOGGER.warn("Person not found -> {}", id);
      return Optional.empty();
    }
  }


  public List<Person> getByFirstName(String firstName) {
    LOGGER.info("Getting persons by first name");
    List<PersonJPA> personJPAList = personRepository.findByFirstNameContainingIgnoreCase(firstName);
    List<Person> persons = personJPAList.stream().map(pJPA -> new Person(pJPA)).collect(Collectors.toList());
    return persons;
  }

}
