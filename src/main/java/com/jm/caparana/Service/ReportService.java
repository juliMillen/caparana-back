package com.jm.caparana.Service;

import com.jm.caparana.Entity.Report;
import com.jm.caparana.Repository.IReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private IReportRepository reportRepository;

    public List<Report> findAllReports(){
        return reportRepository.findAll();
    }

    public Report findReportById(Long id){
        if(id == null || id == 0){
            throw new RuntimeException("id invalid");
        }
        return reportRepository.findById(id).orElseThrow(() -> new RuntimeException("report not found"));
    }

    public Report save(Report newReport){
        return reportRepository.save(newReport);
    }

    public Report updateReport(Long idReport, Report report){
        Report toSearch = reportRepository.findById(idReport).orElse(null);
        if(toSearch == null){
            throw new RuntimeException("report not found");
        }
        toSearch.setDescription(report.getDescription());
        toSearch.setTitle(report.getTitle());
        toSearch.setUrlImage(report.getUrlImage());
        toSearch.setPublicationDate(report.getPublicationDate());
        reportRepository.save(toSearch);
        return toSearch;
    }

    public void deleteReport(Long idReport){
        if(idReport == null || idReport == 0){
            throw  new RuntimeException("id invalid");
        }
        reportRepository.deleteById(idReport);
    }
}
