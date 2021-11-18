package com.inplan.inplan;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.OffsetDateTime;

@Data
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    String userIdd;
    OffsetDateTime startDate;
    OffsetDateTime endDate;
    int categoryId;
    String description;
}
