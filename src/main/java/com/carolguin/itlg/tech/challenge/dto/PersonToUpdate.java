package com.carolguin.itlg.tech.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import java.util.Date;

@Data
public class PersonToUpdate {

  @Min(value = 1, message = "Person's id should be greater than 0")
  @ApiModelProperty(name = "id", dataType = "Long", example = "1", notes = "Person's id")
  private Long id;

  @ApiModelProperty(name = "firstName", dataType = "String", example = "Carolina", notes = "Person's first name")
  @JsonProperty("first-name")
  private String firstName;

  @ApiModelProperty(name = "lastName", dataType = "String", example = "Olguín", notes = "Person's last name")
  @JsonProperty("last-name")
  private String lastName;

  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "birthdate", dataType = "Date", example = "1974-11-07", notes = "Person's birthdate")
  private Date birthdate;

  @ApiModelProperty(name = "hasInsurance", dataType = "Boolean", example = "true", notes = "If a person has insurance or not")
  @JsonProperty("has-insurance")
  private Boolean hasInsurance;
}
