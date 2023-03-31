package com.fernandorobles.medialibrary.service;

import com.fernandorobles.medialibrary.entity.MediaItem;
import com.fernandorobles.medialibrary.repository.MediaItemRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaItemService {

    @Autowired
    private MediaItemRepository mediaItemRepository;

    public Optional<MediaItem> getMediaItem(Long id) {
        return mediaItemRepository.findById(id);
    }

    public List<MediaItem> getMediaItems(){
        return mediaItemRepository.findAll();
    }

    public MediaItem addMediaItem(MediaItem item){
        return mediaItemRepository.save(item);
    }

    public void deleteMediaItem(Long id){

        MediaItem mediaItem = mediaItemRepository.getReferenceById(id);
        mediaItemRepository.delete(mediaItem);

    }

    public MediaItem updateMediaItem(Long id, MediaItem item) {

        Optional<MediaItem> itemFound = mediaItemRepository.findById(id);

        if(itemFound.isPresent()){
            return mediaItemRepository.save(item);
        } else {
            return new MediaItem();
        }

    }
}
