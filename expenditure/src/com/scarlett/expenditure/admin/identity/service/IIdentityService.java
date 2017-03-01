package com.scarlett.expenditure.admin.identity.service;

import java.util.List;
import java.util.Map;

import com.scarlett.expenditure.admin.identity.entity.Module;
import org.springframework.stereotype.Service;

import com.scarlett.expenditure.admin.identity.entity.Role;
import com.scarlett.expenditure.admin.identity.entity.User;
import com.scarlett.expenditure.core.pojo.PageModel;

/**
 *IIdentityService.java 
 *@intention 
 * <p> 权限管理的业务接口 </p>
 * @author Scarlett
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public interface IIdentityService {
    int CODE_LEN = 4;

    /**
     * 初始化用户的分页实体
     * @param user
     * @param pageModel
     */
    void countUser(User user, PageModel pageModel);
    
    /**
     * 按条件加载用户分页数据
     * @param user
     * @param pageModel
     * @return
     */
    List<Map<String, Object>> loadUserPageData(User user, PageModel pageModel);

    /**
     * 异步验证用户名是否重复
     * @param userId
     * @return
     */
    boolean validUserId(String userId);
    
    /**
     * 添加用户
     * @param user
     */
    void saveUser(User user);
    
    /**
     * 获取一个用户
     * @param userId
     * @return
     */
    User getUser(String userId);
    
    /**
     * 修改用户
     * @param user
     */
    void updateUser(User user);
    
    /**
     * 批量删除用户
     * @param split
     */
    void deleteUser(String[] split);
    
    /**
     * 批量审核用户
     * @param split
     * @param status
     */
    void checkUser(String[] split, Short status);
    
    /**
     * 模糊获取用户姓名
     * @param name
     * @return
     */
    List<Object> loadUserName(String name);
    
    /**
     * 分页查询角色
     * @param pageModel 分页实体
     * @return
     */
    List<Map<String, Object>> getRoleByPage(PageModel pageModel);

    /**
     * 添加角色
     * @param role
     */
    void saveRole(Role role);
    /**
     * 根据主键id获取角色
     * @param id 角色id
     * @return 角色
     */
    Role getRole(Long id);
    /**
     * 修改角色
     * @param role 角色
     */
    void updateRole(Role role);
    /**
     * 批量删除
     * @param ids
     */
    void deleteRole(String[] ids);
    
    /**
     * 查询统计角色
     * @param pageModel
     */
    void countRole(PageModel pageModel);
    /**
     * 加载生成操作树
     * @return List
     */
    List<Map<String, Object>> loadModuleTree();
    /**
     * 分页查询操作
     * @param parentCode 父级编号
     * @param pageModel 分页实体
     * @return
     */
    List<Map<String, Object>> getModuleByPage(String parentCode, PageModel pageModel);
    /**
     * 添加操作
     * @param module 操作
     */
    void saveModule(Module module);
    /**
     * 根据主键code查询操作
     * @param code 主键
     * @return 操作
     */
    Module getModule(String code);
    /**
     * 修改操作
     * @param module
     */
    void updateModule(Module module);
    /**
     * 删除操作
     * @param codes
     */
    void deleteModule(String[] codes);
    /**
     * 把在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return
     */
    List<Map<String, Object>> getUserByPageAndRoleId(Role role, PageModel pageModel);
    /**
     * 把不在该角色中的用户查询出来
     * @param role 角色
     * @param pageModel 分页实体
     * @return
     */
    List<Map<String, Object>> getUserByPageNotRoleId(Role role, PageModel pageModel);
    /**
     * 角色绑定用户
     * @param role
     * @param userIds
     */
    void bindUser(Role role, String[] userIds);
    /**
     * 角色解除用户
     * @param role
     * @param userIds
     */
    void unbindUser(Role role, String[] userIds);
    /**
     * 加载权限树
     * @return List
     */
    List<Map<String, Object>> loadPopedomTree();
    /**
     * 查询操作(权限)
     * @param moduleCode 八位
     * @return List
     */
    List<Module> getModuleByCode8(String moduleCode);
    /**
     * 角色绑定操作
     * @param role 角色
     * @param moduleCode 模块代号
     * @param codes
     */
    void bindModule(Role role, String moduleCode, String codes);
    /**
     * 根据角色与模块代号查询操作
     * @return List
     */
    List<String> getOperaCodesByModuleCodeAndRoleId(String moduleCode, Long id);
    /**
     * 加载的菜单树
     * @return List
     */
    List<Map<String, Object>> loadMenuTree();
    /**
     * 获取该用户所有的权限
     * @param userId
     * @return Map
     */
    Map<String, List<String>> getAllPopedomByUserId(String userId);

    /**
     * 统计出子操作的数量
     * @param parentCode
     * @param pageModel
     */
    void countModule(String parentCode, PageModel pageModel);

    /**
     * 把在该角色中的用户个数查询出来
     * @param role
     * @param pageModel
     * @return
     */
    void countUserByPageAndRoleId(Role role, PageModel pageModel);

    /**
     * 把不在该角色中的用户的个数查询出来
     * @param role
     * @param pageModel
     */
    void countUserByPageNotRoleId(Role role, PageModel pageModel);

    /**
     * 异步加载权限树
     * @return
     */
    List<Map<String,Object>> loadPopedomTreeAjax();

    /**
     * 登录
     * @param userId
     * @param password
     * @param vcode
     * @param key
     * @return
     */
    Map<String,Object> login(String userId, String password, String vcode, int key);
}
