package com.tachyonlabs.instagramclient.adapters;

import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tachyonlabs.instagramclient.R;
import com.tachyonlabs.instagramclient.models.InstagramPhoto;

import java.util.List;

public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto> {
    // what data do we need from the activity - context and data source
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> objects) {
        super(context, R.layout.item_photo, objects);
    }

    private String relativeDate(int createdDate) {
        // get an Instagram-style string of how long ago the photo was uploaded
        String dateString;
        long msDate = createdDate;
        msDate *= 1000;
        long relativeDate = System.currentTimeMillis() - msDate;
        if (relativeDate > 604800000L) {
            dateString = (relativeDate / 604800000L) + "w";
        } else if (relativeDate > 86400000L) {
            dateString = (relativeDate / 86400000L) + "d";
        } else if (relativeDate > 3600000L) {
            dateString = (relativeDate / 3600000L) + "h";
        } else if (relativeDate > 60000) {
            dateString = (relativeDate / 60000) + "m";
        } else {
            dateString = (relativeDate / 1000) + "s";
        }

        return dateString;
    }

    // use the template to display each photo
    public View getView(int position, View convertView, ViewGroup parent) {
        // get the data for this position
        InstagramPhoto photo = getItem(position);
        // check if we are using a recycled view, if not we need to inflate
        if (convertView == null) {
            // create a new view from template
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_photo, parent, false);
        }
        // look up the views for populating the data
        ImageView ivUserProfileImage = (ImageView) convertView.findViewById(R.id.ivUserProfileImage);
        TextView tvUsername = (TextView) convertView.findViewById(R.id.tvUsername);
        TextView tvDate = (TextView) convertView.findViewById(R.id.tvDate);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivPhoto);
        TextView tvLikes = (TextView) convertView.findViewById(R.id.tvLikes);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        // insert the model data into each of the view items
        tvUsername.setText(photo.username);
        tvDate.setText(relativeDate(photo.createdTime));
        tvLikes.setText("\u2665 " + photo.likesCount + " likes");
        Spanned formattedCaption = Html.fromHtml("<font color='#125688'>" + photo.username + "</font> " + photo.caption);
        tvCaption.setText(formattedCaption);
        // clear the imageviews, in case the view is recycled
        ivPhoto.setImageResource(0);
        ivUserProfileImage.setImageResource(0);
        // insert the images using Picasso
        Picasso.with(getContext()).load(photo.userProfileImageUrl).fit().into(ivUserProfileImage);
        Picasso.with(getContext()).load(photo.imageUrl).placeholder(R.drawable.placeholder).into(ivPhoto);
        // return the created item as a view
        return convertView;
    }
}
