package com.clover.controller;

import com.clover.pojo.Poet;

import com.clover.service.impl.PoetServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/poets")
public class PoetController {

    @Autowired
    private PoetServiceImpl poetService;
    @GetMapping("/queryPoet/{id}")
    public Poet queryPoetById(@PathVariable int id) {
        System.out.println("模拟查询指定id的诗人");
        Poet poet = new Poet();
        poet.setId(id);
        poet.setName("李白"); // 假设这是诗人的名字
        poet.setDynasty("唐"); // 假设这是诗人的朝代
        poet.setBiography("唐代著名诗人"); // 假设这是诗人的简介
        // 假设诗人的生卒日期，这里使用Java的Date类
        poet.setBirthDate(java.sql.Date.valueOf(LocalDate.of(2004, 1, 1)));
        poet.setDeathDate(java.sql.Date.valueOf(LocalDate.of(0000, 1, 1)));
        return poet;
    }
    @PostMapping("/")
    public ResponseEntity<Integer> addPoet(@RequestBody Poet poet) {
        try {
            poetService.insert(poet);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Integer> deletePoet(@PathVariable int id) {
        try {
            poetService.delete(id);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
    }

    @PutMapping("/")
    public ResponseEntity<Integer> updatePoet(@RequestBody Poet poet) {
        try {
            poetService.update(poet);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
    }

    //http://localhost:8080/api/poets/1
    @GetMapping("/{id}")
    public ResponseEntity<Poet> getPoetById(@PathVariable int id) {
        Poet poet = poetService.findById(id);
        return ResponseEntity.ok(poet);
    }


    //批量插入
    @PostMapping("/batch")
    public ResponseEntity<Integer> batchInsert(@RequestBody List<Poet> poets) {
        try {
            poetService.insertPoetsInBatch(poets);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
    }

    @DeleteMapping("/batch")
    public ResponseEntity<Integer> batchDelete(@RequestBody List<Integer> ids) {
        try {
            poetService.deletePoetsInBatch(ids);
            return ResponseEntity.ok(1);
        } catch (Exception e) {
            return ResponseEntity.ok(0);
        }
    }

    @GetMapping("/page")
    public ResponseEntity<List<Poet>> getPoetsByPage(@RequestParam int page, @RequestParam int size) {
        // Implement pagination logic here
        return ResponseEntity.ok(poetService.findAll());
    }
}