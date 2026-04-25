package com.jm.caparana.Repository;

import com.jm.caparana.Entity.GalleryPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IGalleryRepository extends JpaRepository<GalleryPhoto,Long> {
}
