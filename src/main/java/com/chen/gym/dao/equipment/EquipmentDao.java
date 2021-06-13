package com.chen.gym.dao.equipment;

import com.chen.gym.bean.Equipment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentDao {

    @Select("select * from equipment")
    List<Equipment> findALl();

    @Select("SELECT * FROM `equipment` WHERE id=#{id}")
    Equipment findEquipmentByID(Long id);

    @Insert("insert into equipment(toolName,toolKind,allNum , damageNum, rentNum, totalNum,rendStandard , editTime,userId) " +
            "values(#{toolName} ,#{toolKind} ,#{allNum}, " +
            "#{damageNum} ,#{rentNum} , #{totalNum} , " +
            "#{rendStandard} , #{editTime}, #{userId})")
    void addEquipment(Equipment equipment);

    @Delete("DELETE FROM `equipment` WHERE id=#{id}")
    void deleteEquipment(Long id);

    @Update("UPDATE `equipment` SET " +
            "toolName=#{toolName} ,toolKind= #{toolKind} , allNum=#{allNum} , damageNum=#{damageNum} ,rentNum=#{rentNum} , totalNum=#{totalNum} , " +
            "rendStandard=#{rendStandard} , editTime=#{editTime} , userId=#{userId} " +
            "WHERE id=#{id}")
    void updateEquipment(Equipment equipment);


    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<Equipment> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
}
