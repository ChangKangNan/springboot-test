package com.example.springboot.test.service.impl;

import cn.hutool.json.JSONUtil;
import com.example.springboot.test.annotation.DS;
import com.example.springboot.test.constant.DataSourceConstants;
import com.example.springboot.test.pojo.ProductPojo;
import com.example.springboot.test.pojo.fast.ProductPojoFast;
import com.example.springboot.test.service.HelloService;
import com.example.springboot.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author ckn
 * @date 2022/6/27
 */
@Service
public class HelloServiceImpl implements HelloService {
/*    @Autowired
    @Qualifier("jdbcTemplateMaster")
    JdbcTemplate jdbcTemplateMaster;

    @Autowired
    @Qualifier("jdbcTemplateSalve")
    JdbcTemplate jdbcTemplateSalve;*/
    @Autowired
    TestService testService;

    @Autowired
    HelloService helloService;

    @Override
    @DS
    public String getHello() {
        return fetchDatasourceM()+"======"+testService.fetchDatasourceS();
    }

    @Override
    public String getHello1() {
        return helloService.fetchDatasourceM()+"======"+helloService.fetchDatasourceS();
    }

    @Override
    public void testAutoUpdate() {
        ProductPojo productPojo=new ProductPojo();
        productPojo.setOrg("iii1");
        ProductPojoFast.create().id(new Long(1)).dao().updateOverwrite(productPojo);
    }

    @Override
    @DS
    public String fetchDatasourceM(){
        List<ProductPojo> productPojos = ProductPojoFast.create().dao().fetchAll();
        return JSONUtil.toJsonStr(productPojos);
    }

    @DS(DataSourceConstants.DS_KEY_SLAVE)
    @Override
    public String fetchDatasourceS(){
        List<ProductPojo> productPojos = ProductPojoFast.create().dao().fetchAll();
        return JSONUtil.toJsonStr(productPojos);
    }

    @Override
    public void updateDatasourceM(){
      //  jdbcTemplateMaster.execute("UPDATE product set product_name='abc' where id=1");
        ProductPojo p=new ProductPojo();
        p.setId(new Long(1));
        p.setRelationStock("wwwttt");
        p.setOrg("yyyyttt");
        ProductPojoFast.create().dao().updateOverwrite(p);
    }

    @DS(DataSourceConstants.DS_KEY_SLAVE)
    @Override
    public void updateDatasourceS(){
      //  int i= 1/0;
        //jdbcTemplateSalve.execute("UPDATE product set product_name='cde' where id=1");
        ProductPojo p=new ProductPojo();
        p.setId(new Long(1));
        p.setRelationStock("12333");
        p.setOrg("12333");
        ProductPojoFast.create().dao().update(p);
    }
}
