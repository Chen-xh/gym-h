package com.chen.gym.service.equipment;

import com.chen.gym.bean.EquipmentRentInfo;

import java.util.List;

public interface EquipmentRentInfoService {
    /**
     * 查询所有租用记录
     */
    List<EquipmentRentInfo> findAll();

    /**
     * 查询所有租出中记录
     */
    List<EquipmentRentInfo> findAllRenting();

    /**
     * 查询所有回收记录
     */
    List<EquipmentRentInfo> findAllRecovering();

    /**
     * 根据ID查询租用记录
     */
    EquipmentRentInfo findRentInfoById(Long id);

    /**
     * 添加租用记录
     */
    void add(EquipmentRentInfo equipmentRentInfo);

    /**
     * 回收器材并更新租用记录状态
     */
    void recover(EquipmentRentInfo equipmentRentInfo);

    /**
     * 删除器材租用记录
     */
    void delete(Long id);

    /**
     * 多条件查询
     */
    List<EquipmentRentInfo> select(EquipmentRentInfo equipmentRentInfo);
}
