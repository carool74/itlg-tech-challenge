package com.carolguin.itlg.tech.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Data
@Table(name = "movie")
public class MovieJPA {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String title;
  private String genre;

  @ManyToOne(optional = true)
  @JoinColumn(name = "person_id")
  @JsonIgnore
  @ToString.Exclude
  @EqualsAndHashCode.Exclude
  private PersonJPA person;

  public MovieJPA(){}

  public MovieJPA(String title, String genre){
    this.title = title;
    this.genre = genre;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (!(o instanceof MovieJPA))
      return false;
    return
        id != null &&
            id.equals(((MovieJPA) o).getId());
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

}
