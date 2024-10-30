package com.clover.service.impl;

import com.clover.dao.PoetDao;
import com.clover.pojo.Poet;
import com.clover.pojo.Poem;
import com.clover.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PoetServiceImpl implements PoetService {
    private final PoetDao poetDao;

    // 构造函数 - 构造注入
    public PoetServiceImpl(PoetDao poetDao) {
        this.poetDao = poetDao;
    }

    @Override
    public Poet findById(int id) {
        return poetDao.findById(id);
    }

    @Override
    public List<Poet> findAll() {
        return poetDao.findAll();
    }

    @Override
    public Poet insert(Poet poet) {
        List<Poet> existingPoets = poetDao.findByMultipleConditions(poet.getName(), null, null);
        if (!existingPoets.isEmpty()) {
            throw new IllegalArgumentException("A poet with the same name already exists.");
        }
        poetDao.insert(poet);
        return poet;
    }

    @Override
    public void update(Poet poet) {
        poetDao.update(poet);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        Poet poet = poetDao.findById(id);
        if (poet == null) {
            return false;
        }
        List<Poem> poems = poetDao.selectPoemsByPoetId(id);
        int collectionsCount = poetDao.countCollectionsByAuthorId(id);
        if (!poems.isEmpty() || collectionsCount > 0) {
            return false;
        }
        poetDao.delete(id);
        return true;
    }

    @Override
    public int getLastId() {
        return poetDao.getLastId();
    }

    @Override
    public int countCollectionsByAuthorId(int id) {
        return poetDao.countCollectionsByAuthorId(id);
    }

    @Override
    public List<Poet> findByMultipleConditions(String name, String dynasty, String biography) {
        return poetDao.findByMultipleConditions(name, dynasty, biography);
    }

    @Override
    public void insertPoetsInBatch(List<Poet> poets) {
        for (Poet poet : poets) {
            insert(poet);
        }
    }

    @Override
    public void deletePoetsInBatch(List<Integer> ids) {
        for (Integer id : ids) {
            if (!delete(id)) {
                throw new IllegalStateException("Deletion of poet with ID " + id + " failed due to associated poems or collections.");
            }
        }
    }

    @Override
    public List<Poet> findPoetWithPoemsById(int id) {
        return poetDao.findPoetWithPoemsById(id);
    }

    @Override
    public List<Poem> selectPoemsByPoetId(int poetId) {
        return poetDao.selectPoemsByPoetId(poetId);
    }

    @Override
    public List<Poem> selectCollectionsByUserId(int userId) {
        return poetDao.selectCollectionsByUserId(userId);
    }

    @Override
    public List<Poet> findPoetAndUsersWhoCollectedPoet(int id) {
        return poetDao.findPoetAndUsersWhoCollectedPoet(id);
    }
}