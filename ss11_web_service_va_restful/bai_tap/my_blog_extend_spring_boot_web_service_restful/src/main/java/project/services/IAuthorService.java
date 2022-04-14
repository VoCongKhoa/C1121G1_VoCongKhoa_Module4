package project.services;

import project.models.Author;
import project.models.Post;

import java.util.List;

public interface IAuthorService {
    List<Author> findAll();

    void save(Author post);

    Author findById(Integer id);

    void update(Author post);

    void remove(Integer id);

    List<Author> searchByName(String nameSearch);

}
