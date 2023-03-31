package com.fernandorobles.medialibrary.service;

import com.fernandorobles.medialibrary.entity.MediaItem;
import com.fernandorobles.medialibrary.entity.MediaSource;
import com.fernandorobles.medialibrary.repository.MediaSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaSourceService {

    @Autowired
    private MediaSourceRepository mediaSourceRepository;

    public List<MediaSource> getMediaSources(){
        return mediaSourceRepository.findAll();
    }

    public MediaSource addMediaSource(MediaSource source) {
        return mediaSourceRepository.save(source);
    }

    public Optional<MediaSource> findById(Long id) {
        return mediaSourceRepository.findById(id);
    }

    public MediaSource updateMediaSource(MediaSource source, Long id) {
        Optional<MediaSource> sourceFound = mediaSourceRepository.findById(id);
        if(sourceFound.isPresent()){
            return mediaSourceRepository.save(source);
        }else {
            return new MediaSource();
        }
    }

    public void deleteMediaSource(Long id) {
        MediaSource mediaSource = mediaSourceRepository.getReferenceById(id);
        mediaSourceRepository.delete(mediaSource);
    }
}
