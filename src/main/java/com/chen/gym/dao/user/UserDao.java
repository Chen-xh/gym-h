package com.chen.gym.dao.user;

import com.chen.gym.bean.Contest;
import com.chen.gym.bean.User;
import com.chen.gym.dao.contest.ContestDao;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author: CHEN
 * @date: 2020-12-09 11:10
 **/
@Mapper
public interface UserDao {

    @Select("select * from user_role u left outer join user r on(u.uid=r.userId)")
    List<User> findAllUser();

    @Select("select * from (select * from user_role where rid != 2) u left outer join user r on(u.uid=r.userId)")
    List<User> findAllManager();

    @Select("SELECT * from user_role u left outer join user r on(u.uid=r.userId)  WHERE userId=#{id}")
    User findUserById(Long id);

    @Select("SELECT * FROM `user` WHERE sno=#{sno}")
    User findUserByUsername(String sno);

    @Select("SELECT count(*) FROM `user` WHERE sno=#{sno}")
    Integer getSnoExist(String sno);

    @Select("SELECT target from user_role u left outer join role r on(u.rid=r.rid) where u.uid=#{uid}")
    List<String> getUserRole(Long uid);


    @Insert("insert into user(sno,password,name,tele,mail,address,idCard,birthday) " +
            "values(#{sno} , #{password} , " +
            "#{name}, #{tele}  , " +
            "#{mail} , #{address},#{idCard},#{birthday})")
    @Options(useGeneratedKeys=true,keyProperty="userId")
    void add(User user);

    @Delete("DELETE FROM `user` WHERE userId=#{id}")
    void delete(Long id);

    @Update("UPDATE `user` SET " +
            "sno=#{sno} ,password= #{password} , name=#{name} , tele=#{tele} " +
            ",mail=#{mail} , address=#{address} , " +
            "idCard=#{idCard} , birthday=#{birthday}" +
            "WHERE userId=#{userId}")
    void update(User user);

    //使用MyProvider类的select方法来动态生成sql
    @SelectProvider(type = MyProvider.class, method = "select")
    List<User> select(String sql);

    class MyProvider {
        public String select(String sql) {
            return sql;
        }
    }


}
