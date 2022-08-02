package com.dattech.Author.service.serviceImpl;

import com.dattech.Author.dto.AuthorDto;
import com.dattech.Author.exception.AuthorNotFoundException;
import com.dattech.Author.model.Author;
import com.dattech.Author.repository.AuthorRepository;
import com.dattech.Author.service.AuthorServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorServiceI {
    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public AuthorDto addAuthor(AuthorDto authorDto) {
        if (authorDto == null)return null;
        Author author = AuthorDto.getAuthor(authorDto);
        return AuthorDto.getAuthorDto((Author) authorRepository.save(author));

    }

    @Override
    public AuthorDto findAuthorById(Integer id)  throws AuthorNotFoundException{
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        return optionalAuthor.map(AuthorDto::getAuthorDto).orElseThrow(() -> new AuthorNotFoundException(id));
    }


    @Override
    public List<AuthorDto> getAuthors() {
        List<Author> authors = (List<Author>) authorRepository.findAll();
        List<AuthorDto> authorDtos = new ArrayList<>();
        authors.forEach(author -> authorDtos.add(AuthorDto.getAuthorDto(author)));
        return authorDtos;
    }

    @Override
    public AuthorDto modifyWikiCategory(Integer id, AuthorDto authorDto)  {
        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if(optionalAuthor.isEmpty())return null;

        Author author = optionalAuthor.get();

        if(authorDto == null) return null;

        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());
        author.setPhone(authorDto.getPhone());
        author.setEmail(authorDto.getEmail());
        //author.setWikis(authorDto.);

        return AuthorDto.getAuthorDto((Author) authorRepository.save(author));

    }

    @Override
    public Boolean removeAuthor(Integer id) {
        Optional<Author> optionalAuthor = authorRepository.findById(id);
        if (optionalAuthor.isEmpty())return false;

        Author author = optionalAuthor.get();
        authorRepository.delete(author);
        return true;
    }
}
