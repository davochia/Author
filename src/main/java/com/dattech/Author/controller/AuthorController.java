package com.dattech.Author.controller;


import com.dattech.Author.dto.AuthorDto;
import com.dattech.Author.exception.AuthorNotFoundException;
import com.dattech.Author.service.serviceImpl.AuthorServiceImpl;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("api/v1")
@RestController
public class AuthorController {

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    // Add new Author
    @ApiOperation(value="Add a new author to system", response= AuthorDto.class)
    @PostMapping("/author")
    public AuthorDto addAuthor(@RequestBody AuthorDto authorDto) {
        return authorServiceImpl.addAuthor(authorDto);
    }

    // Get Authors
    @ApiOperation(value="Get all authors from system", response= AuthorDto.class)
    @GetMapping("/authors")
    public List<AuthorDto> getAuthors()  {
        return authorServiceImpl.getAuthors();
    }


    // Get Author by id
    @ApiOperation(value="Get author from system find by id", response= AuthorDto.class)
    @GetMapping("/author/{id}")
    public AuthorDto getAuthor(@PathVariable Integer id) throws AuthorNotFoundException {
        return authorServiceImpl.findAuthorById(id);
    }


    // Edit Author info
    @ApiOperation(value="Edit author in the system", response= AuthorDto.class)
    @PutMapping("/author/{id}")
    public AuthorDto editAuthor(@PathVariable Integer id, @RequestBody AuthorDto newAuthor){
        return authorServiceImpl.modifyWikiCategory(id, newAuthor);
    }


    // Delete author
    @ApiOperation(value="Delete author from system", response= AuthorDto.class)
    @DeleteMapping("/author/{id}")
    public Boolean deleteAuthor(@PathVariable Integer id) {
        return authorServiceImpl.removeAuthor(id);
    }
}