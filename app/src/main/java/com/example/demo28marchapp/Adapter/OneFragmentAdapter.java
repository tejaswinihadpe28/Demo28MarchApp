package com.example.demo28marchapp.Adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.demo28marchapp.Database.AddFavDao;
import com.example.demo28marchapp.Interface.OneFragmentAdapterListener;
import com.example.demo28marchapp.R;
import com.example.demo28marchapp.databinding.RvOneBinding;

import java.util.List;

public class OneFragmentAdapter extends RecyclerView.Adapter<OneFragmentAdapter.OneFragmentViewHolder> {
    private Context context;
    private List<AddFavDao> addFavDaosList;
    private OneFragmentAdapterListener oneFragmentAdapterListener;

    private LayoutInflater layoutInflater;
    public OneFragmentAdapter(Context context, List<AddFavDao> addFavDaosList, OneFragmentAdapterListener oneFragmentAdapterListener) {
        this.context=context;
        this.addFavDaosList=addFavDaosList;
        this.oneFragmentAdapterListener=oneFragmentAdapterListener;

    }

    @NonNull
    @Override
    public OneFragmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RvOneBinding rvOneBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.rv_one, parent, false);
        return new OneFragmentViewHolder(rvOneBinding);



    }

    @Override
    public void onBindViewHolder(@NonNull OneFragmentViewHolder holder, int pos) {
        AddFavDao addFavDao=new AddFavDao();
        addFavDao=addFavDaosList.get(pos);
        holder.rvOneBinding.tvDate.setText(String.valueOf(addFavDao.getStrDate()));
        holder.rvOneBinding.tvUrl.setText(addFavDao.getStrUrl());
        final AddFavDao finalAddFavDao = addFavDao;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oneFragmentAdapterListener.onItemClick(finalAddFavDao);
            }
        });

    }

    @Override
    public int getItemCount() {
        return addFavDaosList.size();
    }

    public class OneFragmentViewHolder extends RecyclerView.ViewHolder {
        private  RvOneBinding rvOneBinding;

        public OneFragmentViewHolder(final RvOneBinding rvOneBinding) {
            super(rvOneBinding.getRoot());
            this.rvOneBinding = rvOneBinding;
        }
    }
}
