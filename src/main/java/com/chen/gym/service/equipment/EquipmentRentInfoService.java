package com.chen.gym.service.equipment;

import com.chen.gym.bean.EquipmentRentInfo;

import java.util.List;

public interface EquipmentRentInfoService {
    /**
     * 查询所有租用记录
     */
    List<EquipmentRentInfo> findAll();

    /**
     * 查询所有待回收
     */
    List<EquipmentRentInfo> findAllBeRecover();

    /**
     * 查询所有回收
     */
    List<EquipmentRentInfo> findAllRecover();

    /**
     * 根据租用状态查询
     */
    List<EquipmentRentInfo> findRentInfoByTarget(int target);

    /**
     * 根据学号或教工号查询租用记录
     */
    List<EquipmentRentInfo> findRentInfoBySno(String sno);

    /**
     * 根据ID查询租用记录
     */
    EquipmentRentInfo findRentInfoById(Long id);

    /**
     * 添加租用记录
     */
    void add(EquipmentRentInfo equipmentRentInfo);

    /**
     * 通过审批
     */
    void passRequire(Long id);

    /**
     * 回收器材
     * target = 4：普通回收
     * target = 6：超时回收
     */
    void recover(EquipmentRentInfo equipmentRentInfo,int target);

    /**
     * 更新标签
     */
    void updateTarget(int target, Long id);

    /**
     * 删除器材租用记录
     */
    void delete(Long id);

    /**
     * 多条件查询
     */
    List<EquipmentRentInfo> select(EquipmentRentInfo equipmentRentInfo);
}
