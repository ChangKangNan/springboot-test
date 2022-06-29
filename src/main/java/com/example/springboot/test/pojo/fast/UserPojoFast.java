package com.example.springboot.test.pojo.fast;
import sh.pd.lx.fast.easyfast.compent.BasePojo;
import sh.pd.lx.fast.easyfast.compent.Criteria;
import com.example.springboot.test.pojo.UserPojo;

/**
* @author kangnan.chang
*/

public class UserPojoFast extends BasePojo<UserPojo> {
    public static UserPojoFast create() {return new UserPojoFast();}
    public Criteria<UserPojo> id(Long... ids) { return this.field("id").equal(ids);}
    public Criteria<UserPojo> productName(String... productNames) { return this.field("productName").equal(productNames);}
    public Criteria<UserPojo> numbers(Double... numberss) { return this.field("numbers").equal(numberss);}
    public Criteria<UserPojo> password(String... passwords) { return this.field("password").equal(passwords);}
    public Criteria<UserPojo> degree(Integer... degrees) { return this.field("degree").equal(degrees);}
}