package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPhotoRepository extends JpaRepository<Photo,Long> {
}
