package com.bcjj;

import com.bcjj.util.TimeStamp2Date;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBootFootballApplicationTests {

//    @Test
//    void contextLoads() {
//    }

    public static void main(String[] args) {
        System.out.println(TimeStamp2Date.stampToDate(10000000));
    }

}
