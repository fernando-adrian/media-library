package com.fernandorobles.medialibrary.repository;

import com.fernandorobles.medialibrary.entity.MediaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaItemRepository extends JpaRepository<MediaItem, Long>{

}
