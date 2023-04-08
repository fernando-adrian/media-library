package com.fernandorobles.medialibrary.controller;

import com.fernandorobles.medialibrary.entity.MediaSource;
import com.fernandorobles.medialibrary.service.MediaSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sources")
public class MediaSourceController {

    @Autowired
    private MediaSourceService mediaSourceService;

    @GetMapping("/")
    public ResponseEntity<List<MediaSource>> getMediaSources(){
        List<MediaSource> sources = mediaSourceService.getMediaSources();
        if (sources.size() > 0)
            return new ResponseEntity<>(sources, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediaSource> getMediaSource(@PathVariable Long id){
        Optional<MediaSource> ms = mediaSourceService.findById(id);
        return ms.map((mediaSource)-> new ResponseEntity<>(mediaSource, HttpStatus.OK))
                .orElseGet(()->new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping("/")
    public ResponseEntity<MediaSource> addMediaSource(@RequestBody MediaSource source){
        MediaSource ms = mediaSourceService.addMediaSource(source);
        return new ResponseEntity<>(ms, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteMediaSource(@PathVariable Long id){
        mediaSourceService.deleteMediaSource(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediaSource> updateMediaSource(@RequestBody MediaSource source, @PathVariable Long id){
        MediaSource ms = mediaSourceService.updateMediaSource(source, id);
        if (ms.getId() != null){
            return new ResponseEntity<>(ms, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
