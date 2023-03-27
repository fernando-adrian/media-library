package com.fernandorobles.medialibrary.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class MediaItem {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String mediaName;
    @OneToMany(
            targetEntity = MediaAuthor.class,
            cascade = CascadeType.PERSIST
    )
    private List<MediaAuthor> mediaAuthor;
    private String mediaDescription;
    private MediaCollection mediaCollection;
    @OneToOne(
            targetEntity = MediaSource.class,
            cascade = CascadeType.PERSIST
    )
    private MediaSource source;
    private int mediaLength;
    private int progress;
    private boolean completed;
    private int rating;
    private String image;

//    {
//        1,
//        "The Last Of Us Remake",
//        "Naugthy Dog",
//        "El juego de ps5 de TloU",
//        "Game",
//        "PS5"
//        100,
//        0,
//        false,
//        0
//        "http://asdasd.as.asdas.jpg"
//    }

//    {
//        2,
//        "Winds of Winter",
//        "George R. R. Martin",
//        "sexta novela de cancion de hielo y fuego",
//        "pdf",
//        "Book",
//        2000,
//        123,
//        false,
//        0
//        "http://asdasd.as.asdas.jpg"
//    }

//    {
//        3,
//        "Attack on Titan",
//        "no tengo idea",
//        "ultima temporada de la serie",
//        "Crunchyroll",
//        "Series",
//        90,
//        70,
//        false,
//        0
//        "http://asdasd.as.asdas.jpg"
//    }

//    {
//        4,
//        "Everything Everywhere all at once",
//        "Daniel Kwan, Daniel Scheinert",
//        "pelicula ganadora del oscar 23",
//        "Amazon Prime",
//        "Movie",
//        130,
//        130,
//        true,
//        7
//        "http://asdasd.as.asdas.jpg"
//    }

}
