package com.scarlett.expenditure.admin.business.action;

import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.business.entity.Company;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.JSONUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *BillAction.java
 *@intention
 * <p> 帐单的控制器</p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
public class BillAction extends BusinessAction {

    private Bill bill;
    private String startDateStr;
    private String endDateStr;


    /** 按条件、分页统计帐单的条数 */
    public String countBill(){
        try {
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(startDateStr) || !StringUtils.isEmpty(endDateStr)) {
                if (StringUtils.isEmpty(startDateStr)) {
                    startDateStr = "2016-06-06 06:06:06";
                }
                startDate = sdf.parse(startDateStr);
                endDate =  StringUtils.isEmpty(endDateStr) ? new Date() : sdf.parse(endDateStr);
                System.out.println("开始：" + startDateStr + "-->" + startDate);
                System.out.println("结束：" + endDateStr + "-->" + endDate);
            }
            businessService.countBill(bill, startDate, endDate, pageModel);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    /** 异步加载交易记录列表 */
    public String loadBillAjax(){
        try {
            Date startDate = null;
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (!StringUtils.isEmpty(startDateStr) || !StringUtils.isEmpty(endDateStr)) {
                if (StringUtils.isEmpty(startDateStr)) {
                    startDateStr = "2016-06-06 06:06:06";
                }
                startDate = sdf.parse(startDateStr);
                endDate =  StringUtils.isEmpty(endDateStr) ? new Date() : sdf.parse(endDateStr);
                System.out.println("开始：" + startDateStr + "-->" + startDate);
                System.out.println("结束：" + endDateStr + "-->" + endDate);
            }
            listMap = businessService.loadBillAjax(bill,startDate, endDate, pageModel);
            System.out.println("查询到的帐单列表：" + JSONUtil.serialize(listMap));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }


    public String addBill(){
        try {
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            endDate = sdf.parse(endDateStr);
            System.out.println("出账时间：" + endDateStr + "-->" + endDate);
            bill.setAppearDate(endDate);
            businessService.addBill(bill);
            setTipStatus(0);
        } catch (Exception ex) {
            setTipStatus(1);
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String showUpdateBill(){
        try {
            bill = businessService.getBillById(bill.getId());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            endDateStr = sdf.format(bill.getAppearDate());
            System.out.println("出帐时间：" + bill.getAppearDate() + "-->" + endDateStr);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public String updateBill(){
        try {
            Date endDate = null;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            endDate = sdf.parse(endDateStr);
            System.out.println("出账时间~：" + endDateStr + "-->" + endDate);
            bill.setAppearDate(endDate);
            businessService.updateBill(bill);
            setTip("修改成功！");
        } catch (Exception ex) {
            setTip("修改失败！");
            ex.printStackTrace();
        }
        return SUCCESS;
    }

    public Bill getBill() {
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
    }

    public String getStartDateStr() {
        return startDateStr;
    }

    public void setStartDateStr(String startDateStr) {
        this.startDateStr = startDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
