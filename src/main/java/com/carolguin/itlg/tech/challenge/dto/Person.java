package com.carolguin.itlg.tech.challenge.dto;

import com.carolguin.itlg.tech.challenge.model.PersonJPA;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class Person {

  @NotBlank(message = "First name can't be blank")
  @ApiModelProperty(name = "firstName", dataType = "String", example = "Carolina", notes = "Person's first name")
  private String firstName;

  @NotBlank(message = "Last name can't be blank")
  @ApiModelProperty(name = "lastName", dataType = "String", example = "Olguín", notes = "Person's last name")
  private String lastName;

  @NotNull
  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "birthdate", dataType = "Date", example = "1974-11-07", notes = "Person's birthdate")
  private Date birthdate;

  @ApiModelProperty(name = "hasInsurance", dataType = "Boolean", example = "true", notes = "If a person has insurance or not")
  private Boolean hasInsurance;

  public Person(){}

  public Person(PersonJPA jpa){
    this.firstName = jpa.getFirstName();
    this.lastName = jpa.getLastName();
    this.birthdate = jpa.getBirthdate();
    this.hasInsurance = jpa.getHasInsurance();
  }

}
