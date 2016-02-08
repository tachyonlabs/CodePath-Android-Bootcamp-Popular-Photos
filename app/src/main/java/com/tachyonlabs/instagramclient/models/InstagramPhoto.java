package com.tachyonlabs.instagramclient.models;

public class InstagramPhoto {
    public String username;
    public String userProfileImageUrl;
    public String caption;
    public String imageUrl;
    public int imageHeight;
    public int likesCount;
    public int createdTime;
    public InstagramPhotoComment[] comments;
}
