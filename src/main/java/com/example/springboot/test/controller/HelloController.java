package com.example.springboot.test.controller;

import cn.hutool.json.JSONUtil;
import com.example.springboot.test.annotation.DS;
import com.example.springboot.test.pojo.UserPojo;
import com.example.springboot.test.pojo.fast.UserPojoFast;
import com.example.springboot.test.service.HelloService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sh.pd.lx.fast.easyfast.compent.OrderAction;
import sh.pd.lx.fast.easyfast.customer.EasyCustomer;

/**
 * @author ckn
 * @date 2022/6/16
 */
@RestController
@RequestMapping("/hello")
public class HelloController {
    @Autowired
    HelloService helloService;

    @GetMapping("/getName")
    public String getName() {
        System.out.println(1 / 0);
        UserPojo pj = UserPojoFast.create().id(new Long(1)).dao().unique();
        return JSONUtil.toJsonStr(pj);
    }

    @GetMapping("/getName1")
    public String getName1() {
        UserPojoFast userPojoFast = UserPojoFast.create();
        userPojoFast.id(new Long(2)).orderBy(OrderAction.DOWN);
        userPojoFast.password("5");
        UserPojo pj = userPojoFast.dao().unique();
        return JSONUtil.toJsonStr(pj);
    }

    @GetMapping("/testAuto")
    public String testAuto() {
        UserPojo userPojo = EasyCustomer.create()
                .select("product_name", "id")
                .from("test_user")
                .fetchOne(UserPojo.class);
        return JSONUtil.toJsonStr(userPojo);
    }

    @GetMapping("/testAuto2")
    public String testAuto2() {
        return helloService.fetchDatasourceM() + "=====" + helloService.fetchDatasourceS();
    }

    @GetMapping("/testAuto3")
    public String testAuto3() {
        return helloService.getHello();
    }

    @GetMapping("/testAuto5")
    public String testAuto5() {
        return helloService.getHello1();
    }

    @GetMapping("/testAuto4")
    @GlobalTransactional
    public String testAuto4() {
        helloService.updateDatasourceM();
        helloService.updateDatasourceS();
        return "success";
    }

    @GetMapping("/testAutoUpdate")
    public String testAutoUpdate() {
        helloService.testAutoUpdate();
        return "success";
    }
}
