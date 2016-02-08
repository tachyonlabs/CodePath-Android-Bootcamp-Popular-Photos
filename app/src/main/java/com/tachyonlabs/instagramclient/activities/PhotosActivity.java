package com.tachyonlabs.instagramclient.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.tachyonlabs.instagramclient.R;
import com.tachyonlabs.instagramclient.adapters.InstagramPhotosAdapter;
import com.tachyonlabs.instagramclient.models.InstagramPhoto;
import com.tachyonlabs.instagramclient.models.InstagramPhotoComment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    ArrayList<InstagramPhoto> photos;
    private InstagramPhotosAdapter aPhotos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photos);
        // Maybe later I'll set the ActionBar font directly, but for now (after
        // trying a few methods that didn't work) I'm just doing it as a logo
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.logo);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        photos = new ArrayList<>();
        // create the adapter linking it to the source
        aPhotos = new InstagramPhotosAdapter(this, photos);
        // find the ListView from the layout
        ListView lvPhotos = (ListView) findViewById(R.id.lvPhotos);
        // set the adapter binding it to the ListView
        lvPhotos.setAdapter(aPhotos);
        // send out API request to popular photos
        fetchPopularPhotos();
    }

    // trigger API request
    public void fetchPopularPhotos() {

        String url = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        // create the network client
        AsyncHttpClient client = new AsyncHttpClient();
        // trigger the GET request
        client.get(url, null, new JsonHttpResponseHandler() {
            // onSuccess (worked, 200)
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                 // iterate each of the Jave items and decode the items into a Java object
                JSONArray photosJSON = null;
                try {
                    photosJSON = response.getJSONArray("data"); // array of posts
                    // iterate array of posts
                    for (int i = 0; i < photosJSON.length(); i++) {
                        // get the JSON object at that position
                        JSONObject photoJSON = photosJSON.getJSONObject(i);
                        // decode the attributes of the JSON into a data model
                        // see https://www.instagram.com/developer/deprecated/endpoints/media/#get_media_popular
                        InstagramPhoto photo = new InstagramPhoto();
                        photo.username = photoJSON.getJSONObject("user").getString("username");
                        photo.userProfileImageUrl = photoJSON.getJSONObject("user").getString("profile_picture");
                        photo.caption = photoJSON.getJSONObject("caption").getString("text");
                        photo.imageUrl = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
                        photo.imageHeight = photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height");
                        photo.likesCount = photoJSON.getJSONObject("likes").getInt("count");
                        photo.createdTime = photoJSON.getInt("created_time");

                        JSONArray commentsJSON = photoJSON.getJSONObject("comments").getJSONArray("data");
                        InstagramPhotoComment[] photoComments = new InstagramPhotoComment[commentsJSON.length()];
                        for (int j = 0; j < Math.min(commentsJSON.length(), 2); j++) {
                            JSONObject commentJSON = commentsJSON.getJSONObject(j);
                            InstagramPhotoComment photoComment = new InstagramPhotoComment();
                            photoComment.username = commentJSON.getJSONObject("from").getString("username");
                            photoComment.text = commentJSON.getString("text");
                            photoComments[j] = photoComment;
                        }
                        photo.comments = photoComments;

                        // add decoded objects to the photos
                        photos.add(photo);
                    }
                } catch(JSONException e) {
                    e.printStackTrace();
                }
                // let the adapter know the data has changed and it should refresh the ListView
                aPhotos.notifyDataSetChanged();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                //super.onFailure(statusCode, headers, responseString, throwable);
            }
        });
    }
}
