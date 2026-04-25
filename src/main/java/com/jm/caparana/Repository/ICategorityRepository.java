package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Categority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICategorityRepository extends JpaRepository<Categority,Long> {
}
