package com.fsd.taskmanager;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fsd.taskmanager.entities.ParentTask;
import com.fsd.taskmanager.model.TaskDetails;
import com.fsd.taskmanager.repository.ParentTaskRepository;
import com.fsd.taskmanager.service.TaskMangerService;

@RunWith(PowerMockRunner.class)
public class TaskManagerServiceTest {
	
	@Mock 
	ParentTaskRepository parentTaskRepository;
	
	@InjectMocks
	private TaskMangerService taskMangerService;
	
	TaskDetails taskDetails;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testAddTask() {
		List<ParentTask> parentTasks = new ArrayList<>();
		ParentTask parentTask = new ParentTask();
		parentTask.setName("name");
		parentTasks.add(parentTask);
		Mockito.when(parentTaskRepository.findAll()).thenReturn(parentTasks);
		List<TaskDetails>  taskDetails = taskMangerService.getAllTasks();
		assertNotNull(taskDetails);
	}
	

}
