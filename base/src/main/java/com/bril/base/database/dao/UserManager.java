package com.bril.base.database.dao;

import android.content.Context;


import com.bril.base.database.entity.User;
import com.bril.base.database.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SY on 2017/12/25.
 */

public class UserManager extends BaseDao<User> {
    public UserManager(Context context) {
        super(context);
    }
/***************************数据库查询*************************/

    /**
     * 通过ID查询对象
     * @param id
     * @return
     */
    private User loadById(long id){

        return daoSession.getUserDao().load(id);
    }

    /**
     * 获取某个对象的主键ID
     * @param student
     * @return
     */
    private long getID(User student){

        return daoSession.getUserDao().getKey(student);
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<User> getStudentByName(String key){
        QueryBuilder queryBuilder =  daoSession.getUserDao().queryBuilder();
        queryBuilder.where(UserDao.Properties.Name.eq(key));
        int size = queryBuilder.list().size();
        if (size > 0){
            return queryBuilder.list();
        }else{
            return null;
        }
    }

    /**
     * 通过名字获取Customer对象
     * @return
     */
    private List<Long> getIdByName(String key){
        List<User> students = getStudentByName(key);
        List<Long> ids = new ArrayList<Long>();
        int size = students.size();
        if (size > 0){
            for (int i = 0;i < size;i++){
                ids.add(students.get(i).getId());
            }
            return ids;
        }else{
            return null;
        }
    }

    /***************************数据库删除*************************/

    /**
     * 根据ID进行数据库的删除操作
     * @param id
     */
    private void deleteById(long id){

        daoSession.getUserDao().deleteByKey(id);
    }


    /**
     * 根据ID同步删除数据库操作
     * @param ids
     */
    private void deleteByIds(List<Long> ids){

        daoSession.getUserDao().deleteByKeyInTx(ids);
    }

    /***********************************
     * 在次添加一些Student特有的数据库操作语句
     * ************************************/

}
