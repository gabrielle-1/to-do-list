package com.gabrielle.crudtodolist.controller;

import com.gabrielle.crudtodolist.model.Task;
import com.gabrielle.crudtodolist.service.TaskService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Slf4j
public class TaskController {

    TaskService taskService;

    @ApiOperation( value = "Criando uma nova tarefa" )
    @ApiResponses( value = {
           @ApiResponse( code = 201, message = "Tarefa criada com sucesso!"),
            @ApiResponse( code = 500, message = "Houve um erro ao criar a tarefa, verifique as informações")
    })

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task){
        log.info("Criando uma nova tarefa com as informações [{}]", task);
        return this.taskService.createTask(task);
    }

    @ApiOperation( value = "Listando todas as tarefas" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = "Tarefas listadas com sucesso!"),
            @ApiResponse( code = 500, message = "Houve um erro ao listar as tarefas")
    })

    @GetMapping("/tasks")
    @ResponseStatus(HttpStatus.OK)
    public List<Task> list(){
        log.info("Listando todas as tarefas cadastradas");
        return this.taskService.listAllTasks();
    }

    @ApiOperation( value = "Buscando uma tarefa pelo seu id" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = "Tarefa encontrada com sucesso!"),
            @ApiResponse( code = 404, message = "Não foi encontrada nenhuma tarefa com esse id")
    })

    @GetMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> getTaskById(@PathVariable (value = "id") Long id){
        log.info("Buscando tarefa com o id [{}]", id);
        return this.taskService.findById(id);
    }

    @ApiOperation( value = "Atualizando uma tarefa" )
    @ApiResponses( value = {
            @ApiResponse( code = 200, message = "Tarefa atualizada com sucesso!"),
            @ApiResponse( code = 404, message = "Não foi possível atualizar a tarefa pois não foi encontrada nenhuma tarefa com esse id")
    })

    @PutMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Task> updateTaskById(@PathVariable (value = "id") Long id, @RequestBody Task task){
        log.info("Atualizando a tarefa com o id [{}] as novas informações são: [{}]", id, task);
        return this.taskService.updateById(task, id);
    }

    @ApiOperation( value = "Deletando uma tarefa" )
    @ApiResponses( value = {
            @ApiResponse( code = 204, message = "Tarefa deletada com sucesso!"),
            @ApiResponse( code = 404, message = "Não foi possível deletar a tarefa pois não foi encontrada nenhuma tarefa com esse id")
    })

    @DeleteMapping("/tasks/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteTaskById(@PathVariable (value = "id") Long id){
        log.info("Excluindo a tarefa com o id [{}]", id);
        return this.taskService.deleteById(id);
    }
}
