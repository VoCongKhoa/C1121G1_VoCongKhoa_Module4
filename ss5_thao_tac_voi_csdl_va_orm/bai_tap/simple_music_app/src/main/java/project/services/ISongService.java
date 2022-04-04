package project.services;

import project.models.Song;

import java.util.List;

public interface ISongService {
    void save(Song song);
    List<Song> findAll();
    Song findById(Integer id);

    void update(Song song);

    void delete(int idDelete);

    List<Song> searchByName(String nameSearch);
}
