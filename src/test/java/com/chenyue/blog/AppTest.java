package com.chenyue.blog;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

@Slf4j
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:dispatcher-servlet.xml"})
public class AppTest {
    //private static final Logger logger = LoggerFactory.getLogger(AppTest.class);

    @Autowired
    DataSource dataSource;
    @Autowired
    TransactionManager txManager;

    @Test
    void test01() {
        //System.out.println(dataSource);
        System.out.println(txManager);
    }

    @Test
    void test02(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'k'hh:mm:ss");
        LocalDateTime dateTime = LocalDateTime.now();

    }
}
