package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IClubRepository extends JpaRepository<Club,Long> {
}
