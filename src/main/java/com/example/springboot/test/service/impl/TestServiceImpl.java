package com.example.springboot.test.service.impl;

import cn.hutool.json.JSONUtil;
import com.example.springboot.test.annotation.DS;
import com.example.springboot.test.constant.DataSourceConstants;
import com.example.springboot.test.pojo.ProductPojo;
import com.example.springboot.test.pojo.fast.ProductPojoFast;
import com.example.springboot.test.service.TestService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ckn
 * @date 2022/6/29
 */
@Service
public class TestServiceImpl implements TestService {

    @Override
    @DS(DataSourceConstants.DS_KEY_SLAVE)
    public void updateDatasourceS() {
        ProductPojo p=new ProductPojo();
        p.setId(new Long(1));
        p.setRelationStock("1233");
        p.setOrg("1233");
        ProductPojoFast.create().dao().update(p);
    }

    @DS(DataSourceConstants.DS_KEY_SLAVE)
    @Override
    public String fetchDatasourceS(){
        List<ProductPojo> productPojos = ProductPojoFast.create().dao().fetchAll();
        return JSONUtil.toJsonStr(productPojos);
    }
}
