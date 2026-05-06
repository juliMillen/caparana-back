package com.jm.caparana.Controller;

import com.jm.caparana.Entity.Report;
import com.jm.caparana.Service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("")
    public ResponseEntity<List<Report>> getReports(){
        List<Report> reportList = reportService.findAllReports();
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable Long id){
        return new ResponseEntity<>(reportService.findReportById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Report> createReport(@RequestBody Report report){
        return new ResponseEntity<>(reportService.save(report), HttpStatus.CREATED);
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable Long id, @RequestBody Report report){
        return new ResponseEntity<>(reportService.updateReport(id,report),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReport(@PathVariable Long id){
        reportService.deleteReport(id);
        return new ResponseEntity<>("Report deleted succesfully", HttpStatus.NOT_FOUND);
    }
}
