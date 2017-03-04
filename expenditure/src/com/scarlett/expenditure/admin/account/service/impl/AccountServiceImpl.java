package com.scarlett.expenditure.admin.account.service.impl;

import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.account.service.IAccountService;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.pojo.PageModel;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *AccountServiceImpl.java
 *@intention
 * <p> 付费易帐户的业务实体类 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class AccountServiceImpl implements IAccountService{

    @Resource
    private IFFYDao FFYDao;

    @Override
    public void countFFY(User user, PageModel pageModel) {
        try {
            pageModel.setRecordCount(FFYDao.countByPage(user));
        } catch (Exception ex) {
            throw new OAException("按条件、分页统计帐号的个数时出现异常", ex);
        }
    }

    @Override
    public List<Map<String, Object>> loadFFYAjax(User user, PageModel pageModel) {
        List<Map<String, Object>> listMap = null;
        Map<String, Object> data;
        try {
            List<FuFeiYi> list = FFYDao.loadFFYByPage(user, pageModel);
            if (list != null && list.size() > 0) {
                listMap = new ArrayList<Map<String, Object>>();
                for (FuFeiYi ffy: list) {
                    data = new HashMap<String, Object>();
                    data.put("userId", ffy.getUser().getUserId());
                    data.put("userName", ffy.getUser().getName());
                    data.put("phone", ffy.getUser().getPhone());
                    data.put("sum", ffy.getSum());
                    String[] statusArrs = { "<font color='red'>新建</font>", "<font>审核</font>",
                            "<font color='#ffddcc'>不通过</font>", "<font color='Thistle'>冻结</font>" };
                    data.put("status", statusArrs[ffy.getUser().getStatus()]);
                    data.put("remark", ffy.getRemark());
                    listMap.add(data);
                }
            }
        } catch (Exception ex) {
            throw new OAException("按条件异步加载用户列表时出现异常", ex);
        }
        return listMap;
    }
}
