package com.chen.gym.service.equipment;

import com.chen.gym.bean.Equipment;
import com.chen.gym.bean.EquipmentKind;

import java.util.List;

public interface EquipmentService {
    /**
     * 查询所有
     */
    List<Equipment> findAll();

    /**
     * 多条件查询
     */
    List<Equipment> select(Equipment equipment);
    /**
     * 根据id查询
     */
    Equipment findEquipmentById(Long id);

    /**
     * 添加
     */
    void add(Equipment equipment);
    /**
     * 删除
     */
    void delete(Long id);
    /**
     * 修改
     */
    void update(Equipment equipment);

    /**
     * 获取所有器材类型
     */
    List<EquipmentKind> getAllKind();
}
