package com.scarlett.expenditure.admin.identity.service.impl;

import java.util.*;

import javax.annotation.Resource;

import com.opensymphony.xwork2.ActionContext;
import com.scarlett.expenditure.admin.AdminConstant;
import com.scarlett.expenditure.admin.account.dao.IFFYDao;
import com.scarlett.expenditure.admin.account.entity.FuFeiYi;
import com.scarlett.expenditure.admin.identity.dao.IModuleDao;
import com.scarlett.expenditure.admin.identity.dao.IPopedomDao;
import com.scarlett.expenditure.admin.identity.entity.Module;
import com.scarlett.expenditure.admin.identity.entity.Popedom;
import com.scarlett.expenditure.core.action.VerifyAction;
import com.scarlett.expenditure.core.dao.GeneratorDao;
import com.scarlett.expenditure.core.util.CookieTools;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.scarlett.expenditure.admin.identity.dao.IRoleDao;
import com.scarlett.expenditure.admin.identity.dao.IUserDao;
import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.admin.identity.service.IIdentityService;
import com.scarlett.expenditure.core.exception.OAException;
import com.scarlett.expenditure.core.pojo.PageModel;
import com.scarlett.expenditure.core.util.MD5;

/**
 * IdentityService.java
 * 
 * @intention <p>
 *            权限的业务实现类
 *            </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class IdentityService implements IIdentityService {
    @Resource
    private IUserDao userDao;

    @Resource
    private IRoleDao roleDao;

    @Resource
    private IModuleDao moduleDao;

    @Resource
    private IPopedomDao popedomDao;

    @Resource
    private GeneratorDao generatorDao;

    @Resource
    private IFFYDao FFYDao;

    /** TODO ##################### 用户管理 ######################### */
    /**
     * 登录
     * @param userId
     * @param password
     * @param vcode
     * @param key
     * @return
     */
    public Map<String, Object> login(String userId, String password, String vcode, int key) {
        try{
            /** 定义Map封装提示信息 */
            Map<String, Object> map = new HashMap<>();
            map.put("tip", "验证码不正确！");
            map.put("status", 1);
            /** 从Session中获取验证码 */
            String code = (String) ActionContext.getContext()
                    .getSession().get(VerifyAction.VERIFY_CODE);
            /** 判断验证码是否正确 */
            if (!StringUtils.isEmpty(vcode) && vcode.equalsIgnoreCase(code)){
                /** 查询用户(用户名与密码) 密码MD5 */
                User user = getUser(userId);
                /** 判断用户是否存在，如果存在，就存入Session */
                if (user != null && user.getPassword().equals(MD5.getMD5(password))){
                    /** 判断状态 */
                    if (user.getStatus() == 1){
                        /** 存入Session */
                        ActionContext.getContext().getSession().put(AdminConstant.SESSION_USER, user);
                        /** 获取该用户所有的权限 */
                        Map<String, List<String>> allPopedoms = getAllPopedomByUserId(user.getUserId());
                        /** 权限存入Session  redis */
                        ActionContext.getContext().getSession().put(AdminConstant.SESSION_USER_POPEDOMS, allPopedoms);

                        /** 是否记住用户(cookie) */
                        if (key == 1){
                            CookieTools.addCookie(AdminConstant.COOKIE_NAME, user.getUserId(), AdminConstant.MAX_AGE);
                        }
                        map.put("tip", "登录成功！");
                        map.put("status", 0);


                    }else{
                        /** 0新建,1审核,2不通过审核,3冻结 */
                        String[] tipArrs = {"新建","审核","不通过审核","冻结"};
                        map.put("tip", "您处于【"+ tipArrs[user.getStatus()]+"】状态，请联系管理员！");
                        map.put("status", 3);
                    }
                }else{
                    map.put("tip", "用户名或密码不正确！");
                    map.put("status", 2);
                }
            }
            /** 返回提示信息 */
            return map;
        }catch(Exception ex){
            throw new OAException("登录方法出现异常！", ex);
        }
    }

    @Override
    public List<Map<String, Object>> loadAllUserAjax() {
        try {
            List<User> users = userDao.getAllUser();
            List<Map<String, Object>> data = new ArrayList<>();
            /** 迭代用户集合 */
            for (User u : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", u.getUserId());
                map.put("name", u.getName());
                data.add(map);
            }
            return data;
        } catch (Exception e) {
            throw new OAException("加载所有用户数据异常！");
        }
    }

    /**
     * 获取该用户所有的权限
     * @param userId
     * @return Map
     */
    public Map<String, List<String>> getAllPopedomByUserId(String userId){
        try{
            /** 根据用户userId查询所有的角色，再根据角色查询所有操作(12位) */
            List<String> operaCodeLists = popedomDao.getOperaCodeByUserId(userId);
            // 000100010001, 000100010002, 000100010003,
            // 000100020001, 000100020002, 000100020003,
            // 000100030001, 000100030002, 000100030003, 000100030004, 000100030005,
            // 000200010001, 000200010002, 000200010003, 000200010004, 000200010005,
            // 000200020001, 000200020002
            // Map: key : 00010001   value : [000100010001, 000100010002, 000100010003]
            Map<String, List<String>> data = new HashMap<>();
            String key = null;
            List<String> value = new ArrayList<>();
            for (String operaCode : operaCodeLists){
                if (key != null && !operaCode.startsWith(key)){ // 00010001
                    data.put(key, value);
                    value = new ArrayList<>();
                }
                /** 截取得到八位 */
                key = operaCode.substring(0, operaCode.length() - CODE_LEN); // 00010002
                value.add(getModule(operaCode).getUrl()); // 12位 000100010001
            }
            /** 添加最后一组 */
            if (key != null && value.size() > 0){
                data.put(key, value);
            }
            for (Map.Entry<String, List<String>> entry : data.entrySet()){
                System.out.println(entry.getKey() + "==" + entry.getValue());
            }
            return data;
        }catch(Exception ex){
            throw new OAException("获取该用户所有的权限出现异常！", ex);
        }
    }

    /**
     * 统计对应子操作的数量
     * @param parentCode
     * @param pageModel
     */
    public void countModule(String parentCode, PageModel pageModel) {
        try {
            if (StringUtils.isEmpty(parentCode) || "0".equals(parentCode))
                pageModel.setRecordCount(moduleDao.countParentModule(CODE_LEN));
            else
                pageModel.setRecordCount(moduleDao.countChildModule(parentCode, parentCode.length() + CODE_LEN));
        } catch (Exception e) {
            throw new OAException("统计对应子操作的数量异常！");
        }
    }

    /**
     * 初始化用户的分页实体
     * 
     * @param user
     * @param pageModel
     */
    public void countUser(User user, PageModel pageModel) {
        try {
            pageModel.setRecordCount(userDao.countUser(user));
        } catch (Exception e) {
            throw new OAException("统计用户异常！");
        }
    }

    /**
     * 按条件加载用户分页数据
     * 
     * @param user
     * @param pageModel
     * @return
     */
    public List<Map<String, Object>> loadUserPageData(User user, PageModel pageModel) {
        try {
            List<User> users = userDao.getUserByPage(user, pageModel);
            List<Map<String, Object>> data = new ArrayList<>();
            /** 迭代用户集合 */
            for (User u : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", u.getUserId());
                map.put("name", u.getName());
                map.put("sex", u.getSex() == 1 ? "男" : "女");
                map.put("qqNum", u.getQqNum());
                map.put("phone", u.getPhone());
                map.put("email", u.getEmail());
                String[] statusArrs = { "<font color='red'>新建</font>", "<font>审核</font>",
                        "<font color='#ffddcc'>不通过</font>", "<font color='Thistle'>冻结</font>" };
                map.put("status", statusArrs[u.getStatus()]);
                map.put("createDate", u.getCreateDate() != null ? u.getCreateDate() : "");
                map.put("creater", u.getCreater() != null ? u.getCreater().getName() : "");
                map.put("checkDate", u.getCheckDate() != null ? u.getCheckDate() : "");
                map.put("checker", u.getChecker() != null ? u.getChecker().getName() : "");
                data.add(map);
            }
            return data;
        } catch (Exception e) {
            throw new OAException("按条件加载用户分页数据异常！");
        }
    }

    /**
     * 异步验证用户名是否重复
     */
    public boolean validUserId(String userId) {
        try {
            User user = userDao.get(User.class, userId);
            return user != null;
        } catch (Exception e) {
            throw new OAException("异步验证用户名是否重复异常！");
        }
    }

    /**
     * 添加用户
     * 
     * @param user 用户实体
     */
    public void saveUser(User user) {
        try {
            user.setCreateDate(new Date());
            // user.setCreater(AdminConstant.getSessionUser());
            user.setPassword(MD5.getMD5(user.getPassword()));
            user.setStatus((short) 0);
            userDao.save(user);
            // 新增用户后，绑定一个付费易帐号
            FuFeiYi ffy = new FuFeiYi();
            ffy.setUser(user);
            // 原始的交易密码：12345
            ffy.setPassword(MD5.getMD5("12345"));
            ffy.setSum(0.00);
            FFYDao.save(ffy);
        } catch (Exception ex) {
            throw new OAException("添加用户时出现异常！", ex);
        }
    }

    /**
     * 修改用户
     * 
     * @param user 用户实体
     */
    public void updateUser(User user) {
        try {
            /** 获取持久化状态的对象 */
            User u = userDao.get(User.class, user.getUserId());
            u.setName(user.getName());
            u.setAnswer(user.getAnswer());
            u.setEmail(user.getEmail());
            // u.setModifier(AdminConstant.getSessionUser());
            u.setModifyDate(new Date());
            u.setPhone(user.getPhone());
            u.setQqNum(user.getQqNum());
            u.setQuestion(user.getQuestion());
            u.setSex(user.getSex());
            u.setTel(user.getTel());
        } catch (Exception ex) {
            throw new OAException("修改用户时出现异常！", ex);
        }
    }

    /**
     * 批量删除用户
     * 
     * @param userIds
     */
    public void deleteUser(String[] userIds) {
        try {
            /** 第一种方式 */
            /*
             * for (String userId : userIds){ userDao.delete(getUser(userId)); }
             */
            /** 第二种方式 */
            /*
             * for (String userId : userIds){ User user = new User(); user.setUserId(userId);
             * userDao.delete(user); }
             */
            /** 第三种方式 */
            userDao.deleteUser(userIds);
        } catch (Exception ex) {
            throw new OAException("批量删除用户时出现异常！", ex);
        }
    }

    /**
     * 批量审核用户
     * 
     * @param userIds
     * @param status
     */
    public void checkUser(String[] userIds, Short status) {
        try {
            /** 第一种方式 */
            /*
             * for (String userId : userIds){ User u = userDao.get(User.class, userId); u.setXxx();
             * }
             */
            /** 第二种方式 */
            userDao.checkUser(userIds, status);
        } catch (Exception ex) {
            throw new OAException("批量删除用户时出现异常！", ex);
        }
    }

    /**
     * 根据userId获取用户
     * 
     * @param userId 用户名
     * @return 用户
     */
    public User getUser(String userId) {
        try {
            User user = userDao.get(User.class, userId);
            return user;
        } catch (Exception ex) {
            throw new OAException("根据userId获取用户方法时出现异常！", ex);
        }
    }

    /**
     * 查询用户姓名
     * 
     * @param name 姓名
     * @return List
     */
    public List<Object> loadUserName(String name) {
        try {
            // ["",""]
            return userDao.getUserByNames(name);
        } catch (Exception ex) {
            throw new OAException("查询用户姓名时出现异常！", ex);
        }
    }

    /** TODO ##################### 角色管理 ######################### */
    /**
     * 统计角色总数，并初始化分页实体
     * 
     * @param pageModel 分页实体
     */
    public void countRole(PageModel pageModel) {
        try {
            pageModel.setRecordCount(roleDao.countRole());
        } catch (Exception e) {
            throw new OAException("统计角色总数，初始化分页实体异常！", e);
        }
    }

    /**
     * 分页查询角色
     * 
     * @param pageModel 分页实体
     * @return 角色集合
     */
    public List<Map<String, Object>> getRoleByPage(PageModel pageModel) {
        try {
            List<Role> roles = roleDao.getRoleByPage(pageModel);
            List<Map<String, Object>> listMap = new ArrayList<>();
            Map<String, Object> map = null;
            for (Role role : roles) {
                map = new HashMap<String, Object>();
                if (role.getCreater() != null) {
                    map.put("creater", role.getCreater().getName());
                } else {
                    map.put("creater", "");
                }
                if (role.getModifier() != null) {
                    map.put("modifier", role.getModifier().getName());
                } else {
                    map.put("modifier", "");
                }
                map.put("id", role.getId());
                map.put("name", role.getName());
                map.put("remark", role.getRemark());
                map.put("createDate", role.getCreateDate() == null ? "" : role.getCreateDate());
                map.put("modifyDate", role.getModifyDate() == null ? "" : role.getModifyDate());
                listMap.add(map);
            }
            return listMap;
        } catch (Exception ex) {
            throw new OAException("分页查询角色时出现异常！", ex);
        }
    }

    /**
     * 添加角色
     * 
     * @param role
     */
    public void saveRole(Role role) {
        try {
            role.setCreateDate(new Date());
            // role.setCreater(AdminConstant.getSessionUser());
            roleDao.save(role);
        } catch (Exception ex) {
            throw new OAException("添加角色时出现异常！", ex);
        }
    }

    /**
     * 根据主键id获取角色
     * 
     * @param id 角色id
     * @return 角色
     */
    public Role getRole(Long id) {
        try {
            return roleDao.get(Role.class, id);
        } catch (Exception ex) {
            throw new OAException("根据主键id获取角色时出现异常！", ex);
        }
    }

    /**
     * 修改角色
     * 
     * @param role 角色
     */
    public void updateRole(Role role) {
        try {
            Role r = getRole(role.getId());
            // r.setModifier(AdminConstant.getSessionUser());
            r.setModifyDate(new Date());
            r.setName(role.getName());
            r.setRemark(role.getRemark());
        } catch (Exception ex) {
            throw new OAException("修改角色时出现异常！", ex);
        }
    }

    /**
     * 批量删除
     * 
     * @param ids
     */
    public void deleteRole(String[] ids) {
        try {
            roleDao.deleteRole(ids);
        } catch (Exception ex) {
            throw new OAException("批量删除时出现异常！", ex);
        }
    }

    /**
     * 把在该角色中的用户个数查询出来
     * @param role
     * @param pageModel
     * @return
     */
    public void countUserByPageAndRoleId(Role role, PageModel pageModel){
        try {
            pageModel.setRecordCount(userDao.countUserByPageAndRoleId(role));
        } catch (Exception ex) {
            throw new OAException("角色中的用户个数查询出现异常！", ex);
        }
    }

    /**TODO ##################### 操作管理 ######################### */
    /**
     * 加载生成操作树
     * @return List
     */
    public List<Map<String, Object>> loadModuleTree(){
        try{
            // [{id : '', pid : '', name : ''},{id : '', pid : '', name : ''}]
            List<Map<String, Object>> data = moduleDao.getModuleByCodeAndName();
            for (Map<String, Object> map : data){
                String code = map.get("id").toString();
                /** 计算pid */
                String pid = code.length() == CODE_LEN ? "0" : code.substring(0, code.length() - CODE_LEN);
                map.put("pid", pid);
                map.put("name", map.get("name").toString().replaceAll("-", ""));
            }
            return data;
        }catch(Exception ex){
            throw new OAException("加载生成操作树时出现异常！", ex);
        }
    }
    /**
     * 分页查询操作
     * @param parentCode 父级编号
     * @param pageModel 分页实体
     * @return
     */
    public List<Map<String, Object>> getModuleByPage(String parentCode, PageModel pageModel){
        try{
            List<Module> modules = moduleDao.getModuleByPage(parentCode,pageModel,CODE_LEN);
            List<Map<String, Object>> listMap = new ArrayList<>();
            Map<String, Object> map = null;
            for (Module m : modules){
                map = new HashMap<>();
                map.put("code", m.getCode());
                map.put("name", m.getName());
                map.put("url", m.getUrl());
                map.put("remark", m.getRemark());
                map.put("createDate", m.getCreateDate() == null ? "" : m.getCreateDate());
                map.put("modifyDate", m.getModifyDate() == null ? "" : m.getModifyDate());
                map.put("creater", m.getCreater() == null ? "" : m.getCreater().getName());
                map.put("modifier", m.getModifier() == null ? "" : m.getModifier().getName());
                listMap.add(map);
            }
            return listMap;
        }catch(Exception ex){
            throw new OAException("分页查询操作时出现异常！", ex);
        }
    }
    /**
     * 添加操作
     * @param module 操作
     */
    public void saveModule(Module module){
        try{
            /** 获取父级code */
            String parentCode = module.getCode();
            parentCode = StringUtils.isEmpty(parentCode) ? "" : parentCode;
            /** 生成主键code值 */
            String code = generatorDao.generatorKey(Module.class, "code", parentCode, CODE_LEN);
            System.out.println("code: " + code);
            module.setName(parentCode.replaceAll(".", "-") + module.getName());
            /** 主键code */
            module.setCode(code);
            module.setCreateDate(new Date());
            module.setCreater(AdminConstant.getSessionUser());
            moduleDao.save(module);
        }catch(Exception ex){
            throw new OAException("添加操作时出现异常！", ex);
        }
    }
    /**
     * 根据主键code查询操作
     * @param code 主键
     * @return 操作
     */
    public Module getModule(String code){
        try{
            return moduleDao.get(Module.class, code);
        }catch(Exception ex){
            throw new OAException("根据主键code查询操作时出现异常！", ex);
        }
    }
    /**
     * 修改操作
     * @param module
     */
    public void updateModule(Module module){
        try{
            /** 截取父级code */
            String parentCode = module.getCode().substring(0, module.getCode().length() - CODE_LEN);
            Module m = getModule(module.getCode());
            m.setModifier(AdminConstant.getSessionUser());
            m.setModifyDate(new Date());
            m.setName(parentCode.replaceAll(".", "-") + module.getName());
            m.setRemark(module.getRemark());
            m.setUrl(module.getUrl());
        }catch(Exception ex){
            throw new OAException("修改操作时出现异常！", ex);
        }
    }
    /**
     * 删除操作
     * @param codes
     */
    public void deleteModule(String[] codes){
        try{
            moduleDao.deleteModule(codes);
        }catch(Exception ex){
            throw new OAException("删除操作时出现异常！", ex);
        }
    }
    /**
     * 把在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    public List<Map<String, Object>> getUserByPageAndRoleId(Role role, PageModel pageModel){
        try{
            List<User> users = userDao.getUserByPageAndRoleId(role, pageModel);
            List<Map<String, Object>> data = new ArrayList<>();
            /** 迭代用户集合 */
            for (User u : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", u.getUserId());
                map.put("name", u.getName());
                map.put("sex", u.getSex() == 1 ? "男" : "女");
                map.put("email", u.getEmail());
                map.put("phone", u.getPhone());
                String[] statusArrs = { "<font color='red'>新建</font>", "<font>审核</font>",
                        "<font color='#ffddcc'>不通过</font>", "<font color='Thistle'>冻结</font>" };
                map.put("status", statusArrs[u.getStatus()]);
                map.put("createDate", u.getCreateDate() != null ? u.getCreateDate() : "");
                map.put("creater", u.getCreater() != null ? u.getCreater().getName() : "");
                map.put("checkDate", u.getCheckDate() != null ? u.getCheckDate() : "");
                map.put("checker", u.getChecker() != null ? u.getChecker().getName() : "");
                data.add(map);
            }
            return data;
        }catch(Exception ex){
            throw new OAException("把在该角色中的用户查询出来时出现异常！", ex);
        }
    }

    /**
     * 把不在该角色中的用户的个数查询出来
     * @param role
     * @param pageModel
     */
    public void countUserByPageNotRoleId(Role role, PageModel pageModel){
        try{
            int num = userDao.countUserByPageNotRoleId(role);
            pageModel.setRecordCount(num);
            System.out.println("-----不在该角色的用户有" + num + "个-----");
        }catch(Exception ex){
            throw new OAException("角色中的用户的个数查询出现异常！", ex);
        }
    }

    /**
     * 异步加载权限树
     * @return
     */
    public List<Map<String, Object>> loadPopedomTreeAjax() {
        try{
            // 1.拿到id和name
            List<Map<String, Object>> data = moduleDao.getModuleByCodeAndName(CODE_LEN*2);
            // 2.封装
            for (Map<String, Object> map : data) {
                String id = map.get("id").toString();
                String pid = id.length() == CODE_LEN ? "0" : id.substring(0, id.length() - CODE_LEN);
                map.put("pId", pid);
                map.put("name", map.get("name").toString().replaceAll("-", ""));
            }
            return data;
        }catch(Exception ex){
            throw new OAException("异步加载权限树出现异常！", ex);
        }
    }

    /**
     * 把不在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return List
     */
    public List<Map<String, Object>> getUserByPageNotRoleId(Role role, PageModel pageModel){
        try{
            List<User> users = userDao.getUserByPageNotRoleId(role, pageModel);
            List<Map<String, Object>> data = new ArrayList<>();
            /** 迭代用户集合 */
            for (User u : users) {
                Map<String, Object> map = new HashMap<>();
                map.put("userId", u.getUserId());
                map.put("name", u.getName());
                map.put("sex", u.getSex() == 1 ? "男" : "女");
                map.put("phone", u.getPhone());
                String[] statusArrs = { "<font color='red'>新建</font>", "<font>审核</font>",
                        "<font color='#ffddcc'>不通过</font>", "<font color='Thistle'>冻结</font>" };
                map.put("status", statusArrs[u.getStatus()]);
                map.put("createDate", u.getCreateDate() != null ? u.getCreateDate() : "");
                map.put("creater", u.getCreater() != null ? u.getCreater().getName() : "");
                data.add(map);
            }
            return data;
        }catch(Exception ex){
            throw new OAException("把不在该角色中的用户查询出来时出现异常！", ex);
        }
    }
    /**
     * 角色绑定用户
     * @param role
     * @param userIds
     */
    public void bindUser(Role role, String[] userIds){
        try{
            /** 多对多时，哪边可以维护中间表 Role 没有加mappedBy */
            Role r = getRole(role.getId());
            /** 获取该角色已有的用户 */
            Set<User> users = r.getUsers();
            /** 循环添加新的用户 */
            for (String userId : userIds){
                User user = new User();
                user.setUserId(userId);
                users.add(user);
            }
            r.setUsers(users);
        }catch(Exception ex){
            throw new OAException("角色绑定用户时出现异常！", ex);
        }
    }
    /**
     * 角色解除用户
     * @param role
     * @param userIds
     */
    public void unbindUser(Role role, String[] userIds){
        try{
            /** 多对多时，哪边可以维护中间表 Role 没有加mappedBy */
            Role r = getRole(role.getId());
            /** 获取该角色已有的用户 */
            Set<User> users = r.getUsers();
            /** 循环添加新的用户 */
            for (String userId : userIds){
                User user = new User();
                user.setUserId(userId);
                users.remove(user);
            }
            r.setUsers(users);
        }catch(Exception ex){
            throw new OAException("角色解除用户时出现异常！", ex);
        }
    }
    /**
     * 加载权限树
     * @return List
     */
    public List<Map<String, Object>> loadPopedomTree(){
        try{
            List<Map<String, Object>> data = moduleDao.getModuleByCodeAndName(CODE_LEN * 2);
            for (Map<String, Object> map : data){
                String id = map.get("id").toString();
                String pid = id.length() == CODE_LEN ? "0" : id.substring(0, id.length() - CODE_LEN);
                map.put("pId", pid);
                map.put("name", map.get("name").toString().replaceAll("-", ""));
            }
            return data;
        }catch(Exception ex){
            throw new OAException("加载权限树时出现异常！", ex);
        }
    }
    /**
     * 查询操作(权限)
     * @param moduleCode 八位
     * @return List
     */
    public List<Module> getModuleByCode8(String moduleCode){
        try{
            return moduleDao.getModuleByCode8(moduleCode, CODE_LEN);
        }catch(Exception ex){
            throw new OAException("查询操作(权限)时出现异常！", ex);
        }
    }
    /**
     * 角色绑定操作
     * @param role 角色
     * @param moduleCode 模块代号
     * @param codes
     */
    public void bindModule(Role role, String moduleCode, String codes){
        try{

            /** 删除以前绑定的权限 */
            popedomDao.deletePopedom(role.getId(), moduleCode);

            if (!StringUtils.isEmpty(codes)){
                Module module = new Module();
                module.setCode(moduleCode); // moduleCode : 8位编号
                String[] operas = codes.split(",");
                for (String operaCode : operas){
                    // operaCode : 12位编号
                    Popedom popedom = new Popedom();
                    popedom.setCreateDate(new Date());
                    popedom.setCreater(AdminConstant.getSessionUser());
                    popedom.setModule(module); // 模块

                    Module opera = new Module();
                    opera.setCode(operaCode);
                    popedom.setOpera(opera); // 操作
                    popedom.setRole(role);
                    popedomDao.save(popedom);
                }
            }
        }catch(Exception ex){
            throw new OAException("角色绑定操作时出现异常！", ex);
        }
    }
    /**
     * 根据角色与模块代号查询操作
     * @return List
     */
    public List<String> getOperaCodesByModuleCodeAndRoleId(String moduleCode, Long id){
        try{
            return popedomDao.getOperaCodesByModuleCodeAndRoleId(moduleCode, id);
        }catch(Exception ex){
            throw new OAException("根据角色与模块代号查询操作时出现异常！", ex);
        }
    }
    /**
     * 加载的菜单树
     * @return List
     */
    public List<Map<String, Object>> loadMenuTree(){
        try{
            /** 根据当前Session中的用户userId查询他所有的角色，再根据角色查询所有权限(code为八位) */
            List<String> moduleCodeLists = popedomDao.getModuleCodeByUserId(AdminConstant.getSessionUser().getUserId());
            System.out.println(moduleCodeLists);
            // 00010001, 00010002, 00010003,
            // 00020001, 00020002, 00020003, 00020004,
            // 00030001, 00030002,
            // 00040001, 00040002
            List<Map<String, Object>> data = new ArrayList<>();
            // [{id : "1", pid: "", name : "", url : ""},{id : "1", pid: "", name : "", url : ""}]

            /** 定义四位的code */
            String parentCode = null;

            /** 迭代所有的模块代号 */
            for (String moduleCode : moduleCodeLists){

                /**############# code为八位 ###############*/
                // {id : "1", pid: "", name : "", url : ""}
                Map<String, Object> map = new HashMap<>();
                map.put("id", moduleCode);  // 00010001 00010002   00020001
                map.put("pid", moduleCode.substring(0, moduleCode.length() - CODE_LEN)); // 0001
                Module module = getModule(moduleCode);
                map.put("name", module.getName().replaceAll("-", ""));
                map.put("url", module.getUrl());
                data.add(map);

                if (parentCode != null && moduleCode.startsWith(parentCode)){
                    continue;
                }

                /**############# code为四位 ###############*/
                // {id : "1", pid: "", name : "", url : ""}
                parentCode =  moduleCode.substring(0, moduleCode.length() - CODE_LEN); // 0001
                map = new HashMap<>();
                map.put("id", parentCode);
                map.put("pid", "0");
                map.put("name", getModule(parentCode).getName());
                map.put("url", "");
                data.add(map);
            }

            return data;
        }catch(Exception ex){
            throw new OAException("加载的菜单树时出现异常！", ex);
        }
    }

}
