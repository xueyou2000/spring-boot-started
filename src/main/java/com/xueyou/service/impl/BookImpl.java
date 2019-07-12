package com.xueyou.service.impl;

import com.xueyou.model.pojo.Book;
import com.xueyou.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 创建 by xueyo on 2019/7/12
 */
@Repository
public class BookImpl implements BookService {

    private JdbcTemplate jdbcTemplate;

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
        String sql = "SELECT id,name,writer,introduction FROM book`";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper(Book.class));
    }

    /**
     * 添加书籍
     *
     * @param book
     * @return 返回刚添加的书
     */
    @Override
    public Book insertByBook(Book book) {
        String sql = "INSERT INTO t_book(name,writer,introduction) VALUES(?,?,?)";
        jdbcTemplate.update(sql, book.getName(),book.getWriter(), book.getIntroduction());
        return book;
    }

    /**
     * 更新书籍信息
     *
     * @param book
     * @return
     */
    @Override
    public Book update(Book book) {
        String sql = "UPDATE t_book SET name=?,writer=?,introduction=? WHERE id=?";
        int i = jdbcTemplate.update(sql, book.getName(), book.getWriter(),
                book.getIntroduction(), book.getId());
        System.out.println("更新书本的id是：" + book.getId());
        return book;
    }

    /**
     * 根据id删除书籍
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM t_book WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * 根据id查找书籍
     *
     * @param id
     * @return
     */
    @Override
    public Book findById(Long id) {
        String sql = "SELECT id,name,writer,introduction FROM t_book WHERE id=?";
        return (Book) jdbcTemplate.queryForObject(sql, new Object[]{id}, new BeanPropertyRowMapper(Book.class));
    }
}
