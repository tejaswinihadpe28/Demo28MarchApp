package com.example.demo28marchapp.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import com.example.demo28marchapp.Database.AddFavDao;
import com.example.demo28marchapp.Database.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;


public class FavouritesViewModel extends AndroidViewModel {
    private DatabaseHelper databaseHelper;
    private MutableLiveData<List<AddFavDao>> mFavs;

    public FavouritesViewModel(@NonNull Application application) {
        super(application);
        databaseHelper = DatabaseHelper.getDatabase(application);
    }

    public void addFav(String url, long date) {
        AddFavDao addFavDao = new AddFavDao();
        addFavDao.setStrUrl(url);
        addFavDao.setStrDate(date);
        databaseHelper.interfaceDao().addFav(addFavDao);

        List<AddFavDao> favourites = mFavs.getValue();

        ArrayList<AddFavDao> clonedFavs;
        if (favourites == null) {
            clonedFavs = new ArrayList<>();
        } else {
            clonedFavs= (ArrayList<AddFavDao>) favourites;
            /*clonedFavs = new ArrayList<>(favourites.size());
            for (int i = 0; i < favourites.size(); i++) {
                clonedFavs.add(new AddFavDao(favourites.get(i)));
            }*/
        }


        clonedFavs.add(addFavDao);
        mFavs.setValue(clonedFavs);

    }

    public MutableLiveData<List<AddFavDao>> getFavs() {
        //if (listMutableLiveData == null) {
        mFavs = new MutableLiveData<>();

        List<AddFavDao>  newFavs = databaseHelper.interfaceDao().loadFav();
        mFavs.setValue(newFavs);


        // }

        return mFavs;
    }

//    public void updateCount(int id,int count) {
//       databaseHelper.interfaceDao().updateCount(id,count);
//
//        List<AddFavDao> favs = mFavs.getValue();
//        ArrayList<AddFavDao> clonedFavs = new ArrayList<>(favs.size());
//        for (int i = 0; i < favs.size(); i++) {
//            clonedFavs.add(new AddFavDao(favs.get(i)));
//        }
//
//        int index = -1;
//        for (int i = 0; i < clonedFavs.size(); i++) {
//            AddFavDao favourites = clonedFavs.get(i);
//            if (favourites.getId() == id) {
//                index = i;
//            }
//        }
//        if (index != -1) {
//            clonedFavs.remove(index);
//        }
//        mFavs.setValue(clonedFavs);
//    }

}