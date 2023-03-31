package com.fernandorobles.medialibrary.repository;

import com.fernandorobles.medialibrary.entity.MediaSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaSourceRepository extends JpaRepository<MediaSource, Long> {
}
