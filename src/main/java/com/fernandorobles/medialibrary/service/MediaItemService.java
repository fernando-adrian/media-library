package com.fernandorobles.medialibrary.service;

import com.fernandorobles.medialibrary.entity.MediaItem;
import com.fernandorobles.medialibrary.repository.MediaItemRepository;
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

    public boolean deleteMediaItem(Long id){

        Optional<MediaItem> mediaItem = Optional.of(mediaItemRepository.getReferenceById(id));
        mediaItem.ifPresent((item) -> mediaItemRepository.delete(item));
        return mediaItem.isPresent();
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
