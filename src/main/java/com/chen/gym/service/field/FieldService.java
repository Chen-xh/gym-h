package com.chen.gym.service.field;

import com.chen.gym.bean.Field;

import java.util.List;

public interface FieldService {
    /**
     * 查找全部场地
     */
    List<Field> findAll();

    /**
     * 查找全部在上课中场地
     */
    List<Field> findAllInClass();

    /**
     * 查找全部租出中场地
     */
    List<Field> findAllRenting();

    /**
     * 查找全部空闲中场地
     */
    List<Field> findAllIdle();

    /**
     * 根据ID查找查各地
     */
    Field findFieldByID(Long id);

    /**
     * 添加场地
     * @param field 添加的场地信息
     */
    void addField(Field field);

    /**
     * 删除场地
     * @param id 要删除的场地id
     */
    void deleteField(Long id);

    /**
     * 更新场地
     * @param field 要更新的场地信息
     */
    void updateField(Field field);

    /**
     * 设置场地为租借中
     */
    void setFieldRenting(Field field);

    /**
     * 设置场地为空闲中
     */
    void setFieldIdle(Field field);

    /**
     * 设置场地为上课中
     */
    void setFieldInClass(Field field);

    /**
     * 多条件查询
     */
    List<Field> select(Field field);
}
