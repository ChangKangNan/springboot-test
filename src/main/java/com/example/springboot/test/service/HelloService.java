package com.example.springboot.test.service;

/**
 * @author ckn
 * @date 2022/6/27
 */
public interface HelloService {
    String getHello();
    String fetchDatasourceM();
    String fetchDatasourceS();
    void updateDatasourceM();
    void updateDatasourceS();
    String getHello1();
    void testAutoUpdate();
}
