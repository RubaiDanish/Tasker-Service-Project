package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.feign.AdserverService;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Component
@Service
public class TaskerServiceLayer {

    TaskerDao dao;
    TaskViewModel tvm;
    AdserverService client;

    @Autowired
    public TaskerServiceLayer (TaskerDao dao,  TaskViewModel tvm, AdserverService client) {
        this.dao = dao;
        this.tvm = tvm;
        this.client = client;
    }

    public TaskViewModel fetchTask(int id) {

        Task task = dao.getTask(id);
        TaskViewModel tvm = new TaskViewModel();

        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        tvm.setAdvertisement(client.getAdvertisementForTask(tvm.getAdvertisement()));

        return tvm;
    }

    private TaskViewModel buildTaskViewModel(Task task) {
        TaskViewModel tvm = new TaskViewModel();
        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());

        return tvm;
    }

    public List<TaskViewModel> fetchAllTasks() {
        List<Task> taskList = dao.getAllTasks();
        List<TaskViewModel> returnVal = new ArrayList<>();
        for (Task t: taskList) {
            returnVal.add(buildTaskViewModel(t));
        }
        return returnVal;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {
        List<Task> categoriedTasks = dao.getTasksByCategory(category);
        List<TaskViewModel> tasksInCategories = new ArrayList<>();
        for (Task t: categoriedTasks) {
            tasksInCategories.add(buildTaskViewModel(t));
        }

        return tasksInCategories;
    }

    public TaskViewModel newTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
        taskViewModel.setId(task.getId());
        String ad = client.getAdvertisementForTask(tvm.getAdvertisement());

        return taskViewModel;
    }

    public void updateTask(Task task) {
        dao.updateTask(task);
    }

    public void deleteTask(int id) {
        dao.deleteTask(id);
    }
}