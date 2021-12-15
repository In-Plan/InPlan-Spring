package com.inplan.inplan.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Plan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "user_id")
    @ManyToOne
    private User user;
    private OffsetDateTime startDate;
    private OffsetDateTime endDate;
    @JoinColumn(name = "plan_category_id")
    @ManyToOne(cascade = CascadeType.ALL)
    private PlanCategory planCategory;
    private String description;
}
