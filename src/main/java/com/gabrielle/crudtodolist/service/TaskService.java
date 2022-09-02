package com.gabrielle.crudtodolist.service;

import com.gabrielle.crudtodolist.model.Task;
import com.gabrielle.crudtodolist.repository.TaskRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TaskService {

    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public List<Task> listAllTasks(){
        return this.taskRepository.findAll();
    }

    public ResponseEntity<Task> findById(Long id){
        return this.taskRepository.findById(id)
                .map(task -> ResponseEntity.ok().body(task))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Task> updateById(Task task, Long id){
        return this.taskRepository.findById(id)
                .map( taskToUpdate -> {
                    taskToUpdate.setTitle(task.getTitle());
                    taskToUpdate.setDescription(task.getDescription());
                    taskToUpdate.setDeadLine(task.getDeadLine());
                    Task updated = taskRepository.save(taskToUpdate);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id){
        return this.taskRepository.findById(id)
                .map( taskToDelete -> {
                    taskRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
