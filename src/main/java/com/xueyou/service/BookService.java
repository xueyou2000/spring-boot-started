package com.xueyou.service;

import com.xueyou.model.pojo.Book;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/12
 */
public interface BookService {

    /**
     * 查询所有书记
     * @return 返回所有书籍
     */
    List findAll();

    /**
     * 添加书籍
     * @param book 要添加的书籍
     * @return 返回刚添加的书
     */
    int insertByBook(Book book);

    /**
     * 更新书籍信息
     * @param book 要更新的书籍
     * @return 返回书籍
     */
    Book update(Book book);

    /**
     * 根据id删除书籍
     * @param id 要删除的书籍id
     */
    void delete(Long id);

    /**
     * 根据id查找书籍
     * @param id 要查询的书籍id
     * @return 返回书籍
     */
    Book findById(Long id);
}
