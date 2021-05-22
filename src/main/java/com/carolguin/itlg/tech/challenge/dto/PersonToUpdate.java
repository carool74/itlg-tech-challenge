package com.carolguin.itlg.tech.challenge.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Optional;

@Data
public class PersonToUpdate {

  @NotNull
  @Min(value = 1, message = "Person's id should be greater than 0")
  @ApiModelProperty(name = "id", dataType = "long", example = "1", notes = "Person's id")
  private Long id;

  @ApiModelProperty(name = "firstName", dataType = "Optional<String>", example = "Carolina", notes = "Person's first name")
  private Optional<String> firstName;

  @ApiModelProperty(name = "lastName", dataType = "Optional<String>", example = "Olguín", notes = "Person's last name")
  private Optional<String> lastName;

  @JsonFormat(pattern="yyyy-MM-dd")
  @ApiModelProperty(name = "birthdate", dataType = "Optional<Date>", example = "1974-11-07", notes = "Person's birthdate")
  private Optional<Date> birthdate;

  @ApiModelProperty(name = "hasInsurance", dataType = "boolean", example = "true", notes = "If a person has insurance or not")
  private Optional<Boolean> hasInsurance;
}
