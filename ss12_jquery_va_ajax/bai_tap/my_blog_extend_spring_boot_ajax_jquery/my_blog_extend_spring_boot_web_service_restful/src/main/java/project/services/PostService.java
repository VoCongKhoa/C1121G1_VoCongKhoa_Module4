package project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.models.Post;
import project.repositories.IPostRepository;

import java.util.List;

@Service
public class PostService implements IPostService {
    @Autowired
    IPostRepository iPostRepository;


    //ONLY PAGING
//    @Override
//    public Page<Post> findAll(Pageable pageable) {
//        int pageSize = pageable.getPageSize();
//        int currentPage = pageable.getPageNumber();
//        int startItem = currentPage * pageSize;
//        List<Post> list;
//
//        if (iPostRepository.findAll().size() < startItem) {
//            list = Collections.emptyList();
//        } else {
//            int toIndex = Math.min(startItem + pageSize, iPostRepository.findAll().size());
//            list = iPostRepository.findAll().subList(startItem, toIndex);
//        }
//
//        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), iPostRepository.findAll().size());
//    }

//
//    @Override
//    public Page<Post> findAllWithSort(Pageable pageable) {
//        Page<Post> postList = iPostRepository.findAllByOrderByPostDateModified(pageable);
//        return postList;
//    }
//
//    @Override
//    public Page<Post> findAllWithSearch(Pageable pageable, String valueSearch) {
//        return iPostRepository.findAllByPostTitleContaining(pageable,valueSearch);
//    }

    @Override
    public Page<Post> findAllWithSearch(Pageable pageable, String valueSearch) {
        return null;
    }

    @Override
    public Page<Post> findAllWithSort(Pageable pageable) {
        return null;
    }

    @Override
    public List<Post> findAllByCategory(int categoryId) {
        return iPostRepository.findAllByCategory(categoryId);
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
    public List<Post> searchByTitle(String contentSearch) {
        return iPostRepository.findAllByPostTitleSearch(contentSearch);
    }

//    @Override
//    public Page<Post> searchByContent(String contentSearch, Pageable pageable) {
//        return iPostRepository.findAllByPostContentSearch(contentSearch, pageable);
//    }

    @Override
    public Page<Post> findAll(Pageable pageable) {
        return iPostRepository.findAll(pageable);
    }

}
