package com.test.test.repository;

import com.test.test.entity.Choice;
import com.test.test.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChoiceRepository extends JpaRepository<Choice, Long> {
    @Query(value = "SELECT * FROM choices WHERE user_id = ?1", nativeQuery = true)
    List<Choice> findByUserId(Long userId);

}
