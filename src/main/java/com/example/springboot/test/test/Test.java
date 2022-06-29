package com.example.springboot.test.test;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author ckn
 * @date 2022/6/16
 */
public class Test {


    private enum Action {
        MONTH("month"),QUARTER("quarter"),YEAR("year"),LAST("last");
        String info;
        Action(String info){
            this.info=info;
        }
    }


    Date getDateNext(Date startDate, Date endDate, String action) {
        if(action.equals(Action.MONTH.info)){
            long betweenMonth = DateUtil.betweenMonth(startDate, endDate, true);
            if(betweenMonth == 0){
                return startDate;
            }else {
                Date dateNewMonth = getNewDay(startDate,Action.MONTH.info);
                int compare = DateUtil.compare(dateNewMonth, endDate);
                if(compare>0){
                    return endDate;
                }else {
                    return dateNewMonth;
                }
            }
        }else if(action.equals(Action.QUARTER.info)){
            long betweenMonth = DateUtil.betweenMonth(startDate, endDate, true);
            if(betweenMonth == 0){
                return startDate;
            }else {
                Date dateNewQuarter = getNewDay(startDate,Action.QUARTER.info);
                int compare = DateUtil.compare(dateNewQuarter, endDate);
                if(compare>0){
                    return endDate;
                }else {
                    return dateNewQuarter;
                }
            }
        }else if(action.equals(Action.YEAR.info)){
            long betweenMonth = DateUtil.betweenMonth(startDate, endDate, true);
            if(betweenMonth == 0){
                return startDate;
            }else {
                Date dateNewYear = getNewDay(startDate,Action.YEAR.info);
                int compare = DateUtil.compare(dateNewYear, endDate);
                if(compare>0){
                    return endDate;
                }else {
                    return dateNewYear;
                }
            }
        }else {
            return endDate;
        }
    }
    Date getNewDay(Date startDate,String action){
        long plusMonth= 0L;
        if(action.equals(Action.MONTH.info)){plusMonth=1L;}
        if(action.equals(Action.QUARTER.info)){plusMonth=3L;}
        int dayOfMonth = DateUtil.dayOfMonth(startDate);
        LocalDate localDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localDateNew=null;
        if(StrUtil.equalsAny(action,Action.MONTH.info,Action.QUARTER.info)){
            localDateNew = localDate.plusMonths(plusMonth).withDayOfMonth(dayOfMonth);
        }else {
            localDateNew = localDate.plusYears(1L).withDayOfMonth(dayOfMonth);
        }
        return Date.from(localDateNew.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    void generateRepaymentInfo(Long fundingLoaningId, Long fundingLoaningReceivedId, BigDecimal applyAmount, BigDecimal rate, long yearDay, String action, Date firstPayInterestDay, Date expireTime){
        Date dateNext=firstPayInterestDay;
        while (DateUtil.compare(expireTime,dateNext) >=0){
            Date sourceDate=dateNext;
            dateNext = getDateNext(dateNext,expireTime,action);
            long betweenDay = DateUtil.betweenDay(sourceDate, dateNext, true);
        }
    }

    public static void main(String[] args) {
        long betweenDay = DateUtil.betweenDay(DateUtil.parse("2022-6-21"), DateUtil.parse("2022-7-21"), true);
        System.out.println(betweenDay);
    }
}
