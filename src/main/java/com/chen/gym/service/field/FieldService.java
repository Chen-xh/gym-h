package com.chen.gym.service.field;

import com.chen.gym.bean.Field;

import java.util.List;

public interface FieldService {
    /**
     * 查找全部场地
     */
    List<Field> findAll();

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
     * 多条件查询
     */
    List<Field> select(Field field);
}
