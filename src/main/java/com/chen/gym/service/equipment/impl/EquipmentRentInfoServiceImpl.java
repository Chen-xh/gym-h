package com.chen.gym.service.equipment.impl;

import com.chen.gym.bean.EquipmentRentInfo;
import com.chen.gym.service.equipment.EquipmentRentInfoService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentRentInfoServiceImpl implements EquipmentRentInfoService {
    @Override
    public List<EquipmentRentInfo> findAll() {
        return null;
    }

    @Override
    public List<EquipmentRentInfo> findAllRenting() {
        return null;
    }

    @Override
    public List<EquipmentRentInfo> findAllRecovering() {
        return null;
    }

    @Override
    public EquipmentRentInfo findRentInfoById(Long id) {
        return null;
    }

    @Override
    public void add(EquipmentRentInfo equipmentRentInfo) {

    }

    @Override
    public void recover(EquipmentRentInfo equipmentRentInfo) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<EquipmentRentInfo> select(EquipmentRentInfo equipmentRentInfo) {
        return null;
    }
}
