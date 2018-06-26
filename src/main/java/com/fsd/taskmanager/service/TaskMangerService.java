package com.fsd.taskmanager.service;

import java.util.List;
import java.util.Optional;

import com.fsd.taskmanager.entities.ParentTask;
import com.fsd.taskmanager.model.TaskDetails;

public interface TaskMangerService {
	void addTask(TaskDetails taskDetails);

	List<TaskDetails> getAllTasks();

	Optional<ParentTask> getTaskDetailsById(int taskId);

	void updateTaskDetails(TaskDetails taskDetails);

}
