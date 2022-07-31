package com.dattech.Author.service;


import com.dattech.Author.dto.AuthorDto;
import com.dattech.Author.exception.AuthorNotFoundException;

import java.util.List;

public interface AuthorServiceI {
    AuthorDto addAuthor(AuthorDto authorDto);
    AuthorDto findAuthorById(Integer id) throws AuthorNotFoundException;
    List<AuthorDto> getAuthors( );
    AuthorDto modifyWikiCategory(Integer id, AuthorDto authorDto);
    Boolean removeAuthor(Integer id) ;
}
