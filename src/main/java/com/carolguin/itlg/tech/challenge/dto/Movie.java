package com.carolguin.itlg.tech.challenge.dto;

import com.carolguin.itlg.tech.challenge.model.MovieJPA;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Movie {

  @NotBlank(message = "Movie's title can't be blank")
  @ApiModelProperty(name = "title", dataType = "String", example = "The Lord of the Rings", notes = "Movie's title")
  private String title;

  @ApiModelProperty(name = "genre", dataType = "String", example = "fantasy", notes = "Movie's genre")
  private String genre;

  public Movie(){}

  public Movie(MovieJPA jpa){
    this.setTitle(jpa.getTitle());
    this.setGenre(jpa.getGenre());
  }

}
