package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.model.Task;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    protected TaskerDao taskerDao;

    @Before
    public void setUp() throws Exception {
        List<Task> tList = taskerDao.getAllTasks();

        tList.stream()
                .forEach(task -> taskerDao.deleteTask(task.getId()));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void addGetDeleteTask() {

        Task task = new Task();
        task.setDescription("Summative 8");
        task.setCreateDate(LocalDate.of(2021,04,12));
        task.setDueDate(LocalDate.of(2021,04,19));
        task.setCategory("Education");

        task = taskerDao.createTask(task);

        Task task2 = taskerDao.getTask(task.getId());

        assertEquals(task, task2);

        taskerDao. deleteTask(task.getId());

        task2 = taskerDao.getTask(task.getId());

        assertNull(task2);
    }

    @Test
    public void getAllTasks() {

        Task task = new Task();
        task.setDescription("Summative 8");
        task.setCreateDate(LocalDate.of(2021,04,12));
        task.setDueDate(LocalDate.of(2021,04,19));
        task.setCategory("Education");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("Capstone 1");
        task.setCreateDate(LocalDate.of(2021,03,29));
        task.setDueDate(LocalDate.of(2021,04,05));
        task.setCategory("Education");

        taskerDao.createTask(task);

        List<Task> taskList = taskerDao.getAllTasks();

        assertEquals(taskList.size(), 2);
    }

    @Test
    public void getTaskByCategory() {

        Task task = new Task();
        task.setDescription("Summative 8");
        task.setCreateDate(LocalDate.of(2021,04,12));
        task.setDueDate(LocalDate.of(2021,04,19));
        task.setCategory("Education");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("Capstone 1");
        task.setCreateDate(LocalDate.of(2021,03,29));
        task.setDueDate(LocalDate.of(2021,04,05));
        task.setCategory("Education");

        taskerDao.createTask(task);

        task = new Task();
        task.setDescription("Feed Miso");
        task.setCreateDate(LocalDate.of(2021,01,12));
        task.setDueDate(LocalDate.of(2040,12,31));
        task.setCategory("Daily");

        taskerDao.createTask(task);

        List<Task> taskList = taskerDao.getTasksByCategory("Education");
        assertEquals(2, taskList.size());

        taskList = taskerDao.getTasksByCategory("Daily");
        assertEquals(1, taskList.size());

        taskList = taskerDao.getTasksByCategory("Work");
        assertEquals(0, taskList.size());
    }

    @Test
    public void updateTask() {

        Task task = new Task();
        task.setDescription("Summative 8");
        task.setCreateDate(LocalDate.of(2021,04,12));
        task.setDueDate(LocalDate.of(2021,04,19));
        task.setCategory("Education");

        taskerDao.createTask(task);

        task.setDescription("UPDATED TASK");
        task.setCreateDate(LocalDate.of(2021,12,20));
        task.setDueDate(LocalDate.of(2021,12,31));
        task.setCategory("Work");

        taskerDao.updateTask(task);

        Task task2 = taskerDao.getTask(task.getId());

        assertEquals(task2, task);
    }

}
