package com.example.jobservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Map;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Job {
    @Id
    //@SequenceGenerator(name = "job_sequence", sequenceName = "job_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.TABLE) //, generator = "job_sequence")
    private Long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String address;
    private String category;

    @Transient
    private Map<String, Itinerary> itineraries;

    public Job(String title, String description, String address, String category) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.category = category;
    }


    public Map<String, Itinerary> getItineraries() {
        return itineraries;
    }

    public void setItineraries(Map<String, Itinerary> itineraries) {
        this.itineraries = itineraries;
    }
}
