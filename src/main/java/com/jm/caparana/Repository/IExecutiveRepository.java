package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Executive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IExecutiveRepository extends JpaRepository<Executive,Long> {
}
