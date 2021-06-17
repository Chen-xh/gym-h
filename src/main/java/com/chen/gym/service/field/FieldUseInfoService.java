package com.chen.gym.service.field;

import com.chen.gym.bean.FieldUseInfo;

import java.util.List;

public interface FieldUseInfoService {
    /**
     * 查询所有场地使用信息
     */
    List<FieldUseInfo> findAll();

    /**
     * 查询所有租出场地使用信息
     */
    List<FieldUseInfo> findAllRenting();

    /**
     * 查询所有上课使用场地使用信息
     */
    List<FieldUseInfo> findAllInClass();

    /**
     * 查询所有已回收场地使用信息
     */
    List<FieldUseInfo> findAllRecover();

    /**
     * 通过id查询场地使用信息
     */
    FieldUseInfo findFieldUseInfoByID(Long id);

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
     * 回收场地使用信息
     */
    void recover(Long id);

    /**
     * 多条件查询使用信息
     */
    List<FieldUseInfo> select(FieldUseInfo fieldUseInfo);

}
