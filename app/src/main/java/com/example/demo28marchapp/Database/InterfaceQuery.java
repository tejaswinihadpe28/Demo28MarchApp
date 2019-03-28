package com.example.demo28marchapp.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface InterfaceQuery {
    @Insert
    public void addFav(AddFavDao addFavDao);

    @Query("SELECT * FROM " + DbUtils.FAV_TABLE)
    public List<AddFavDao> loadFav();

//
//    @Query("DELETE FROM " + DbUtils.FAV_TABLE + " WHERE " + DbUtils.AddFav.KEYID + "=:id")
//    public int deleteVisitUniqueData(int id,int count);

    @Query("Update " + DbUtils.FAV_TABLE + " SET " + DbUtils.AddFav.KEYCOUNT + "=:count" + " WHERE " + DbUtils.AddFav.KEYID + "=:id")
    public void updateCount(int id,int count);

}
