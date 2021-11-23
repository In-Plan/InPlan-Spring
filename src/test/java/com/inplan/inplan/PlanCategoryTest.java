package com.inplan.inplan;

import com.inplan.inplan.dao.PlanCategory;
import com.inplan.inplan.repository.PlanCategoryRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class PlanCategoryTest {
    @Autowired
    PlanCategoryRepository planCategoryRepository;

    @Test
    public void createPlanCategory() {
        PlanCategory planCategory = new PlanCategory(0L, "운동");
        planCategoryRepository.save(planCategory);
    }

    @Test
    public void selectPlanCategory() {
        PlanCategory planCategory = new PlanCategory(0L, "운동");
        planCategory = planCategoryRepository.save(planCategory);

        Optional<PlanCategory> selectPlanCategory = planCategoryRepository.findById(planCategory.getId());
        System.out.println("selectPlanCategory = " + selectPlanCategory);

        assertThat(selectPlanCategory.isPresent()).isTrue();
        assertThat(selectPlanCategory.get().getName()).isEqualTo("운동");
    }

    @Test
    public void updatePlanCategory() {
        PlanCategory planCategory = new PlanCategory(0L, "운동");
        planCategory = planCategoryRepository.save(planCategory);

        planCategory.setName("공부");
        planCategory = planCategoryRepository.save(planCategory);

        Optional<PlanCategory> updatedPlanCategory = planCategoryRepository.findById(planCategory.getId());
        System.out.println("updatedPlanCategory = " + updatedPlanCategory);

        assertThat(updatedPlanCategory.isPresent()).isTrue();
        assertThat(updatedPlanCategory.get().getName()).isEqualTo("공부");
    }

    @Test
    public void deletePlanCategory() {
        PlanCategory planCategory = new PlanCategory(0L, "운동");
        planCategory = planCategoryRepository.save(planCategory);

        planCategoryRepository.deleteById(planCategory.getId());

        Optional<PlanCategory> deletedPlanCategory = planCategoryRepository.findById(planCategory.getId());
        System.out.println("deletedPlanCategory = " + deletedPlanCategory);

        assertThat(deletedPlanCategory.isPresent()).isFalse();
    }
}
