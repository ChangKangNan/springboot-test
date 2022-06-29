package com.example.springboot.test.pojo.fast;
import sh.pd.lx.fast.easyfast.compent.BasePojo;
import sh.pd.lx.fast.easyfast.compent.Criteria;
import com.example.springboot.test.pojo.ProductPojo;

/**
* @author kangnan.chang
*/

public class ProductPojoFast extends BasePojo<ProductPojo> {
    public static ProductPojoFast create() {return new ProductPojoFast();}
    public Criteria<ProductPojo> id(Long... ids) { return this.field("id").equal(ids);}
    public Criteria<ProductPojo> productName(String... productNames) { return this.field("productName").equal(productNames);}
    public Criteria<ProductPojo> relationStock(String... relationStocks) { return this.field("relationStock").equal(relationStocks);}
    public Criteria<ProductPojo> org(String... orgs) { return this.field("org").equal(orgs);}
}