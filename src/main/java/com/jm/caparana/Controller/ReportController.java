package com.jm.caparana.Controller;

import com.jm.caparana.DTO.ReportDTO;
import com.jm.caparana.Entity.Report;
import com.jm.caparana.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("")
    public ResponseEntity<List<ReportDTO>> getReports(){
        List<ReportDTO> reportList = reportService.findAllReports();
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReportDTO> getReportById(@PathVariable Long id){
        return new ResponseEntity<>(reportService.findReportById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    @PreAuthorize("hasAuthority('CREATE')")
    public ResponseEntity<ReportDTO> createReport(@RequestParam("title") String title, @RequestParam("description") String description,
                                                  @RequestParam("publicationDate") LocalDate publicationDate, @RequestParam("image")MultipartFile image)throws IOException {
        return new ResponseEntity<>(reportService.saveDTO(title,description,publicationDate,image), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    @PreAuthorize("hasAuthority('UPDATE')")
    public ResponseEntity<ReportDTO> updateReport(@PathVariable Long id, @RequestBody ReportDTO report){
        return new ResponseEntity<>(reportService.updateReport(id,report),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('DELETE')")
    public ResponseEntity<String> deleteReport(@PathVariable Long id){
        reportService.deleteReport(id);
        return new ResponseEntity<>("Report deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
