package com.carolguin.itlg.tech.challenge.controller;

import com.carolguin.itlg.tech.challenge.dto.Person;
import com.carolguin.itlg.tech.challenge.dto.PersonToUpdate;
import com.carolguin.itlg.tech.challenge.services.PersonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Api(tags = "Person")
@RestController
@RequestMapping("/persons")
public class PersonController {

  @Autowired
  private PersonService personService;


  @ApiOperation(value = "Create a new person record", response= Person.class)
  @ApiResponses(value = {
      @ApiResponse(code = 201, message = "Created"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 409, message = "Conflict: id already exists"),
  })
  @PostMapping(value = "/{id}")
  public ResponseEntity<Person> create(@NotNull @Valid @RequestBody Person person) {
    Optional<Person> personOptional = personService.create(person);
    if (personOptional.isPresent()){
      return ResponseEntity.ok(personOptional.get());
    }
    else {
      return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
  }


  @ApiOperation(value = "Update an existing person record", response = Person.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Not found"),
  })
  @PutMapping("/{id}")
  public ResponseEntity<Person> update(@NotNull @PathVariable("id") Long id,
                                       @NotNull @Valid @RequestBody PersonToUpdate personToUpdate){
    Optional<Person> personOptional = personService.update(id, personToUpdate);
    if (personOptional.isPresent()){
      return ResponseEntity.ok(personOptional.get());
    }
    else {
      return ResponseEntity.notFound().build();
    }
  }

  @ApiOperation(value = "Delete an existing person record", response = Person.class)
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Success"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Not found"),
  })
  @DeleteMapping("/{id}")
  public ResponseEntity delete(@NotNull @PathVariable("id") Long id){
    Optional<Person> personOptional = personService.delete(id);
    if (personOptional.isPresent()){
      return ResponseEntity.ok().build();
    }
    else {
      return ResponseEntity.notFound().build();
    }
  }


  @ApiOperation(value = "Get all persons sorted by lastName and firstName")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @GetMapping(value="/all")
  public ResponseEntity<List<Person>> getAll(){
    return ResponseEntity.ok(personService.getAll());
  }


  @ApiOperation(value = "Get a person by id")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
      @ApiResponse(code = 404, message = "Not found"),
  })
  @GetMapping(value="/by-id/{id}")
  public ResponseEntity<Person> getById(@NotNull @PathVariable Long id){
    Optional<Person> personOptional = personService.getById(id);
    if (!personOptional.isPresent()){
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(personOptional.get());
  }


  @ApiOperation(value = "Find persons by firstName")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "Ok"),
      @ApiResponse(code = 400, message = "Bad Request"),
  })
  @GetMapping(value="/by-name/{name}")
  public ResponseEntity<List<Person>> getByFirstName(@NotNull @PathVariable String name){
    return ResponseEntity.ok(personService.getByFirstName(name));
  }


}
