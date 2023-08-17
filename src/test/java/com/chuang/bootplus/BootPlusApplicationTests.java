package com.chuang.bootplus;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BootPlusApplicationTests {

    @Test
    void mp(){
        String s = new Md5Hash("123456").toHex();
        System.out.println(s);
    }
}
