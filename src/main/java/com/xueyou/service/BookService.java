package com.xueyou.service;

import com.xueyou.model.pojo.Book;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/12
 */
public interface BookService {

    /**
     * 查询所有书记
     * @return
     */
    List<Book> findAll();

    /**
     * 添加书籍
     * @param book
     * @return 返回刚添加的书
     */
    Book insertByBook(Book book);

    /**
     * 更新书籍信息
     * @param book
     * @return
     */
    Book update(Book book);

    /**
     * 根据id删除书籍
     * @param id
     */
    void delete(Long id);

    /**
     * 根据id查找书籍
     * @param id
     * @return
     */
    Book findById(Long id);
}
