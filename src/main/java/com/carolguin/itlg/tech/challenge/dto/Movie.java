package com.carolguin.itlg.tech.challenge.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Movie {

  @ApiModelProperty(name = "title", dataType = "String", example = "The Lord of the Rings", notes = "Movie's title")
  private String title;

  @ApiModelProperty(name = "genre", dataType = "String", example = "fantasy", notes = "Movie's genre")
  private String genre;

}
