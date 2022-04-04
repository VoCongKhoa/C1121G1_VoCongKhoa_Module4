package project.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.models.Song;
import project.repositories.ISongRepository;
import project.services.ISongService;

import java.util.List;

@Service
public class SongService implements ISongService {

    @Autowired
    private ISongRepository iSongRepository;

    @Override
    public void save(Song song) {
        this.iSongRepository.save(song);
    }

    @Override
    public List<Song> findAll() {
        return this.iSongRepository.findAll();
    }

    @Override
    public Song findById(Integer id) {
        return iSongRepository.findById(id);
    }

    @Override
    public void update(Song song) {
        iSongRepository.update(song);
    }

    @Override
    public void delete(int idDelete) {
        iSongRepository.delete(idDelete);
    }

    @Override
    public List<Song> searchByName(String nameSearch) {
        return iSongRepository.searchByName(nameSearch);
    }
}
