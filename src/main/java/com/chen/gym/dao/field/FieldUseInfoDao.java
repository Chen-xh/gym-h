package com.chen.gym.dao.field;

import com.chen.gym.bean.FieldUseInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface FieldUseInfoDao {
    @Select("select * from fieldUseInfo")
    List<FieldUseInfo> findAll();

    @Select("SELECT * FROM `fieldUseInfo` WHERE target=0")
    List<FieldUseInfo> findAllBePass();

    @Select("SELECT * FROM `fieldUseInfo` WHERE target=1")
    List<FieldUseInfo> findAllRenting();

    @Select("SELECT * FROM `fieldUseInfo` WHERE target=5 or target = 6")
    List<FieldUseInfo> findAllRecover();

    @Select("SELECT * FROM `fieldUseInfo` WHERE sno=#{sno}")
    List<FieldUseInfo> findFieldUseInfoBySno(String sno);

    @Select("SELECT * FROM `fieldUseInfo` WHERE id=#{id}")
    FieldUseInfo findFieldUseInfoByID(Long id);

    @Select("SELECT * FROM `fieldUseInfo` WHERE target=#{target}")
    List<FieldUseInfo> findFieldUseInfoByTarget(int target);

    @Insert("insert into fieldUseInfo(siteName, fid, sno , whyToUse, target, borrowTime," +
            "startTime,endTime ,editTime, totalMoney) " +
            "values(#{siteName} ,#{fid} ,#{sno}, " +
            "#{whyToUse} ,#{target} , " +
            "#{borrowTime} ,#{startTime} , " +
            "#{endTime} ,#{editTime} , #{totalMoney})")
    void add(FieldUseInfo fieldUseInfo);

    @Update("UPDATE `fieldUseInfo` SET " +
            "fid= #{fid} , sno=#{sno} , whyToUse=#{whyToUse} , " +
            "target= #{target} , borrowTime=#{borrowTime} , startTime=#{startTime} , " +
            "endTime= #{endTime} ,editTime= #{editTime} , totalMoney=#{totalMoney} , " +
            "WHERE id=#{id}")
    void update(FieldUseInfo fieldUseInfo);

    @Delete("DELETE FROM `fieldUseInfo` WHERE id=#{id}")
    void delete(Long id);

    @Update("UPDATE `fieldUseInfo` SET " +
            "target=#{target}, " +
            "editTime=#{editTime}, " +
            "WHERE id=#{id}")
    void updateTarget(Long id, int target, Date editTime);

    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<FieldUseInfo> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
}
