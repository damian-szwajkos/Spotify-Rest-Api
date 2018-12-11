package java_advance.spring_boot_spotify.service;

import java_advance.spring_boot_spotify.model.Playlist;
import java_advance.spring_boot_spotify.model.Song;
import java_advance.spring_boot_spotify.repository.SongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService implements SongServiceInterface {
    private SongRepository songRepository;

    @Autowired
    public SongService(SongRepository songRepository){
        this.songRepository = songRepository;
    }

    @Override
    public Optional<Song> getSongById(Long id) {
        return this.songRepository.findById(id);
    }

    @Override
    public List<Song> getAllSongs() {
        return this.songRepository.findAll();
    }

    @Override
    public void deleteSongById(Long id) {
        this.songRepository.deleteById(id);
    }

    @Override
    public Song addSong(List<String> songDetails) {
        Song song = new Song(songDetails.get(0), songDetails.get(1), songDetails.get(2));
        return this.songRepository.save(song);
    }

    @Override
    public void archiveSong(Long id) {
        this.songRepository.findById(id).orElse(null).setActive(false);
    }

    @Override
    public void addSongToPLaylist(Long id, Long playlistId) {
        this.songRepository.findById(id).orElse(null).addToPlaylist(playlistId);
    }
}