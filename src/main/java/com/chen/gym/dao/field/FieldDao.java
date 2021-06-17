package com.chen.gym.dao.field;

import com.chen.gym.bean.Field;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface FieldDao {
    @Select("select * from field")
    List<Field> findAll();

    @Select("SELECT * FROM `field` WHERE siteSituation=2")
    List<Field> findAllInClass();

    @Select("SELECT * FROM `field` WHERE siteSituation=3")
    List<Field> findAllRenting();

    @Select("SELECT * FROM `field` WHERE siteSituation=1")
    List<Field> findAllIdle();

    @Select("SELECT * FROM `field` WHERE id=#{id}")
    Field findFieldByID(Long id);

    @Insert("insert into field(siteName,siteCost , place, userId, editTime,siteSituation) " +
            "values(#{siteName} ,#{siteCost}, " +
            "#{place} ,#{userId} , " +
            "#{editTime} , 1)")
    void addField(Field field);

    @Delete("DELETE FROM `field` WHERE id=#{id}")
    void deleteField(Long id);

    @Update("UPDATE `field` SET " +
            "siteName= #{siteName} , siteCost=#{siteCost} , place=#{place} ," +
            "userId=#{userId} , editTime=#{editTime} , siteSituation=#{siteSituation} " +
            "WHERE id=#{id}")
    void updateField(Field field);

    // 使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<Field> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
}
