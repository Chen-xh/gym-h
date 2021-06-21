package com.chen.gym.service.equipment.impl;

import com.chen.gym.bean.Equipment;
import com.chen.gym.bean.EquipmentRentInfo;
import com.chen.gym.dao.equipment.EquipmentDao;
import com.chen.gym.dao.equipment.EquipmentRentInfoDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.equipment.EquipmentRentInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;

@Service
public class EquipmentRentInfoServiceImpl implements EquipmentRentInfoService {
    @Resource
    EquipmentRentInfoDao equipmentRentInfoDao;

    @Resource
    private EquipmentDao equipmentDao;


    private static final Logger PLOG = LoggerFactory.getLogger(EquipmentRentInfoServiceImpl.class);

    @Override
    public List<EquipmentRentInfo> findAll() {
        return equipmentRentInfoDao.findAll();
    }

    @Override
    public List<EquipmentRentInfo> findAllBeRecover() {
        return equipmentRentInfoDao.findAllBeRecover();
    }

    @Override
    public List<EquipmentRentInfo> findAllRecover() {
        return equipmentRentInfoDao.findAllRecover();
    }

    @Override
    public List<EquipmentRentInfo> findRentInfoByTarget(int target) {
        return equipmentRentInfoDao.findRentInfoByTarget(target);
    }

    @Override
    public List<EquipmentRentInfo> findRentInfoBySno(String sno) {
        return equipmentRentInfoDao.findRentInfoBySno(sno);
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
        // 初始化处理
        equipmentRentInfo.setRequireTime(new Date());
        equipmentRentInfo.setTarget(0);

        System.out.println("add EquipmentRentInfo test : " + equipmentRentInfo);
        equipmentRentInfoDao.add(equipmentRentInfo);
    }

    @Override
    public void passRequire(Long id) {
        EquipmentRentInfo item =equipmentRentInfoDao.findRentInfoById(id);
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }

        // 器材数量减少变化处理

        // 器材租用记录标签更新为通过
        item.setEndTime(new Date());
        item.setTarget(1);

        Equipment equipment = equipmentDao.findEquipmentByID(item.getEid());
        // 空器材异常
        if(equipment == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Equipment);
        }
        // 器材租出数更新
        equipment.setRentNum(equipment.getRentNum() + item.getRentNum());
        // 器材数目异常
        if(equipment.getDamageNum() + equipment.getRentNum() > equipment.getAllNum()){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.Equipment_Number_Error);
        }
        // 器材存余数更新
        equipment.setTotalNum(equipment.getAllNum() - equipment.getDamageNum() - equipment.getRentNum());
        // 器材数据库更新
        equipmentDao.updateEquipment(equipment);


        equipmentRentInfoDao.update(item);
    }

    @Override
    public void recover(EquipmentRentInfo equipmentRentInfo,int target) {
        EquipmentRentInfo item =equipmentRentInfoDao.findRentInfoById(equipmentRentInfo.getId());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }

        // 器材数量增加变化处理

        // 器材租用数据标签更新为通过
        item.setEndTime(new Date());
        item.setTarget(target);
        item.setBrokenNum(equipmentRentInfo.getBrokenNum());
        item.setBackNum(equipmentRentInfo.getBackNum());

        Equipment equipment = equipmentDao.findEquipmentByID(equipmentRentInfo.getEid());
        // 空器材异常
        if(equipment == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Equipment);
        }
        // 器材租出数更新
        equipment.setRentNum(equipment.getRentNum() - item.getRentNum());
        equipment.setDamageNum(equipment.getDamageNum() + item.getBrokenNum());
        // 器材数目异常
        if(equipment.getRentNum() < 0 || equipment.getDamageNum() + equipment.getRentNum() > equipment.getAllNum()){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.Equipment_Number_Error);
        }
        // 器材存余数更新
        equipment.setTotalNum(equipment.getAllNum() - equipment.getDamageNum() - equipment.getRentNum());
        // 器材数据库更新
        equipmentDao.updateEquipment(equipment);
        equipmentRentInfoDao.update(item);
    }

    @Override
    public void updateTarget(int target, Long id) {
        EquipmentRentInfo item =equipmentRentInfoDao.findRentInfoById(id);
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_EquipmentRentInfo);
        }
        item.setEditTime(new Date());

        equipmentRentInfoDao.updateTarget(target,id,item.getEditTime());
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
        StringBuilder sql = new StringBuilder("SELECT * FROM equipmentRentInfo where 1=1 ");
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
