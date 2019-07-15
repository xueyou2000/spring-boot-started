package com.xueyou.controller;

import com.xueyou.model.pojo.Book;
import com.xueyou.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 创建 by xueyo on 2019/7/15
 */
@RestController
@RequestMapping("/book")
@Slf4j
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    /**
     * 添加书籍
     * @param book 要添加的书籍
     * @return 返回添加的书籍
     */
    @RequestMapping("/add")
    public int add(@RequestBody Book book) {
        return bookService.insertByBook(book);
    }

}
