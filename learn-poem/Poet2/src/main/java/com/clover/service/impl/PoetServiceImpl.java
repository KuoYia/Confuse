package com.clover.service.impl;

import com.clover.dao.PoetDao;
import com.clover.dao.impl.PoetDaoImpl;
import com.clover.pojo.Poet;
import com.clover.pojo.Poem;
import com.clover.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service

public class PoetServiceImpl implements PoetService {

    @Autowired
    private PoetDao poetDao;

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
        // 该方法用于将一个新的Poet对象插入到数据库中，并返回插入的Poet对象

        // 检查数据库中是否已存在同名的诗人
        List<Poet> existingPoets = poetDao.findByMultipleConditions(poet.getName(), null, null);
        // findByMultipleConditions方法接受诗人的姓名、朝代和传记作为参数，这里只搜索姓名

        // 如果搜索结果不为空，说明存在至少一个同名的诗人
        if (!existingPoets.isEmpty()) {
            // 如果存在同名诗人，则抛出IllegalArgumentException异常，表示不能插入同名诗人
            throw new IllegalArgumentException("A poet with the same name already exists.");
        }

        // 如果没有同名诗人，调用poetDao的insert方法将诗人对象插入到数据库中
        poetDao.insert(poet);

        // 插入操作完成后，返回传入的poet对象
        return poet;
    }

    @Override
    public void update(Poet poet) {
        poetDao.update(poet);
    }

    @Override
    @Transactional
    public boolean delete(int id) {
        // 检查诗人是否存在
        Poet poet = poetDao.findById(id);
        if (poet == null) {
            return false; // 诗人不存在，删除操作不允许，返回false
        }

        // 查询与给定诗人ID相关联的所有诗
        List<Poem> poems = poetDao.selectPoemsByPoetId(id);
        // 计算与给定诗人ID相关联的收藏数量
        int collectionsCount = poetDao.countCollectionsByAuthorId(id);

        // 如果存在与诗人关联的诗或收藏，则不允许删除
        if (!poems.isEmpty() || collectionsCount > 0) {
            return false; // 由于存在关联数据，删除操作不允许，返回false
        }

        // 如果没有关联的诗或收藏，执行删除诗人的操作
        poetDao.delete(id);

        // 删除操作成功，返回true
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



    // 构造函数 - 构造注入
    public PoetServiceImpl(PoetDao poetDao) {
        this.poetDao = poetDao;
    }


    // setter方法 - 设值注入
    // 注意：虽然这里提供了setter方法，但在构造注入的情况下通常不使用它来注入依赖
    public void setPoetDao(PoetDao poetDao) {
        this.poetDao = poetDao;
    }
}