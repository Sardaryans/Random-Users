package com.example.suren.randomusers.modelview;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.example.suren.randomusers.R;
import com.example.suren.randomusers.model.User;
import com.squareup.picasso.Picasso;

public class UserModelView {

    private Context mContext;
    private User mUser;

    public UserModelView(Context context, User user) {
        mContext = context;
        mUser = user;
    }

    public String getName() {
        return mUser.getName().toString();
    }

    public String getPictureURL() {
        return mUser.getPicture().getLarge();
    }


    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.user_pic)
                .into(view);
    }
}
