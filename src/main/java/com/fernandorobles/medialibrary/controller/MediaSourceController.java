package com.fernandorobles.medialibrary.controller;

import com.fernandorobles.medialibrary.advice.exceptions.ErrorResponse;
import com.fernandorobles.medialibrary.advice.exceptions.MediaSourceNotFoundException;
import com.fernandorobles.medialibrary.entity.MediaSource;
import com.fernandorobles.medialibrary.service.MediaSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/sources")
@Slf4j
public class MediaSourceController {

    @Value("${reflectoring.trace:false}")
    private boolean printStackTrace;
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
    public ResponseEntity<MediaSource> getMediaSource(@PathVariable Long id) throws MediaSourceNotFoundException {
        Optional<MediaSource> source = mediaSourceService.findById(id);
        return source.map((mediaSource)-> new ResponseEntity<>(mediaSource, HttpStatus.OK))
                .orElseThrow(()-> new MediaSourceNotFoundException("Media Source with id (" + id + ") not found"));

    }

    @ExceptionHandler(MediaSourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponse> handleMediaSourceNotFoundException(
            MediaSourceNotFoundException exception
    ){
        log.error("failed to find the requested element", exception);
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            MediaSourceNotFoundException exception,
            HttpStatus httpStatus
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                httpStatus.value(),
                exception.getMessage()
        );

        if (printStackTrace){
            errorResponse.setStackTrace(Arrays.toString(exception.getStackTrace()));
        }
        return ResponseEntity
                .status(httpStatus)
                .body(errorResponse);
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
