package com.scarlett.expenditure.admin.business.service.impl;

import com.scarlett.expenditure.admin.account.entity.Record;
import com.scarlett.expenditure.admin.business.dao.IBillDao;
import com.scarlett.expenditure.admin.business.dao.ICompanyDao;
import com.scarlett.expenditure.admin.business.entity.Bill;
import com.scarlett.expenditure.admin.business.entity.Company;
import com.scarlett.expenditure.admin.business.service.IBusinessService;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.pojo.PageModel;
import com.scarlett.expenditure.core.util.NumUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 *BusinessServiceImpl.java
 *@intention
 * <p> 业务模块的业务实现类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class BusinessServiceImpl implements IBusinessService {

    @Resource
    private ICompanyDao companyDao;

    @Resource
    private IBillDao billDao;

    @Resource
    private IUserDao userDao;

    @Override
    public void countCompany(Company company, PageModel pageModel) {
        try {
            pageModel.setRecordCount(companyDao.countByPage(company));
        } catch (Exception ex) {
            throw new OAException("按条件、分页统计机构的个数时出现异常", ex);
        }
    }

    @Override
    public List<Map<String, Object>> loadCompanyAjax(Company company, PageModel pageModel) {
        List<Map<String, Object>> listMap = null;
        Map<String, Object> data;
        try {
            List<Company> list = companyDao.loadCompanyByPage(company, pageModel);
            if (list != null && list.size() > 0) {
                listMap = new ArrayList<Map<String, Object>>();
                for (Company com: list) {
                    data = new HashMap<String, Object>();
                    data.put("id", com.getId());
                    data.put("name", com.getName());
                    String type = com.getType();
                    data.put("type", type);
                    String unit = "";
                    if ("水费".equals(type)) unit = "m³";
                    if ("电费".equals(type)) unit = "度";
                    if ("煤气费".equals(type)) unit = "升";
                    data.put("price","￥ " + com.getPrice() + "元/" + unit);
                    listMap.add(data);
                }
            }
        } catch (Exception ex) {
            throw new OAException("异步加载机构列表时出现异常", ex);
        }
        return listMap;
    }

    @Override
    public boolean validComAjax(String name, Long id) {
        try {
            int count = companyDao.countByName(name, id);
            return count > 0;
        } catch (Exception ex) {
            throw new OAException("校验机构名时出现异常", ex);
        }
    }

    @Override
    public void addCompany(Company company) {
        try {
            if (company != null) companyDao.save(company);
        } catch (Exception ex) {
            throw new OAException("增加机构时出现异常", ex);
        }
    }

    @Override
    public Company getCompanyById(Long id) {
        try {
            return companyDao.get(Company.class, id);
        } catch (Exception ex) {
            throw new OAException("根据id查询机构时出现异常", ex);
        }
    }

    @Override
    public void updateCompany(Company company) {
        try {
            companyDao.update(company);
        } catch (Exception ex) {
            throw new OAException("修改机构时出现异常", ex);
        }
    }

    @Override
    public List<Object> loadCompanyNameAjax(String name) {
        try {
            List<Object> list = companyDao.getNameList(name);
            return list;
        } catch (Exception ex) {
            throw new OAException("加载名字时出现异常", ex);
        }
    }

    @Override
    public List<Map<String, Object>> loadAllCompanyAjax() {
        List<Map<String, Object>> listMap = null;
        Map<String, Object> data;
        try {
            List<Company> list = companyDao.loadAllCompanyAjax();
            if (list != null && list.size() > 0) {
                listMap = new ArrayList<Map<String, Object>>();
                for (Company com: list) {
                    data = new HashMap<String, Object>();
                    data.put("id", com.getId());
                    data.put("name", com.getName());
                    String type = com.getType();
                    data.put("type", type);
                    data.put("price", com.getPrice());
                    listMap.add(data);
                }
            }
        } catch (Exception ex) {
            throw new OAException("异步加载所有机构时出现异常", ex);
        }
        return listMap;
    }



    /*#################################### 帐单管理 TODO ########################################*/

    @Override
    public void countBill(Bill bill, Date startDate, Date endDate, PageModel pageModel) {
        try {
            pageModel.setRecordCount(billDao.countByPage(bill, startDate, endDate));
        } catch (Exception ex) {
            throw new OAException("按条件、分页统计帐单的个数时出现异常", ex);
        }
    }

    @Override
    public List<Map<String, Object>> loadBillAjax(Bill b, Date startDate, Date endDate, PageModel pageModel) {
        List<Map<String, Object>> listMap = null;
        Map<String, Object> data;
        try {
            List<Bill> list = billDao.loadBillAjax(b, startDate, endDate, pageModel);
            if (list != null && list.size() > 0) {
                listMap = new ArrayList<Map<String, Object>>();
                for (Bill bill: list) {
                    data = new HashMap<String, Object>();
                    data.put("id", bill.getId());
                    data.put("comId", bill.getCompany().getId());
                    data.put("comName", bill.getCompany().getName());
                    String type = bill.getCompany().getType();
                    String unit = "";
                    if ("水费".equals(type)) unit = "m³";
                    if ("电费".equals(type)) unit = "度";
                    if ("煤气费".equals(type)) unit = "升";
                    data.put("price","￥ " + bill.getCompany().getPrice() + "元/" + unit);
                    data.put("use", bill.getUsageAmount() + unit);
                    data.put("sum", bill.getSumPrice());
                    String[] typeArrs = { "<font color='red'>未缴</font>", "<font>已缴</font>"};
                    data.put("type", typeArrs[bill.getType()]);
                    data.put("pertain", bill.getPertain().getUserId() + " " + bill.getPertain().getName());
                    data.put("appearDate", bill.getAppearDate());
                    data.put("handleDate", bill.getHandleDate() == null ? "" : bill.getHandleDate());
                    data.put("checker", bill.getChecker() == null ? "" : bill.getChecker().getName());
                    data.put("remark", StringUtils.isEmpty(bill.getRemark()) ? "" : bill.getRemark());
                    listMap.add(data);
                }
            }
        } catch (Exception ex) {
            throw new OAException("异步加载帐单列表时出现异常", ex);
        }
        return listMap;
    }

    @Override
    public void addBill(Bill bill) {
        try {
            // 设置总价
            if (bill.getCompany() != null) bill.setCompany(companyDao.get(Company.class, bill.getCompany().getId()));
            Double price = bill.getCompany().getPrice();
            Double usageAmount = bill.getUsageAmount();
            bill.setSumPrice(NumUtil.mul(price, usageAmount));
            billDao.save(bill);
        } catch (Exception ex) {
            throw new OAException("增加帐单时出现异常", ex);
        }
    }

    @Override
    public Bill getBillById(Long id) {
        try {
            Bill bill = billDao.load(Bill.class, id);
            if (bill != null) {
                if (bill.getCompany() != null) {
                    bill.getCompany().getId();
                    bill.getCompany().getName();
                }
                if (bill.getPertain() != null) {
                    bill.getPertain().getUserId();
                    bill.getPertain().getName();
                }
            }
            return bill;
        } catch (Exception ex) {
            throw new OAException("获取要修改的帐单时出现异常", ex);
        }
    }

    @Override
    public void updateBill(Bill bill) {
        try {
            // 设置总价
            if (bill.getCompany() != null) bill.setCompany(companyDao.get(Company.class, bill.getCompany().getId()));
            Double price = bill.getCompany().getPrice();
            Double usageAmount = bill.getUsageAmount();
            bill.setSumPrice(NumUtil.mul(price, usageAmount));
            billDao.update(bill);
        } catch (Exception ex) {
            throw new OAException("修改帐单时出现异常", ex);
        }
    }
}
