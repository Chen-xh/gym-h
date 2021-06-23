package com.chen.gym.service.field;

import com.chen.gym.bean.FieldUseInfo;

import java.util.Date;
import java.util.List;

public interface FieldUseInfoService {
    /**
     * 查询所有场地使用信息
     */
    List<FieldUseInfo> findAll();

    /**
     * 查询所有待审核状态的场地使用信息
     */
    List<FieldUseInfo> findAllBePass();

    /**
     * 查询所有租出场地使用信息
     */
    List<FieldUseInfo> findAllRenting();

    /**
     * 查询所有已回收场地使用信息
     */
    List<FieldUseInfo> findAllRecover();

    /**
     * 通过用户账号查询场地使用信息
     */
    List<FieldUseInfo> findFieldUseInfoBySno(String sno);

    /**
     * 通过id查询场地使用信息
     */
    FieldUseInfo findFieldUseInfoByID(Long id);

    /**
     * 通过记录状态标签查询场地使用信息
     */
    List<FieldUseInfo> findFieldUseInfoByTarget(int target);

    /**
     * 添加场地使用信息
     */
    void add(FieldUseInfo fieldUseInfo);

    /**
     * 更新场地使用信息
     */
    void update(FieldUseInfo fieldUseInfo);

    /**
     * 删除场地使用信息
     */
    void delete(Long id);

    /**
     * 更新标签标识
     */
    void updateTarget(Long id, int target);

    /**
     * 添加上课场地
     */
    void setInClass(FieldUseInfo fieldUseInfo);

    /**
     * 多条件查询使用信息
     */
    List<FieldUseInfo> select(FieldUseInfo fieldUseInfo);

}
