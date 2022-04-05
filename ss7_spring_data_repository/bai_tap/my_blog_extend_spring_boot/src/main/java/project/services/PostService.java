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
    public Post findById(Integer id) {
        return iPostRepository.findById(id).orElse(null);
    }


    @Override
    public void update(Post post) {
        iPostRepository.save(post);
    }

    @Override
    public void remove(Integer id) {
        iPostRepository.deleteById(id);
    }

    @Override
    public List<Post> searchByContent(String contentSearch) {
        return iPostRepository.findPostByPostContentContaining(contentSearch);
    }

}
