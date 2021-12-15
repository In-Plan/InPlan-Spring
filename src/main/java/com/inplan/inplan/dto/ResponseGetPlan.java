package com.inplan.inplan.dto;


import com.inplan.inplan.dao.Plan;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseGetPlan {
    private List<Plan> plans;
}
