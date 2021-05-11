package com.trilogyed.tasker.dao.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.feign.AdserverService;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class TaskerServiceLayerTest {
    private TaskerServiceLayer service;
    private TaskerDao taskerDao;

    @MockBean
    private AdserverService adServer;

    @Before
    public void setUp() throws Exception {
        setUpTaskerDaoMock();
        setUpAdserverMock();
        service = new TaskerServiceLayer(taskerDao, adServer);

    }

    private void setUpAdserverMock() {
        adServer = mock(AdserverService.class);

        doReturn("Ad").when(adServer).getAd();
        this.adServer = adServer;

    }

    private void setUpTaskerDaoMock() {
        taskerDao = mock(TaskerDao.class);

        Task task = new Task();
        task.setDescription("Project");
        task.setCreateDate(LocalDate.of(2021,5,20));
        task.setDueDate(LocalDate.of(2021,5,24));
        task.setCategory("Class");


        Task task2 = new Task();
        task2.setId(2);
        task2.setDescription("Project");
        task2.setCreateDate(LocalDate.of(2021,5,20));
        task2.setDueDate(LocalDate.of(2021,5,24));
        task2.setCategory("Class");


        List<Task> taskList = new ArrayList<>();
        taskList.add(task2);

        doReturn(task2).when(taskerDao).createTask(task);
        doReturn(task2).when(taskerDao).getTask(2);
        doReturn(taskList).when(taskerDao).getAllTasks();
        doReturn(taskList).when(taskerDao).getTasksByCategory("Class");

        this.taskerDao = taskerDao;
    }

    @Test
    public void shouldSaveAndFindTask() {
        TaskViewModel task = new TaskViewModel();
        task.setDescription("Project");
        task.setCreateDate(LocalDate.of(2021,5,20));
        task.setDueDate(LocalDate.of(2021,5,24));
        task.setCategory("Class");

        TaskViewModel task2 = new TaskViewModel();
        task2.setId(2);
        task2.setDescription("Project");
        task2.setCreateDate(LocalDate.of(2021,5,20));
        task2.setDueDate(LocalDate.of(2021,5,24));
        task2.setCategory("Class");

        task = service.newTask(task);
        TaskViewModel fromService = service.fetchTask(task.getId());
        assertEquals(task, fromService);
    }

    @Test
    public void shouldGetAllTasks() {
        TaskViewModel task2 = new TaskViewModel();
        task2.setId(2);
        task2.setDescription("Project");
        task2.setCreateDate(LocalDate.of(2021,5,20));
        task2.setDueDate(LocalDate.of(2021,5,24));
        task2.setCategory("Class");
        task2.setAdvertisement("Ad");

        List<TaskViewModel> taskList = new ArrayList<>();
        taskList.add(task2);

        List<TaskViewModel> taskList2 = service.fetchAllTasks();
        assertEquals(taskList2, taskList);
    }

    @Test
    public void shouldGetTasksByCategory() {
        TaskViewModel task2 = new TaskViewModel();
        task2.setId(2);
        task2.setDescription("Project");
        task2.setCreateDate(LocalDate.of(2021,5,20));
        task2.setDueDate(LocalDate.of(2021,5,24));
        task2.setCategory("Class");
        task2.setAdvertisement("Ad");

        List<TaskViewModel> taskList = new ArrayList<>();
        taskList.add(task2);

        List<TaskViewModel> taskList2 = service.fetchTasksByCategory("Class");
        assertEquals(taskList2, taskList);
    }
}


