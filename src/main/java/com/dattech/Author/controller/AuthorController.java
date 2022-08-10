package com.dattech.Author.controller;


import com.dattech.Author.dto.AuthorDto;
import com.dattech.Author.exception.AuthorNotFoundException;
import com.dattech.Author.service.serviceImpl.AuthorServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Slf4j

@RestController
@RequestMapping("api/authors")
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    //Without responseEntity

    // Add new Author
//    @Operation(summary="Add a new author to system", response= AuthorDto.class)
//    @PostMapping
//    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
//        return authorServiceImpl.addAuthor(authorDto);
//    }
//
//    // Get Authors
//    @Operation(summary="Get all authors from system", response= AuthorDto.class)
//    @GetMapping
//    public List<AuthorDto> getAuthors()  {
//        return authorServiceImpl.getAuthors();
//    }
//
//
//    // Get Author by id
//    @Operation(summary="Get author from system find by id", response= AuthorDto.class)
//    @GetMapping("/{id}")
//    public AuthorDto getAuthorById(@PathVariable Integer id) throws AuthorNotFoundException {
//        return authorServiceImpl.findAuthorById(id);
//    }
//
//
//    // Edit Author info
//    @Operation(summary="Edit author in the system", response= AuthorDto.class)
//    @PutMapping("/{id}")
//    public AuthorDto editAuthorById(@PathVariable Integer id, @RequestBody AuthorDto newAuthor){
//        return authorServiceImpl.modifyAuthor(id, newAuthor);
//    }
//
//
//    // Delete author
//    @Operation(summary="Delete author from system", response= AuthorDto.class)
//    @DeleteMapping("/{id}")
//    public Boolean deleteAuthor(@PathVariable Integer id) {
//        return authorServiceImpl.removeAuthor(id);
//    }


    //With responseEntity

    @GetMapping // Returns all authors
    @Operation(summary="Get all authors from system")
    public ResponseEntity<List<AuthorDto>> getAll() {
        return new ResponseEntity<>(authorServiceImpl.getAuthors(), HttpStatus.OK);
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author Found",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AuthorDto.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Author not found",
                    content = @Content) })
    @Operation(summary="Get author from system find by id")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthorById(@Parameter(description = "id of author to be searched") @PathVariable Integer id) throws AuthorNotFoundException {
        return new ResponseEntity<>(authorServiceImpl.findAuthorById(id), HttpStatus.OK);
    }


    @PostMapping // Create  new author
    @Operation(summary="Add a new author to system")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto authorDto) {
        return new ResponseEntity<>(authorServiceImpl.addAuthor(authorDto) , HttpStatus.CREATED);
    }


    @PutMapping("/{id}") // Edith author
    @Operation(summary="Edit author in the system")
    public ResponseEntity<AuthorDto> editAuthor(@PathVariable Integer id, @RequestBody AuthorDto newAuthor) {
        return new ResponseEntity<>(authorServiceImpl.modifyAuthor(id, newAuthor), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}") // Delete author
    @Operation(summary="Delete author from system")
    public ResponseEntity<String> deleteAuthor(@PathVariable Integer id) {
        authorServiceImpl.removeAuthor(id);
        return new ResponseEntity<>("Author with ID: " + id + " deleted", HttpStatus.NO_CONTENT);
    }

}