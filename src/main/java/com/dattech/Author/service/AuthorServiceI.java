package com.dattech.Author.service;

import com.dattech.Author.dto.AuthorDto;

import java.util.List;

public interface AuthorServiceI {
    AuthorDto addAuthor(AuthorDto authorDto);
    AuthorDto findAuthorById(Integer id) ;
    List<AuthorDto> getAuthors( );
    AuthorDto modifyAuthor(Integer id, AuthorDto authorDto);
    Boolean removeAuthor(Integer id) ;
}
