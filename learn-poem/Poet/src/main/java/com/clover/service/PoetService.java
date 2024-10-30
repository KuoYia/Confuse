package com.clover.service;

import com.clover.pojo.Poet;
import com.clover.pojo.Poem;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PoetService {
    Poet findById(int id);
    List<Poet> findAll();
    Poet insert(Poet poet);
    void update(Poet poet);
    boolean delete(int id);
    int getLastId();
    int countCollectionsByAuthorId(int id);
    List<Poet> findByMultipleConditions(String name, String dynasty, String biography);
    void insertPoetsInBatch(List<Poet> poets);
    void deletePoetsInBatch(List<Integer> ids);
    List<Poet> findPoetWithPoemsById(int id);
    List<Poem> selectPoemsByPoetId(int poetId);
    List<Poem> selectCollectionsByUserId(int userId);
    List<Poet> findPoetAndUsersWhoCollectedPoet(int id);
}