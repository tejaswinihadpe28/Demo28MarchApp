package com.example.demo28marchapp.Fragment;


import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo28marchapp.Adapter.OneFragmentAdapter;
import com.example.demo28marchapp.Database.AddFavDao;
import com.example.demo28marchapp.Interface.OneFragmentAdapterListener;
import com.example.demo28marchapp.R;
import com.example.demo28marchapp.ViewModel.FavouritesViewModel;
import com.example.demo28marchapp.databinding.FragmentOneBinding;

import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class OneFragment extends Fragment {
    private FragmentOneBinding fragmentOneBinding;
    private FavouritesViewModel favouritesViewModel;
    private OneFragmentAdapter oneFragmentAdapter;
    private List<AddFavDao> mFav;

    public OneFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentOneBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false);
        View rootView = fragmentOneBinding.getRoot();

        favouritesViewModel = new FavouritesViewModel(getActivity().getApplication());
        fragmentOneBinding.rvOneFragment.setLayoutManager(new LinearLayoutManager(getActivity()));

        final Observer<List<AddFavDao>> favsObserver = new Observer<List<AddFavDao>>() {
            @Override
            public void onChanged(@Nullable final List<AddFavDao> UpadtedAddFavDaosList) {
                if (mFav == null) {
                    mFav = UpadtedAddFavDaosList;
                    OneFragmentAdapterListener oneFragmentAdapterListener=new OneFragmentAdapterListener() {
                        @Override
                        public void onItemClick(Object obj) {
                            AddFavDao addFavDao=(AddFavDao)obj;
//                            favouritesViewModel.updateCount(addFavDao.getId(),addFavDao.getCount());
                            Toast.makeText(getActivity(), "item click", Toast.LENGTH_SHORT).show();
                        }
                    };
                    oneFragmentAdapter = new OneFragmentAdapter(getActivity(), mFav,oneFragmentAdapterListener);
                    fragmentOneBinding.rvOneFragment.setAdapter(oneFragmentAdapter);
                } else {
                    DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {

                        @Override
                        public int getOldListSize() {
                            return mFav.size();
                        }

                        @Override
                        public int getNewListSize() {
                            return UpadtedAddFavDaosList.size();
                        }

                        @Override
                        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                            return mFav.get(oldItemPosition).getId() ==
                                    UpadtedAddFavDaosList.get(newItemPosition).getId();
                        }

                        @Override
                        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                            AddFavDao oldFav = mFav.get(oldItemPosition);
                            AddFavDao newFav = UpadtedAddFavDaosList.get(newItemPosition);
                            return oldFav.equals(newFav);
                        }
                    });

                    mFav = UpadtedAddFavDaosList;
                    Log.e("show","mFav=="+mFav);
                    result.dispatchUpdatesTo(oneFragmentAdapter);
                }
            }


        };

        fragmentOneBinding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText editText = new EditText(getActivity());
                AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                        .setTitle("New favourite")
                        .setMessage("Add a url link below")
                        .setView(editText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url = String.valueOf(editText.getText());
                                long date = (new Date()).getTime();


                                favouritesViewModel.addFav(url, date);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                alertDialog.show();
            }
        });

        favouritesViewModel.getFavs().observe(this, favsObserver);

        return rootView;
    }

}