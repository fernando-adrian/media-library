package com.fernandorobles.medialibrary.controller;

import com.fernandorobles.medialibrary.entity.MediaItem;
import com.fernandorobles.medialibrary.service.MediaItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/media")
@Slf4j
public class MediaItemController {

    @Autowired
    private MediaItemService mediaItemService;

    @GetMapping("/")
    public ResponseEntity<List<MediaItem>> getMediaItems(){
        List<MediaItem> items = mediaItemService.getMediaItems();
        if(items.size() > 0)
            return new ResponseEntity<>(items, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaItem> getMediaItem(@PathVariable Long id){
        Optional<MediaItem> item = mediaItemService.getMediaItem(id);
        return item.map(mediaItem -> new ResponseEntity<>(mediaItem, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/")
    public ResponseEntity<MediaItem> addMediaItem(@RequestBody MediaItem item){
        log.info("addMediaItem: " + item.toString());
        MediaItem mi = mediaItemService.addMediaItem(item);
        return new ResponseEntity<>(mi, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMediaItem(@PathVariable Long id){
        mediaItemService.deleteMediaItem(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaItem> updateMediaItem(@PathVariable Long id, @RequestBody MediaItem item){
        MediaItem updatedItem = mediaItemService.updateMediaItem(id, item);
        if(updatedItem.getId() != null)
            return new ResponseEntity<>(updatedItem, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
