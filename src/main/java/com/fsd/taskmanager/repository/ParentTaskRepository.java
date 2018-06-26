package com.fsd.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fsd.taskmanager.entities.ParentTask;

@Repository
public interface ParentTaskRepository extends JpaRepository<ParentTask, Integer>{

}