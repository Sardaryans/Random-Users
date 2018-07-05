package com.example.suren.randomusers.adapter;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.suren.randomusers.R;
import com.example.suren.randomusers.model.User;
import com.example.suren.randomusers.modelview.UserModelView;

import java.util.List;

public class PeopleAdapter extends RecyclerView.Adapter<PeopleAdapter.BindingViewHolder>  {
    private Context mContext;
    public List<User> mUsers;



    public PeopleAdapter(Context context, List<User> speakers) {
        mContext = context;
        mUsers = speakers;
    }

    @Override
    public BindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = DataBindingUtil
                .inflate(LayoutInflater
                        .from(parent.getContext()), R.layout.user_item, parent, false
                );
        return new BindingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(BindingViewHolder holder, int position) {

        ViewDataBinding viewDataBinding = holder.getViewDataBinding();
        viewDataBinding.setVariable(
                1,
                new UserModelView(mContext, mUsers.get(position))
        );

    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class BindingViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding mViewDataBinding;

        public BindingViewHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());

            mViewDataBinding = viewDataBinding;
            mViewDataBinding.executePendingBindings();
        }

        public ViewDataBinding getViewDataBinding() {
            return mViewDataBinding;
        }
    }
}
