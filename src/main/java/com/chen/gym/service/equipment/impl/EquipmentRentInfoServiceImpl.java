package com.chen.gym.service.equipment.impl;

import com.chen.gym.bean.EquipmentRentInfo;
import com.chen.gym.dao.equipment.EquipmentRentInfoDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.equipment.EquipmentRentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class EquipmentRentInfoServiceImpl implements EquipmentRentInfoService {
    EquipmentRentInfoDao equipmentRentInfoDao;
    private static final Logger PLOG = LoggerFactory.getLogger(EquipmentRentInfoServiceImpl.class);

    @Override
    public List<EquipmentRentInfo> findAll() {
        return equipmentRentInfoDao.findAll();
    }

    @Override
    public List<EquipmentRentInfo> findAllRenting() {
        return equipmentRentInfoDao.findAllRenting();
    }

    @Override
    public List<EquipmentRentInfo> findAllRecovering() {
        return equipmentRentInfoDao.findAllRecovering();
    }

    @Override
    public EquipmentRentInfo findRentInfoById(Long id) {
        EquipmentRentInfo equipmentRentInfo =equipmentRentInfoDao.findRentInfoById(id);
        if(equipmentRentInfo == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }
        return equipmentRentInfo;
    }

    @Override
    public void add(EquipmentRentInfo equipmentRentInfo) {
        equipmentRentInfoDao.add(equipmentRentInfo);
    }

    @Override
    public void recover(EquipmentRentInfo equipmentRentInfo) {
        EquipmentRentInfo item =equipmentRentInfoDao.findRentInfoById(equipmentRentInfo.getId());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }

        item.setId(equipmentRentInfo.getId());
        item.setBackNum(equipmentRentInfo.getBackNum());
        item.setBackTime(equipmentRentInfo.getBackTime());
        item.setBrokenNum(equipmentRentInfo.getBrokenNum());
        item.setClassNum(equipmentRentInfo.getClassNum());
        item.setEndTime(equipmentRentInfo.getEndTime());
        item.setRentNum(equipmentRentInfo.getRentNum());
        item.setStartTime(equipmentRentInfo.getStartTime());
        item.setTarget(equipmentRentInfo.getTarget());
        item.setToolName(equipmentRentInfo.getToolName());
        item.setTotalMoney(equipmentRentInfo.getTotalMoney());
        item.setUsername(equipmentRentInfo.getUsername());

        equipmentRentInfoDao.recover(item);
    }

    @Override
    public void delete(Long id) {
        EquipmentRentInfo equipmentRentInfo =equipmentRentInfoDao.findRentInfoById(id);
        if(equipmentRentInfo == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }
        equipmentRentInfoDao.delete(id);
    }

    @Override
    public List<EquipmentRentInfo> select(EquipmentRentInfo equipmentRentInfo) {
        StringBuilder sql = new StringBuilder("SELECT * FROM equipment where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = equipmentRentInfo.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(equipmentRentInfo) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("date") || f.getName().equals("startTime") || f.getName().equals("endTime")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(equipmentRentInfo)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> " + sql.toString());
        return equipmentRentInfoDao.select(sql.toString());
    }
}
