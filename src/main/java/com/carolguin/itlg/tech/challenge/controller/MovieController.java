package com.carolguin.itlg.tech.challenge.controller;

import com.carolguin.itlg.tech.challenge.dto.Movie;
import com.carolguin.itlg.tech.challenge.dto.Person;
import com.carolguin.itlg.tech.challenge.dto.PersonToUpdate;
import com.carolguin.itlg.tech.challenge.services.MovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Api(tags = "Movie")
@RestController
@RequestMapping("/movies")
public class MovieController {

  @Autowired
  private MovieService movieService;


  @ApiOperation(value = "Get a person's favourite movies")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @GetMapping(value="/person/{id}")
  public ResponseEntity<List<Movie>> getByPersonId(@NotNull @PathVariable Long id){
    return ResponseEntity.ok(movieService.getPersonFavouriteMovies(id));
  }


  @ApiOperation(value = "Add a favourite movie to a person", response = Movie.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Not found"),
  })
  @PutMapping("/person/{id}/add")
  public ResponseEntity<Movie> addFavouriteMovie(@NotNull @PathVariable("id") Long id,
                                                 @NotNull @Valid @RequestBody Movie movie){
    Optional<Movie> movieOptional = movieService.addFavouriteMovie(id, movie);
    if (movieOptional.isPresent()){
      return ResponseEntity.ok(movieOptional.get());
    }
    else {
      return ResponseEntity.notFound().build();
    }
  }


  @ApiOperation(value = "Remove a favourite movie from a person", response = Movie.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Not found"),
  })
  @PutMapping("/person/{id}/remove/{title}")
  public ResponseEntity<Movie> removeFavouriteMovie(@NotNull @PathVariable("id") Long id,
                                                    @NotBlank @PathVariable("title") String title){
    Optional<Movie> movieOptional = movieService.removeFavouriteMovie(id, title);
    if (movieOptional.isPresent()){
      return ResponseEntity.ok(movieOptional.get());
    }
    else {
      return ResponseEntity.notFound().build();
    }
  }

}
