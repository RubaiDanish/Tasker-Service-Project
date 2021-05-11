package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskerController {

    @Autowired
    TaskerServiceLayer service;

    public TaskerController(TaskerServiceLayer service) {
        this.service = service;
    }

    //Create new task
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel task) {
        return service.newTask(task);
    }

    //Get task by id
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public TaskViewModel getTask(@PathVariable int id) {
        return service.fetchTask(id);
    }

    //Get all tasks
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public List<TaskViewModel> getAllTasks() {
        return service.fetchAllTasks();
    }

    //Get task by category
    @RequestMapping(value = "/tasks/category/{category}", method = RequestMethod.GET)
    public List<TaskViewModel> getTaskByCategory(@PathVariable String category) {
        return service.fetchTasksByCategory(category);
    }

    //Update task
    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    public void updateTask(@Valid @RequestBody TaskViewModel tvm) {
        service.updateTask(tvm);
    }

    //Delete task
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable int id) {
        service.deleteTask(id);
    }
}
