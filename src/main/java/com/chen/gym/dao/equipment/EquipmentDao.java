package com.chen.gym.dao.equipment;

import com.chen.gym.bean.Equipment;
import com.chen.gym.bean.EquipmentKind;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentDao {

    @Select("select * from equipment")
    List<Equipment> findALl();

    @Select("SELECT * FROM `equipment` WHERE totalNum > 0")
    List<Equipment> findALlCanRent();

    @Select("SELECT * FROM `equipment` WHERE id=#{id}")
    Equipment findEquipmentByID(Long id);

    @Select("SELECT * FROM `equipment` WHERE equipmentName=#{equipmentName}")
    Equipment findEquipmentByEquipmentName(String equipmentName);

    @Insert("insert into equipment(equipmentName,allNum , damageNum, rentNum, totalNum, rendStandard , editTime,sno) " +
            "values(#{equipmentName} ,#{allNum}, " +
            "#{damageNum} ,#{rentNum} , #{totalNum} , " +
            "#{rendStandard} , #{editTime}, #{sno})")
    void addEquipment(Equipment equipment);

    @Delete("DELETE FROM `equipment` WHERE id=#{id}")
    void deleteEquipment(Long id);

    @Update("UPDATE `equipment` SET " +
            "equipmentName= #{equipmentName} , allNum=#{allNum} , damageNum=#{damageNum} ,rentNum=#{rentNum} , totalNum=#{totalNum} , " +
            "rendStandard=#{rendStandard} , editTime=#{editTime} , sno=#{sno} " +
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

    @Select("select * from equipmentKind")
    List<EquipmentKind> getAllKind();
}
