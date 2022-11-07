package com.example.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface userDAO {
    @Insert
    void insertUser(user user);

    @Query("SELECT *FROM tb_user")
    List<user> getListUser();

    @Query("SELECT *FROM tb_user where username =:username")
    List<user> checkUser(String username);

    @Update
    void updateUser(user user);
    @Delete
    void deleteUser(user user);
    @Query("DELETE FROM tb_user")
    void deleteAll();
    @Query("SELECT *FROM tb_user where username like '%' || :name || '%'")
    List<user> searchUser(String name);
    @Query("SELECT *FROM tb_user where id = :id ")
    List<user> getIdUser(String id);
}
