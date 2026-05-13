package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISponsorRepository extends JpaRepository<Sponsor,Long> {
}
