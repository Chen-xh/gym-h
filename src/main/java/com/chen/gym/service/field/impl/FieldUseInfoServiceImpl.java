package com.chen.gym.service.field.impl;

import com.chen.gym.bean.Field;
import com.chen.gym.bean.FieldUseInfo;
import com.chen.gym.dao.field.FieldDao;
import com.chen.gym.dao.field.FieldUseInfoDao;
import com.chen.gym.exception.CustomizeRuntimeException;
import com.chen.gym.exception.MyCustomizeErrorCode;
import com.chen.gym.service.field.FieldUseInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class FieldUseInfoServiceImpl implements FieldUseInfoService {
    @Resource
    private FieldUseInfoDao fieldUseInfoDao;
    @Resource
    private FieldDao fieldDao;

    private static final Logger PLOG = LoggerFactory.getLogger(FieldUseInfoServiceImpl.class);

    @Override
    public List<FieldUseInfo> findAll() {
        return fieldUseInfoDao.findAll();
    }

    @Override
    public List<FieldUseInfo> findAllBePass() {
        return fieldUseInfoDao.findAllBePass();
    }

    @Override
    public List<FieldUseInfo> findAllRenting() {
        return fieldUseInfoDao.findAllRenting();
    }


    @Override
    public List<FieldUseInfo> findAllRecover() {
        return fieldUseInfoDao.findAllRecover();
    }

    @Override
    public List<FieldUseInfo> findFieldUseInfoBySno(String sno) {
        return fieldUseInfoDao.findFieldUseInfoBySno(sno);
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
    public List<FieldUseInfo> findFieldUseInfoByTarget(int target) {
        return fieldUseInfoDao.findFieldUseInfoByTarget(target);
    }

    @Override
    public void add(FieldUseInfo fieldUseInfo) {
        fieldUseInfo.setEditTime(new Date());
        fieldUseInfo.setTarget(0);
        Field item = fieldDao.findFieldByID(fieldUseInfo.getFid());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FIELD_EXIST);
        }
        fieldUseInfo.setSiteName(item.getSiteName());
        fieldUseInfoDao.add(fieldUseInfo);
    }

    @Override
    public void update(FieldUseInfo fieldUseInfo) {
        FieldUseInfo item = fieldUseInfoDao.findFieldUseInfoByID(fieldUseInfo.getId());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.NOT_FOND_FieldUseInfo);
        }

        item.setSiteName(fieldUseInfo.getSiteName());
        item.setFid(fieldUseInfo.getFid());
        item.setSno(fieldUseInfo.getSno());
        item.setWhyToUse(fieldUseInfo.getWhyToUse());
        item.setTarget(fieldUseInfo.getTarget());
        item.setBorrowTime(fieldUseInfo.getBorrowTime());
        item.setStartTime(fieldUseInfo.getStartTime());
        item.setEndTime(fieldUseInfo.getEndTime());
        item.setEditTime(new Date());
        item.setTotalMoney(fieldUseInfo.getTotalMoney());

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
    public void updateTarget(Long id, int target) {
        FieldUseInfo fieldUseInfo = new FieldUseInfo();
        fieldUseInfo.setEditTime(new Date());
        fieldUseInfoDao.updateTarget(id, target,fieldUseInfo.getEditTime());
    }

    @Override
    public void setInClass(FieldUseInfo fieldUseInfo) {
        fieldUseInfo.setEditTime(new Date());
        fieldUseInfo.setTarget(3);
        fieldUseInfo.setTotalMoney(0);
        fieldUseInfo.setWhyToUse("上课");
        Field item = fieldDao.findFieldByID(fieldUseInfo.getFid());
        if(item == null){
            throw new CustomizeRuntimeException(MyCustomizeErrorCode.FIELD_EXIST);
        }
        fieldUseInfo.setSiteName(item.getSiteName());

        fieldUseInfoDao.add(fieldUseInfo);
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
