package com.chen.gym.dao.equipment;

import com.chen.gym.bean.EquipmentRentInfo;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface EquipmentRentInfoDao {
    /**
     * 查询所有租用记录
     */
    @Select("select * from equipmentRentInfo")
    List<EquipmentRentInfo> findAll();

    /**
     * 查询所有待回收
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE target=1 or target=3")
    List<EquipmentRentInfo> findAllBeRecover();

    /**
     * 查询所有租用记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE target=4 or target=6")
    List<EquipmentRentInfo> findAllRecover();

    /**
     * 根据租用状态查询
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE target=#{target}")
    List<EquipmentRentInfo> findRentInfoByTarget(int target);

    /**
     * 根据学号或教工号查询租用记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE sno=#{sno}")
    List<EquipmentRentInfo> findRentInfoBySno(String sno);

    /**
     * 根据ID查询租用记录
     */
    @Select("SELECT * FROM `equipmentRentInfo` WHERE id=#{id}")
    EquipmentRentInfo findRentInfoById(Long id);

    /**
     * 添加租用记录
     */
    @Insert("insert into equipmentRentInfo(rentNum,startTime , endTime, requireTime, classNum,sno , target,backNum, " +
            "editTime,brokenNum,totalMoney,eid) " +
            "values(#{rentNum} ,#{startTime}, #{endTime}," +
            "#{requireTime} ,#{classNum} , #{sno} , " +
            "#{target} ,#{backNum} , #{editTime} , " +
            "#{brokenNum} , #{totalMoney}, #{eid})")
    void add(EquipmentRentInfo equipmentRentInfo);

    /**
     * 更新租用记录
     */
    @Update("UPDATE `equipmentRentInfo` SET " +
            "rentNum= #{rentNum} , startTime=#{startTime} , " +
            "endTime=#{endTime} ,requireTime=#{requireTime} , " +
            "classNum=#{classNum} ,sno=#{sno} , " +
            "target=#{target} ,backNum=#{backNum} , " +
            "editTime=#{editTime} ,brokenNum=#{brokenNum} , " +
            "totalMoney=#{totalMoney}, eid=#{eid} " +
            "WHERE id=#{id}")
    void update(EquipmentRentInfo equipmentRentInfo);

    /**
     * 更新标签
     */
    @Update("UPDATE `equipmentRentInfo` SET " +
            "target=#{target}," +
            "editTime=#{editTime}" +
            "WHERE id=#{id}")
    void updateTarget(int target, Long id, Date editTime);

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
