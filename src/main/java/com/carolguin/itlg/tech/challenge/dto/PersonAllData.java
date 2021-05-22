package com.carolguin.itlg.tech.challenge.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper=false)
public class PersonAllData extends Person{

  @ApiModelProperty(name = "id", dataType = "Long", example = "1", notes = "Person's id")
  private Long id;

  @ApiModelProperty(name = "favouriteMovies", dataType = "List<Movie>", notes = "List of favourite movies")
  private List<Movie> favouriteMovies;
}
