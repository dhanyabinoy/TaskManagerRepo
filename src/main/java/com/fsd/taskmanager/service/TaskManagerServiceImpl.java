package com.fsd.taskmanager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fsd.taskmanager.entities.ParentTask;
import com.fsd.taskmanager.entities.Task;
import com.fsd.taskmanager.model.TaskDetails;
import com.fsd.taskmanager.repository.ParentTaskRepository;
import com.fsd.taskmanager.util.TaskManagerUtil;

@Service
public class TaskManagerServiceImpl implements TaskMangerService {
	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Override
	public void addTask(TaskDetails taskDetails) {
		ParentTask parentTask = mapTaskDetailsForDB(taskDetails);
		parentTaskRepository.save(parentTask);
	}

	private ParentTask mapTaskDetailsForDB(TaskDetails taskDetails) {
		ParentTask parentTask = new ParentTask();
		parentTask.setName(taskDetails.getParentTask());
		Task task = new Task();
		task.setName(taskDetails.getTask());
		task.setPriority(Integer.valueOf(taskDetails.getPriority()));
		task.setStartDate(TaskManagerUtil.toDate(taskDetails.getStartDate()));
		task.setEndDate(TaskManagerUtil.toDate(taskDetails.getEndDate()));
		task.setParentTask(parentTask);
		parentTask.getTasks().add(task);
		return parentTask;
	}

	@Override
	public List<TaskDetails> getAllTasks() {
		List<TaskDetails> taskDetails = new ArrayList<>();
		List<ParentTask> tasks = parentTaskRepository.findAll();
		maFromDBToTaskDetails(taskDetails, tasks);
		return taskDetails;
	}

	private void maFromDBToTaskDetails(List<TaskDetails> taskDetails, List<ParentTask> tasks) {
		for (ParentTask parentTask : tasks) {
			TaskDetails taskDetail = new TaskDetails();
			taskDetail.setId(String.valueOf(parentTask.getId()));
			taskDetail.setParentTask(parentTask.getName());
			for (Task task : parentTask.getTasks()) {
				taskDetail.setTask(task.getName());
				taskDetail.setPriority(String.valueOf(task.getPriority()));
				taskDetail.setStartDate(TaskManagerUtil.dateToString(task.getStartDate()));
				taskDetail.setEndDate(TaskManagerUtil.dateToString(task.getEndDate()));
				taskDetails.add(taskDetail);
			}

		}

	}

	@Override
	public void updateTaskDetails(TaskDetails taskDetails) {
		Optional<ParentTask> parenttaskObj = getTaskDetailsById(Integer.valueOf(taskDetails.getId()));
		if (parenttaskObj.isPresent()) {
			parenttaskObj.get().setName(taskDetails.getParentTask());
			for (Task task : parenttaskObj.get().getTasks()) {
				task.setName(taskDetails.getTask());
				task.setPriority(Integer.valueOf(taskDetails.getPriority()));
				task.setStartDate(TaskManagerUtil.toDate(taskDetails.getStartDate()));
				task.setEndDate(TaskManagerUtil.toDate(taskDetails.getEndDate()));
				task.setParentTask(parenttaskObj.get());
			}
		}
		parentTaskRepository.save(parenttaskObj.get());
	}

	@Override
	public Optional<ParentTask> getTaskDetailsById(int taskId) {
		return parentTaskRepository.findById(taskId);
	}

}
