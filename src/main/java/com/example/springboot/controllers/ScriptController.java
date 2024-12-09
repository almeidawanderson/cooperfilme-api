package com.example.springboot.controllers;

import com.example.springboot.dtos.ScriptRecordDTO;
import com.example.springboot.models.ScriptModel;
import com.example.springboot.repositories.ScriptRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ScriptController {

    @Autowired
    ScriptRepository scriptRepository;

    @PostMapping("/scripts")
    public ResponseEntity<ScriptModel> saveProduct(@RequestBody @Valid ScriptRecordDTO scriptRecordDTO){
        var scriptModel = new ScriptModel();

        BeanUtils.copyProperties(scriptRecordDTO, scriptModel);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(scriptRepository.save(scriptModel));
    }

    @GetMapping("/scripts")
    public ResponseEntity<List<ScriptModel>> getAllScript(){
        return ResponseEntity.status(HttpStatus.OK).body(scriptRepository.findAll());
    }

    @GetMapping("/scripts/{id}")
    public ResponseEntity<Object> getOneScript(@PathVariable(value = "id")UUID id){
        Optional<ScriptModel> scriptO = scriptRepository.findById(id);
        if(scriptO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Script not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(scriptO.get());
    }

    @PutMapping("/scripts/{id}")
    public ResponseEntity<Object> updateScript(@PathVariable(value = "id") UUID id, @RequestBody @Valid ScriptRecordDTO scriptRecordDTO){
        Optional<ScriptModel> scriptO = scriptRepository.findById(id);
        if(scriptO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Script not found");
        }
        var scriptModel = scriptO.get();
        BeanUtils.copyProperties(scriptRecordDTO, scriptModel);
        return ResponseEntity.status(HttpStatus.OK).body(scriptRepository.save(scriptModel));
    }

    @DeleteMapping("/scripts/{id}")
    public ResponseEntity<Object> deleteScript(@PathVariable(value = "id")UUID id){
        Optional<ScriptModel> scriptO = scriptRepository.findById(id);
        if(scriptO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Script not found");
        }
        scriptRepository.delete(scriptO.get());
        return ResponseEntity.status(HttpStatus.OK).body("Script deleted successfully");
    }



}
