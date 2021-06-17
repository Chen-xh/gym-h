package com.chen.gym.service.equipment.impl;

import com.chen.gym.bean.Equipment;
import com.chen.gym.dao.equipment.EquipmentDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.equipment.EquipmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Resource
    private EquipmentDao equipmentDao;

    private static final Logger PLOG = LoggerFactory.getLogger(EquipmentServiceImpl.class);

    @Override
    public List<Equipment> findAll() {
        return equipmentDao.findALl();
    }

    @Override
    public List<Equipment> select(Equipment equipment) {
        StringBuilder sql = new StringBuilder("SELECT * FROM contest where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = equipment.getClass();
            Field[] fields = cls.getDeclaredFields();
            for (Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(equipment) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("date") || f.getName().equals("editTime")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(equipment)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> " + sql.toString());
        return equipmentDao.select(sql.toString());
    }

    @Override
    public Equipment findEquipmentById(Long id) {
        Equipment equipment = equipmentDao.findEquipmentByID(id);
        if(equipment == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Equipment);
        }
        return equipment;
    }

    @Override
    public void add(Equipment equipment) {
        equipment.setEditTime(new Date());
        equipmentDao.addEquipment(equipment);
    }

    @Override
    public void delete(Long id) {
        Equipment equipment = equipmentDao.findEquipmentByID(id);
        if(equipment == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Equipment);
        }
        equipmentDao.deleteEquipment(id);
    }

    @Override
    public void update(Equipment equipment) {
        Equipment item = equipmentDao.findEquipmentByID(equipment.getId());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Equipment);
        }

        item.setEditTime(new Date());
        item.setAllNum(equipment.getAllNum());
        item.setDamageNum(equipment.getDamageNum());
        item.setRendStandard(equipment.getRendStandard());
        item.setRentNum(equipment.getRentNum());
        item.setToolKind(equipment.getToolKind());
        item.setToolName(equipment.getToolName());
        item.setTotalNum(equipment.getTotalNum());
        item.setUserId(equipment.getUserId());

        equipmentDao.updateEquipment(item);
    }
}
