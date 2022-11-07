package com.example.room;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ClassDAO {
    @Insert
    void insertClass(ClassUser classUser);
    @Query("SELECT *FROM tb_class")
    List<ClassUser> getListClass();

}
