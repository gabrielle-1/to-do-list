package com.gabrielle.crudtodolist.repository;

import com.gabrielle.crudtodolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>{
}
