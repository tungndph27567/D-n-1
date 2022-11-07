package com.example.room;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "tb_class")
public class ClassUser {
    @PrimaryKey(autoGenerate = true)
    private int id_class;
    private String nameClass;
    private int id;

    public int getId_class() {
        return id_class;
    }

    public void setId_class(int id_class) {
        this.id_class = id_class;
    }

    public String getNameClass() {
        return nameClass;
    }

    public void setNameClass(String nameClass) {
        this.nameClass = nameClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ClassUser( String nameClass, int id) {

        this.nameClass = nameClass;
        this.id = id;
    }
}
