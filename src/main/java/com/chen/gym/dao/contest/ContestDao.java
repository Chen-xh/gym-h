package com.chen.gym.dao.contest;

import com.chen.gym.bean.Contest;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface ContestDao {

    @Select("select * from contest")
    List<Contest> findAll();

    @Select("SELECT * FROM `contest` WHERE cid=#{id}")
    Contest findContestById(Long id);

    @Insert("insert into `contest` " +
            "name=#{name} ,organizers= #{organizers} , openTime=#{openTime}, date=#{date} ,object=#{object} , tele=#{tele} , " +
            "place=#{place} , equipment=#{equipment}, target=1")
    void add(Contest contest);

    @Delete("DELETE FROM `contest` WHERE cid=#{id}")
    void delete(Long id);

    @Update("UPDATE `contest` SET " +
            "name=#{name} ,organizers= #{organizers} , openTime=#{openTime} , date=#{date} ,object=#{object} , tele=#{tele} , " +
            "place=#{place} , equipment=#{equipment} , target={target}" +
            "WHERE cid=#{cid}")
    void update(Contest contest);

    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<Contest> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }
    @Update("UPDATE `contest` SET target=#{target} WHERE cid=#{cid}")
    void updateTarget(int target ,Long cid);

    @Update("UPDATE `contest` SET place=#{place}   WHERE cid=#{cid}")
    void updatePlace(Long place ,Long cid);
    @Update("UPDATE `contest` SET   equipment=#{equipment}  WHERE cid=#{cid}")
    void updateEquipment(Long equipment ,Long cid);
}
