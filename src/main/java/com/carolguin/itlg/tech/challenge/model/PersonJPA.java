package com.carolguin.itlg.tech.challenge.model;

import com.carolguin.itlg.tech.challenge.dto.Person;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "person")
public class PersonJPA {

  @Id
  private Long id;
  private String firstName;
  private String lastName;
  private Date birthdate;
  private Boolean hasInsurance;

  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<MovieJPA> favouriteMovies = new ArrayList<>();

  public PersonJPA(){}

  public PersonJPA(Long id, Person person) {
    this.id = id;
    this.firstName = person.getFirstName();
    this.lastName = person.getLastName();
    this.birthdate = person.getBirthdate();
    this.hasInsurance = person.getHasInsurance();

  }

  public void addMovie(MovieJPA movieJPA) {
    favouriteMovies.add(movieJPA);
    movieJPA.setPerson(this);
  }

  public void removeMovie(MovieJPA movieJPA) {
    favouriteMovies.remove(movieJPA);
    movieJPA.setPerson(null);
  }

}
