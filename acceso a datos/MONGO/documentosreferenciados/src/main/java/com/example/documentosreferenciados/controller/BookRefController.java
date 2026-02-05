package com.example.documentosreferenciados.controller;

import com.example.documentosreferenciados.modelo.BookRef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/books")
public class BookRefController {
    @Autowired
    BookRef bookRef;


}
