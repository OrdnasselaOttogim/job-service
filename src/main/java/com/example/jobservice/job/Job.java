package com.example.jobservice.job;

import com.example.jobservice.user.User;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table
public class Job {
    @Id
    @SequenceGenerator(name = "job_sequence", sequenceName = "job_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_sequence")
    private Long id;
    private String title;
    private String description;
    private String address;
    private int payment;
    //User owner;


    public Job(String title, String description, String address, int payment) {
        this.title = title;
        this.description = description;
        this.address = address;
        this.payment = payment;
    }
}
