package com.example;

import com.example.book.BookControllerTests;
import com.example.comment.CommentControllerTests;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.platform.suite.api.Suite;

@Suite
@SelectClasses({
        BookControllerTests.class,
        CommentControllerTests.class
})
public class JavaTestContainerTestSuite {
}
