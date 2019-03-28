package com.example.demo28marchapp.Fragment;


import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.demo28marchapp.Adapter.OneFragmentAdapter;
import com.example.demo28marchapp.Database.AddFavDao;
import com.example.demo28marchapp.Database.DatabaseHelper;
import com.example.demo28marchapp.Interface.OneFragmentAdapterListener;
import com.example.demo28marchapp.R;
import com.example.demo28marchapp.databinding.FragmentTwoBinding;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class TwoFragment extends Fragment {

    private String TAG = "show";

    private Disposable disposable;
    private FragmentTwoBinding fragmentTwoBinding;
    private List<AddFavDao> addFavDaos=new ArrayList<>();
    private    OneFragmentAdapter oneFragmentAdapter;
    public TwoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        fragmentTwoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_two, container, false);
        View rootView = fragmentTwoBinding.getRoot();
        return rootView;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // observable
        fragmentTwoBinding.rvTwoFragment.setLayoutManager(new LinearLayoutManager(getActivity()));
        OneFragmentAdapterListener oneFragmentAdapterListener=new OneFragmentAdapterListener() {
            @Override
            public void onItemClick(Object o) {

                Toast.makeText(getActivity(), "item second click", Toast.LENGTH_SHORT).show();
            }
        };
         oneFragmentAdapter = new OneFragmentAdapter(getActivity(), addFavDaos,oneFragmentAdapterListener);
        fragmentTwoBinding.rvTwoFragment.setAdapter(oneFragmentAdapter);

        Observable<List<AddFavDao>> animalsObservable = getAnimalsObservable();

        // observer
        Observer<List<AddFavDao>> animalsObserver = getAnimalsObserver();

        // observer subscribing to observable
        animalsObservable
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(animalsObserver);

    }

    private Observer<List<AddFavDao>> getAnimalsObserver() {
        return new Observer<List<AddFavDao>>() {
            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
                Log.d(TAG, "onSubscribe");
            }

            @Override
            public void onNext(List<AddFavDao> updatedAddFavDaos) {
                addFavDaos=updatedAddFavDaos;
                Log.d(TAG, "Name: " + addFavDaos);

                oneFragmentAdapter.notifyDataSetChanged();



            }

            @Override
            public void onError(Throwable e) {
                Log.e(TAG, "onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "All items are emitted!");
            }
        };
    }

    private Observable<List<AddFavDao>> getAnimalsObservable() {
        return Observable.fromArray(DatabaseHelper.getDatabase(getActivity()).interfaceDao().loadFav());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        disposable.dispose();
    }
}
