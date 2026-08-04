package com.jm.caparana.Mapper;

import com.jm.caparana.DTO.*;
import com.jm.caparana.Entity.*;
import org.hibernate.engine.spi.CollectionEntry;

import java.util.stream.Collectors;

public class Mapper {

    //mapeo de Club a CLUBDTO

    static public ClubDTO mapToClubDTO(Club club){
        if(club == null){
            throw new IllegalArgumentException("Club is null");
        }
        return ClubDTO.builder()
                .idClub(club.getIdClub())
                .name(club.getName())
                .history(club.getHistory())
                .stadiumHistory(club.getStadiumHistory())
                .colorsHistory(club.getColorsHistory())
                .titles(club.getTitles())
                .build();
    }


    //mapeo de Categority a CATEGORITYDTO

    static public CategorityDTO mapToCategorityDTO(Categority categority){
        if(categority == null){
            throw new IllegalArgumentException("Categority is null");

        }
        var list = categority.getPlayerList().stream().map(players ->
                PlayerDTO.builder()
                        .idPlayer(players.getIdPlayer())
                        .name(players.getName())
                        .surname(players.getSurname())
                        .position(players.getPosition())
                        .num(players.getNum())
                        .urlImage(players.getUrlImage())
                        .build()
        ).collect(Collectors.toList());

        return CategorityDTO.builder()
                .idCategority(categority.getIdCategority())
                .nameCategority(categority.getNameCategority())
                .playerList(list)
                .build();

    }

    //mapeo de Player a PlayerDTO

    static public PlayerDTO mapToPlayerDTO(Player player){
        if(player == null){
            throw new IllegalArgumentException("Player is null");
        }



        return PlayerDTO.builder()
                .idPlayer(player.getIdPlayer())
                .name(player.getName())
                .surname(player.getSurname())
                .position(player.getPosition())
                .num(player.getNum())
                .urlImage(player.getUrlImage())
                .categority(mapToCategorityDTO(player.getCategority()))
                .build();

    }

    //mapeo de Discipline a DisciplineDTO

    static public DisciplineDTO mapToDisciplineDTO(Discipline discipline){
        if(discipline == null){
            throw new IllegalArgumentException("Discipline is null");
        }

        return DisciplineDTO.builder()
                .idDiscipline(discipline.getIdDispline())
                .nameDiscipline(discipline.getNameDiscipline())
                .description(discipline.getDescription())
                .schedule(discipline.getSchedule())
                .professorAsig(discipline.getProfessorAsig())
                .ubication(discipline.getUbication())
                .build();
    }

    //mapeo de Executive a ExecutiveDTO

    static public ExecutiveDTO mapToExecutiveDTO(Executive executive){
        if(executive == null){
            throw new IllegalArgumentException("Executive is null");
        }
        return ExecutiveDTO.builder()
                .idExecutive(executive.getIdExecutive())
                .name(executive.getName())
                .surname(executive.getSurname())
                .position(executive.getPosition())
                .urlImage(executive.getUrlImage())
                .build();
    }

    //mapeo de GalleryPhoto a GalleryPhotoDTO

    static public GalleryPhotoDTO mapToGalleryDTO(GalleryPhoto gallery){
        if(gallery == null){
            throw  new IllegalArgumentException("Gallery is null");
        }

        var list = gallery.getPhotos().stream().map(photos ->
            PhotoDTO.builder()
                    .idPhoto(photos.getIdPhoto())
                    .description(photos.getDescription())
                    .urlImage(photos.getUrlImage())
                    .build()
        ).collect(Collectors.toList());

        return GalleryPhotoDTO.builder()
                .idGallery(gallery.getIdGallery())
                .title(gallery.getTitle())
                .publicationDate(gallery.getPublicationDate())
                .photoDTOS(list)
                .build();
    }

    //mapeo de Report a ReportDTO

    static public ReportDTO mapToReportDTO(Report report){
        if(report == null){
            throw new IllegalArgumentException("Report is null");
        }

        return ReportDTO.builder()
                .idReport(report.getIdReport())
                .title(report.getTitle())
                .description(report.getDescription())
                .publicationDate(report.getPublicationDate())
                .urlImage(report.getUrlImage())
                .build();
    }

    //mapeo de Sponsor a SponsorDTO

    static public SponsorDTO mapToSponsorDTO(Sponsor sponsor){
        if(sponsor == null){
            throw new IllegalArgumentException("Sponsor is null");
        }

        return SponsorDTO.builder()
                .idSponsor(sponsor.getIdSponsor())
                .name(sponsor.getName())
                .urlImage(sponsor.getUrlImage())
                .build();
    }

    //mapeo de Photo a PhotoDTO

    static public PhotoDTO mapToPhotoDTO(Photo photo){
        if(photo == null){
            throw new IllegalArgumentException("Photo is null");
        }

        return PhotoDTO.builder()
                .idPhoto(photo.getIdPhoto())
                .description(photo.getDescription())
                .urlImage(photo.getUrlImage())
                .build();
    }
}
