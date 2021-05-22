package com.carolguin.itlg.tech.challenge.dto;

import com.carolguin.itlg.tech.challenge.model.PersonJPA;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Person {

  @NotNull
  @Min(value = 1, message = "Person's id should be greater than 0")
  @ApiModelProperty(name = "id", dataType = "Long", example = "1", notes = "Person's id")
  private Long id;

  @NotBlank(message = "First name can't be blank")
  @ApiModelProperty(name = "first-name", dataType = "String", example = "Carolina", notes = "Person's first name")
  @JsonProperty("first-name")
  private String firstName;

  @NotBlank(message = "Last name can't be blank")
  @ApiModelProperty(name = "last-name", dataType = "String", example = "Olguín", notes = "Person's last name")
  @JsonProperty("last-name")
  private String lastName;

  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "birthdate", dataType = "Date", example = "1974-11-07", notes = "Person's birthdate")
  private Date birthdate;

  @ApiModelProperty(name = "has-insurance", dataType = "Boolean", example = "true", notes = "If a person has insurance or not")
  @JsonProperty("has-insurance")
  private Boolean hasInsurance;

  @ApiModelProperty(name = "favourite-movies", dataType = "List<Movie>", notes = "List of favourite movies", example= "[\n" +
      "{\n" +
      "    \"title\": \"The Lord of the Rings\",\n" +
      "    \"genre\": \"fantasy\",\n" +
      "},\n" +
      "\n" +
      "{\n" +
      "    \"title\": \"Pulp Fiction\",\n" +
      "    \"genre\": \"action\",\n" +
      "},\n" +
      "\n" +
      "]")
  @JsonProperty("favourite-movies")
  private List<Movie> favouriteMovies = new ArrayList<>();

  public Person(){}

  public Person(PersonJPA jpa){
    this.id = jpa.getId();
    this.firstName = jpa.getFirstName();
    this.lastName = jpa.getLastName();
    this.birthdate = jpa.getBirthdate();
    this.hasInsurance = jpa.getHasInsurance();
    jpa.getFavouriteMovies().forEach(movieJPA -> this.getFavouriteMovies().add(new Movie(movieJPA)));
  }

}
