package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Post;
import project.repositories.IPostRepository;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository iPostRepository;

    @Override
    public List<Post> findAll() {
        return iPostRepository.findAll();
    }

    @Override
    public void save(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public Post findById(Long id) {
        return iPostRepository.findById(id);
    }


    @Override
    public void update(Post post) {
        iPostRepository.update(post);
    }

    @Override
    public void remove(Long id) {
        iPostRepository.remove(id);
    }

    @Override
    public List<Post> searchByName(String nameSearch) {
        return iPostRepository.searchByName(nameSearch);
    }

    @Override
    public boolean insertWithStoredProcedure(Post post) {
        return iPostRepository.insertWithStoredProcedure(post);
    }
}
