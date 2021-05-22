package com.carolguin.itlg.tech.challenge.services;

import com.carolguin.itlg.tech.challenge.dto.Movie;
import com.carolguin.itlg.tech.challenge.dto.Person;
import com.carolguin.itlg.tech.challenge.model.MovieJPA;
import com.carolguin.itlg.tech.challenge.model.PersonJPA;
import com.carolguin.itlg.tech.challenge.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MovieService {
  @Value("${max.movies.per.person}")
  private int maxMoviesPerPerson;

  @Autowired
  private PersonRepository personRepository;

  private static final Logger LOGGER = LoggerFactory.getLogger(MovieService.class);


  public List<Movie> getPersonFavouriteMovies(Long personId) {
    LOGGER.info("Getting all favourite movies for person id {}", personId);

    List<Movie> movies = new ArrayList<>();

    Optional<PersonJPA> personJPA = personRepository.findById(personId);
    if (personJPA.isPresent()) {
      LOGGER.info("Person found -> {}", personId);
      List<MovieJPA> movieJPAList = personJPA.get().getFavouriteMovies();
      movies = movieJPAList.stream().map(mJPA -> new Movie(mJPA)).collect(Collectors.toList());
    }
    else {
      LOGGER.warn("Person not found -> {}", personId);
    }
    return movies;
  }


  public Optional<Movie> addFavouriteMovie(Long personId, Movie movie) {
    Optional<PersonJPA> personJPAOptional = personRepository.findById(personId);

    if (personJPAOptional.isPresent()) {
      PersonJPA personJPA = personJPAOptional.get();

      if (personJPA.getFavouriteMovies().size() < maxMoviesPerPerson) {
        personJPA.addMovie(new MovieJPA(movie.getTitle(), movie.getGenre()));
        PersonJPA personSaved = personRepository.save(personJPA);
        LOGGER.info("Movie added successfully to person ({} - {} {})", personSaved.getId(), personSaved.getFirstName(), personSaved.getLastName());
        return Optional.of(movie);
      }
      else {
        LOGGER.warn("Person can't have more than {} favourite movies", maxMoviesPerPerson);
        return Optional.empty();
      }
    }
    else {
      LOGGER.warn("Person not found -> {}", personId);
      return Optional.empty();
    }
  }


  public Optional<Movie> removeFavouriteMovie(Long personId, String movieTitle) {
    Optional<PersonJPA> personJPAOptional = personRepository.findById(personId);

    if (personJPAOptional.isPresent()) {
      PersonJPA personJPA = personJPAOptional.get();

      MovieJPA movieJPA = personJPA.getFavouriteMovies().stream().filter(mJPA -> mJPA.getTitle().equals(movieTitle)).findAny().orElse(null);

      if (movieJPA==null) {
        LOGGER.warn("Movie not found -> {}", movieTitle);
        return Optional.empty();
      }
      else {
        personJPA.removeMovie(movieJPA);
        PersonJPA personSaved = personRepository.save(personJPA);
        LOGGER.info("Movie removed successfully from person ({} - {} {})", personSaved.getId(), personSaved.getFirstName(), personSaved.getLastName());
        return Optional.of(new Movie(movieJPA));
      }
    }
    else {
      LOGGER.warn("Person not found -> {}", personId);
      return Optional.empty();
    }
  }

}
