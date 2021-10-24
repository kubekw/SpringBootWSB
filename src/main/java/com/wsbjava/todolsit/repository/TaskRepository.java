package com.wsbjava.todolsit.repository;

import com.wsbjava.todolsit.model.Task;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.time.LocalDate;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {


    @Query(value = "Select t from Task t where t.deadline > :deadlineAfter and t.isVisible=true")
    Iterable<Task> findAllByDeadlineAfter(LocalDate deadlineAfter, Sort sort);


    Iterable<Task> findAllByIsVisibleIsTrue();

    Optional<Task> findTaskByIdEqualsAndIsVisibleIsTrue(Integer id);


}
