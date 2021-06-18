package com.chen.gym.dao.field;

import com.chen.gym.bean.FieldUseInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface FieldUseInfoDao {
    @Select("select * from FieldUseInfoDao")
    List<FieldUseInfo> findAll();

    @Select("SELECT * FROM `FieldUseInfoDao` WHERE target=1")
    List<FieldUseInfo> findAllRenting();

    @Select("SELECT * FROM `FieldUseInfoDao` WHERE target=2")
    List<FieldUseInfo> findAllInClass();

    @Select("SELECT * FROM `FieldUseInfoDao` WHERE target=3")
    List<FieldUseInfo> findAllRecover();

    @Select("SELECT * FROM `FieldUseInfoDao` WHERE id=#{id}")
    FieldUseInfo findFieldUseInfoByID(Long id);

    @Insert("insert into FieldUseInfoDao(siteName, Cost , borrower, purpose, target," +
            "borrowTime,startTime , endTime) " +
            "values(#{siteName} ,#{Cost}, " +
            "#{borrower} ,#{purpose} , " +
            "#{target} ,#{borrowTime} , " +
            "#{startTime} , #{endTime})")
    void add(FieldUseInfo fieldUseInfo);

    @Update("UPDATE `FieldUseInfoDao` SET " +
            "siteName= #{siteName} , Cost=#{Cost} , borrower=#{borrower} , " +
            "purpose= #{purpose} , target=#{target} , borrowTime=#{borrowTime} , " +
            "startTime= #{startTime} , endTime=#{endTime} , " +
            "WHERE id=#{id}")
    void update(FieldUseInfo fieldUseInfo);

    @Delete("DELETE FROM `FieldUseInfoDao` WHERE id=#{id}")
    void delete(Long id);

    @Update("UPDATE `FieldUseInfoDao` SET " +
            "target=3 " +
            "WHERE id=#{id}")
    void recover(Long id);

    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<FieldUseInfo> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
}
