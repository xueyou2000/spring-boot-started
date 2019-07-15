package com.xueyou.service.impl;

import com.xueyou.model.pojo.Book;
import com.xueyou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 创建 by xueyo on 2019/7/12
 */
@Repository
public class BookImpl implements BookService {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public BookImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /**
     * 查询所有书记
     *
     * @return 返回所有书籍
     */
    @Override
    public List<Book> findAll() {
        String sql = "SELECT id,name,writer,introduction, create_time FROM book";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }

    /**
     * 添加书籍
     *
     * @param book 要添加的书籍
     * @return 返回刚添加的书
     */
    @Override
    public int insertByBook(Book book) {
        book.setCreateTime(new Date());
        String sql = "INSERT INTO book(name,writer,introduction, create_time) VALUES(?,?,?,?)";
        return jdbcTemplate.update(sql, book.getName(),book.getWriter(), book.getIntroduction(), book.getCreateTime());
    }

    /**
     * 更新书籍信息
     *
     * @param book 要更新的书籍
     * @return 返回书籍
     */
    @Override
    public Book update(Book book) {
        String sql = "UPDATE book SET name=?,writer=?,introduction=?, create_time=? WHERE id=?";
        int i = jdbcTemplate.update(sql, book.getName(), book.getWriter(),
                book.getIntroduction(), book.getCreateTime(), book.getId());
        System.out.println("更新书本的id是：" + book.getId());
        return book;
    }

    /**
     * 根据id删除书籍
     *
     * @param id 要删除的书籍id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 根据id查找书籍
     *
     * @param id 要查询的书籍id
     * @return 返回书籍
     */
    @Override
    public Book findById(Long id) {
        String sql = "SELECT id,name,writer,introduction, create_time FROM book WHERE id=?";
        return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper<>(Book.class));
    }
}
