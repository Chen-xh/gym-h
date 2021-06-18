package com.chen.gym.dao.equipment;

import com.chen.gym.bean.EquipmentRentInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface EquipmentRentInfoDao {
    /**
     * 查询所有租用记录
     */
    @Select("select * from equipmentRentInfo")
    List<EquipmentRentInfo> findAll();

    /**
     * 查询所有租出中记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE target=1")
    List<EquipmentRentInfo> findAllRenting();

    /**
     * 查询所有回收记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE target=2")
    List<EquipmentRentInfo> findAllRecovering();

    /**
     * 根据ID查询租用记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE id=#{id}")
    EquipmentRentInfo findRentInfoById(Long id);

    /**
     * 添加租用记录
     */
    @Insert("insert into equipmentRentInfo(toolName,rentNum , startTime, endTime, classNum,username , target,backNum, " +
            "backTime,brokenNum,totalMoney) " +
            "values(#{toolName} ,#{rentNum}, " +
            "#{startTime} ,#{endTime} , #{classNum} , " +
            "#{username} ,#{target} , #{backNum} , " +
            "#{backTime} , #{brokenNum}, #{totalMoney})")
    void add(EquipmentRentInfo equipmentRentInfo);

    /**
     * 回收器材并更新租用记录状态
     */
    @Update("UPDATE `equipmentRentInfo` SET " +
            "toolName= #{toolName} , rentNum=#{rentNum} , " +
            "startTime=#{startTime} ,endTime=#{endTime} , " +
            "classNum=#{classNum} ,username=#{username} , " +
            "target=#{target} ,backNum=#{backNum} , " +
            "backTime=#{backTime} ,brokenNum=#{brokenNum} , " +
            "totalMoney=#{totalMoney} " +
            "WHERE id=#{id}")
    void recover(EquipmentRentInfo equipmentRentInfo);

    /**
     * 删除器材租用记录
     */
    @Delete("DELETE FROM `equipmentRentInfo` WHERE id=#{id}")
    void delete(Long id);

    /**
     * 多条件查询
     */

    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<EquipmentRentInfo> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
}
