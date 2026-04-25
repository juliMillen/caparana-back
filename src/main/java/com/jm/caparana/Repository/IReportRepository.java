package com.jm.caparana.Repository;

import com.jm.caparana.Entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReportRepository extends JpaRepository<Report,Long> {
}
