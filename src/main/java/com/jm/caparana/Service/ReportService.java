package com.jm.caparana.Service;

import com.jm.caparana.DTO.ReportDTO;
import com.jm.caparana.Entity.Report;
import com.jm.caparana.Mapper.Mapper;
import com.jm.caparana.Repository.IReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {

    @Autowired
    private IReportRepository reportRepository;

    public List<ReportDTO> findAllReports(){
        return reportRepository.findAll().stream()
                .map(Mapper::mapToReportDTO)
                .toList();
    }

    public ReportDTO findReportById(Long id){
        if(id == null || id == 0){
            throw new RuntimeException("id invalid");
        }
        return Mapper.mapToReportDTO(reportRepository.findById(id).orElseThrow(() -> new RuntimeException("report not found")));
    }

    public ReportDTO saveDTO(ReportDTO newReport){

        Report toCreate = Report.builder()
                .title(newReport.getTitle())
                .description(newReport.getDescription())
                .publicationDate(newReport.getPublicationDate())
                .urlImage(newReport.getUrlImage())
                .build();
        return Mapper.mapToReportDTO(reportRepository.save(toCreate));
    }

    public ReportDTO updateReport(Long idReport, ReportDTO reportDTO){
        Report toUpdate = reportRepository.findById(idReport).orElseThrow(() -> new RuntimeException("Report not found"));
        toUpdate.setTitle(reportDTO.getTitle());
        toUpdate.setDescription(reportDTO.getDescription());
        toUpdate.setPublicationDate(reportDTO.getPublicationDate());
        toUpdate.setUrlImage(reportDTO.getUrlImage());
        return Mapper.mapToReportDTO(reportRepository.save(toUpdate));
    }

    public void deleteReport(Long idReport){
        if(idReport == null || idReport == 0){
            throw  new RuntimeException("id invalid");
        }
        reportRepository.deleteById(idReport);
    }
}
