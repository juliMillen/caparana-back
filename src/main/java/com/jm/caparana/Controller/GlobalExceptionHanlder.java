package com.jm.caparana.Controller;

import com.jm.caparana.Exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHanlder {

    //Clase para tomar las diferentes excepciones desde el frontend

    @ExceptionHandler(ClubException.class)
    public ResponseEntity<String> handlerClubException(ClubException exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CategorityException.class)
    public ResponseEntity<String> handlerCategorityException(CategorityException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DisciplineException.class)
    public ResponseEntity<String> handlerDisciplineException(DisciplineException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExecutiveException.class)
    public ResponseEntity<String> handlerExecutiveException(ExecutiveException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GalleryException.class)
    public ResponseEntity<String>handlerGalleryException(GalleryException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhotoException.class)
    public ResponseEntity<String> handlerPhotoException(PhotoException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlayerException.class)
    public ResponseEntity<String> handlerPlayerException(PlayerException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ReportException.class)
    public ResponseEntity<String> handlerReportException(ReportException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SponsorException.class)
    public ResponseEntity<String> handlerSponsorException(SponsorException exception){
        return new ResponseEntity<>(exception.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
