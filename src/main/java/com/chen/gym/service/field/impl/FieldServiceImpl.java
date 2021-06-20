package com.chen.gym.service.field.impl;

import com.chen.gym.bean.Field;
import com.chen.gym.dao.field.FieldDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.field.FieldService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FieldServiceImpl implements FieldService {
    @Resource
    private FieldDao fieldDao;

    private static final Logger PLOG = LoggerFactory.getLogger(FieldServiceImpl.class);

    @Override
    public List<Field> findAll() {
        return fieldDao.findAll();
    }

    @Override
    public Field findFieldByID(Long id) {
        Field field = fieldDao.findFieldByID(id);
        if(field == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Field);
        }
        return field;
    }

    @Override
    public void addField(Field field) {
        Field item = fieldDao.findFieldByName(field.getSiteName());
        if(item != null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FIELD_EXIST);
        }
        field.setEditTime(new Date());
        fieldDao.addField(field);
    }

    @Override
    public void deleteField(Long id) {
        Field field = fieldDao.findFieldByID(id);
        if(field == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Field);
        }
        fieldDao.deleteField(id);
    }

    @Override
    public void updateField(Field field) {
        Field item = fieldDao.findFieldByID(field.getID());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_Field);
        }

        item.setEditTime(new Date());
        item.setID(field.getID());
        item.setPlace(field.getPlace());
        item.setSiteCost(field.getSiteCost());
        item.setSiteName(field.getSiteName());
        item.setSno(field.getSno());

        fieldDao.updateField(item);
    }

    @Override
    public List<Field> select(Field field) {
        StringBuilder sql = new StringBuilder("SELECT * FROM field where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = field.getClass();
            java.lang.reflect.Field[] fields = cls.getDeclaredFields();
            for (java.lang.reflect.Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(field) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("date") || f.getName().equals("editTime")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(field)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> " + sql.toString());
        return fieldDao.select(sql.toString());
    }
}
