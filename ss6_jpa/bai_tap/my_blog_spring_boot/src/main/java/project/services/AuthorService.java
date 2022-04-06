package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import project.models.Author;
import project.models.Post;
import project.repositories.IAuthorRepository;

import java.util.List;

@Repository
public class AuthorService implements IAuthorService{

    @Autowired
    IAuthorRepository iAuthorRepository;
    @Override

    public List<Author> findAll() {
        return iAuthorRepository.findAll();
    }

    @Override
    public void save(Author author) {

    }

    @Override
    public Author findById(Integer id) {
        return null;
    }

    @Override
    public void update(Author author) {

    }

    @Override
    public void remove(Integer id) {

    }

    @Override
    public List<Author> searchByName(String nameSearch) {
        return null;
    }

}
