package com.chen.gym.service.field.impl;

import com.chen.gym.bean.FieldUseInfo;
import com.chen.gym.dao.field.FieldDao;
import com.chen.gym.dao.field.FieldUseInfoDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.field.FieldUseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

public class FieldUseInfoServiceImpl implements FieldUseInfoService {
    @Resource
    private FieldUseInfoDao fieldUseInfoDao;

    private static final Logger PLOG = LoggerFactory.getLogger(FieldUseInfoServiceImpl.class);

    @Override
    public List<FieldUseInfo> findAll() {
        return fieldUseInfoDao.findAll();
    }

    @Override
    public List<FieldUseInfo> findAllRenting() {
        return fieldUseInfoDao.findAllRenting();
    }

    @Override
    public List<FieldUseInfo> findAllInClass() {
        return fieldUseInfoDao.findAllInClass();
    }

    @Override
    public List<FieldUseInfo> findAllRecover() {
        return fieldUseInfoDao.findAllRecover();
    }

    @Override
    public FieldUseInfo findFieldUseInfoByID(Long id) {
        FieldUseInfo fieldUseInfo = fieldUseInfoDao.findFieldUseInfoByID(id);
        if(fieldUseInfo == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_FieldUseInfo);
        }
        return fieldUseInfo;
    }

    @Override
    public void add(FieldUseInfo fieldUseInfo) {
        fieldUseInfoDao.add(fieldUseInfo);
    }

    @Override
    public void update(FieldUseInfo fieldUseInfo) {
        FieldUseInfo item = fieldUseInfoDao.findFieldUseInfoByID(fieldUseInfo.getId());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_FieldUseInfo);
        }

        item.setBorrower(fieldUseInfo.getBorrower());
        item.setBorrowTime(fieldUseInfo.getBorrowTime());
        item.setCost(fieldUseInfo.getCost());
        item.setEndTime(fieldUseInfo.getEndTime());
        item.setPurpose(fieldUseInfo.getPurpose());
        item.setSiteName(fieldUseInfo.getSiteName());
        item.setStartTime(fieldUseInfo.getStartTime());
        item.setTarget(fieldUseInfo.getTarget());

        fieldUseInfoDao.update(item);
    }

    @Override
    public void delete(Long id) {
        FieldUseInfo item = fieldUseInfoDao.findFieldUseInfoByID(id);
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_FieldUseInfo);
        }
        fieldUseInfoDao.delete(id);
    }

    @Override
    public void recover(Long id) {
        FieldUseInfo item = fieldUseInfoDao.findFieldUseInfoByID(id);
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_FieldUseInfo);
        }

        fieldUseInfoDao.recover(id);
    }

    @Override
    public List<FieldUseInfo> select(FieldUseInfo fieldUseInfo) {
        StringBuilder sql = new StringBuilder("SELECT * FROM fieldUseInfo where 1=1 ");
        try {
            //通过反射遍历对象中的属性
            Class cls = fieldUseInfo.getClass();
            java.lang.reflect.Field[] fields = cls.getDeclaredFields();
            for (java.lang.reflect.Field f : fields) {
                //设置属性可访问，破坏私有
                f.setAccessible(true);
                if (f.get(fieldUseInfo) != null) {
//                    System.out.println("属性名:" + f.getName() + " 属性值:" + f.get(contest));
                    if (f.getName().equals("date") || f.getName().equals("startTime") || f.getName().equals("endTime")) {
//                       时间要具体处理。。
                    } else {
                        sql.append(" and ").append(f.getName()).append(" like '%").append(f.get(fieldUseInfo)).append("%'");
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        PLOG.info("Select >> " + sql.toString());
        return fieldUseInfoDao.select(sql.toString());
    }
}
