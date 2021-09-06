package com.example.myspringapp.model;

import com.example.myspringapp.validation.ValidateEmail;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.Id;

import java.time.LocalDate;
public class Album {

    @Id
    private String albumId;
    @Length(max = 10)
    private String name;
    @ValidateEmail
    private String createdBy;
    private String coverPicUrl;
    private LocalDate dateCreated;

    public LocalDate getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Album(String name, String createdBy, String coverPicUrl, LocalDate dateCreated) {

        this.name = name;
        this.createdBy = createdBy;
        this.coverPicUrl = coverPicUrl;
        this.dateCreated = dateCreated;
    }

    public String getAlbumId() {
        return albumId;
    }

    public void setAlbumId(String albumId) {
        this.albumId = albumId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCoverPicUrl() {
        return coverPicUrl;
    }

    public void setCoverPicUrl(String coverPicUrl) {
        this.coverPicUrl = coverPicUrl;
    }
}
