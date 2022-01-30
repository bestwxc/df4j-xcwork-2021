package com.df4j.xcwork.mybatis;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.weekend.WeekendSqls;

import java.util.List;


/**
 * 公共的基础类，提供mybatis的操作
 *
 * @param <B>
 * @param <M>
 */
public class MybatisServiceSupport<B, M extends tk.mybatis.mapper.common.Mapper<B>> {

    private M mapper;

    public MybatisServiceSupport() {

    }

    protected MybatisServiceSupport(M mapper) {
        this.mapper = mapper;
    }

    public void setMapper(M mapper) {
        this.mapper = mapper;
    }

    public M getMapper() {
        return mapper;
    }

    public int deleteByPrimaryKey(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int delete(B record) {
        return mapper.delete(record);
    }

    public int insert(B record) {
        return mapper.insert(record);
    }

    public int insertSelective(B record) {
        return mapper.insertSelective(record);
    }

    public boolean existsWithPrimaryKey(Object key) {
        return existsWithPrimaryKey(key);
    }

    public List<B> selectAll() {
        return mapper.selectAll();
    }

    public B selectByPrimaryKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public int selectCount(B record) {
        return mapper.selectCount(record);
    }

    public List<B> select(B record) {
        return mapper.select(record);
    }

    public B selectOne(B record) {
        return mapper.selectOne(record);
    }

    public int updateByPrimaryKey(B record) {
        return mapper.updateByPrimaryKey(record);
    }

    public int updateByPrimaryKeySelective(B record) {
        return mapper.updateByPrimaryKeySelective(record);
    }

    public int deleteByExample(Object example) {
        return mapper.deleteByExample(example);
    }

    public List<B> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }

    public B selectOneByExample(Object example) {
        return mapper.selectOneByExample(example);
    }

    public int updateByExample(B record, Object example) {
        return mapper.updateByExample(record, example);
    }

    public int updateByExampleSelective(B record, Object example) {
        return mapper.updateByExampleSelective(record, example);
    }

    public List<B> selectByExampleAndRowBounds(Object example, org.apache.ibatis.session.RowBounds rowBounds) {
        return mapper.selectByExampleAndRowBounds(example, rowBounds);
    }

    public List<B> selectByRowBounds(B record, org.apache.ibatis.session.RowBounds rowBounds) {
        return mapper.selectByRowBounds(record, rowBounds);
    }

    public WeekendSqls<B> createWeekendSqls() {
        WeekendSqls<B> sqls = WeekendSqls.custom();
        return sqls;
    }

    public Example buildExample(WeekendSqls<B> sqls, Class<B> clazz) {
        return new Example.Builder(clazz).where(sqls).build();
    }
}
