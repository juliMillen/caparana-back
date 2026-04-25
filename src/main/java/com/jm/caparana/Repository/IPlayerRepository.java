package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends JpaRepository<Player,Long> {
}
