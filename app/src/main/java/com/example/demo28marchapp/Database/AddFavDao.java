package com.example.demo28marchapp.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = DbUtils.FAV_TABLE)
public class AddFavDao {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = DbUtils.AddFav.KEYID)
    private int id;
    @ColumnInfo(name = DbUtils.AddFav.KEYURL)
    private String strUrl;
    @ColumnInfo(name = DbUtils.AddFav.KEYDATE)
    private long strDate;
    @ColumnInfo(name = DbUtils.AddFav.KEYCOUNT)
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStrUrl() {
        return strUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStrUrl(String strUrl) {
        this.strUrl = strUrl;
    }

    public long getStrDate() {
        return strDate;
    }

    public void setStrDate(long strDate) {
        this.strDate = strDate;
    }
    public AddFavDao() {

    }
    public AddFavDao(AddFavDao addFavDao) {
        id = addFavDao.id;
        strUrl = addFavDao.strUrl;
        strDate = addFavDao.strDate;
        count=addFavDao.count;
    }
}
