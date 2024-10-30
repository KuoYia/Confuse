package com.clover.dao.impl;

import com.clover.dao.PoetDao;
import com.clover.pojo.Poem;
import com.clover.pojo.Poet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PoetDaoImpl implements PoetDao {

    @Autowired
    private PoetDao poetMapper;

    @Override
    public Poet findById(int id) {
        return poetMapper.findById(id);
    }

    @Override
    public List<Poet> findAll() {
        return poetMapper.findAll();
    }

    @Override
    public int insert(Poet poet) {
        return poetMapper.insert(poet);
    }

    @Override
    public void update(Poet poet) {
        poetMapper.update(poet);
    }

    @Override
    public void delete(int id) {
        poetMapper.delete(id);
    }

    @Override
    public int getLastId() {
        return poetMapper.getLastId();
    }

    @Override
    public int countCollectionsByAuthorId(int id) {
        return poetMapper.countCollectionsByAuthorId(id);
    }

    @Override
    public List<Poet> findByMultipleConditions(String name, String dynasty, String biography) {
        return poetMapper.findByMultipleConditions(name, dynasty, biography);
    }

    @Override
    public void insertPoetsInBatch(List<Poet> poets) {
        poetMapper.insertPoetsInBatch(poets);
    }

    @Override
    public void deletePoetsInBatch(List<Integer> ids) {
        poetMapper.deletePoetsInBatch(ids);
    }

    @Override
    public List<Poet> findPoetWithPoemsById(int id) {
        return poetMapper.findPoetWithPoemsById(id);
    }

    @Override
    public List<Poem> selectPoemsByPoetId(int poetId) {
        return poetMapper.selectPoemsByPoetId(poetId);
    }

    @Override
    public List<Poem> selectCollectionsByUserId(int userId) {
        return poetMapper.selectCollectionsByUserId(userId);
    }

    @Override
    public List<Poet> findPoetAndUsersWhoCollectedPoet(int id) {
        return poetMapper.findPoetAndUsersWhoCollectedPoet(id);
    }
}

