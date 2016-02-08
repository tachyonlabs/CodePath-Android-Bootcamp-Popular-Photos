# Project 1 - Popular Photos

Popular Photos is an android app that allows a user to check out popular photos from Instagram. The app utilizes Instagram API to display images and basic image information to the user.

Time spent: I'll try to put a value in here for next week's assignment, because I know it's useful information for you to aggregate from the class as a whole, but right now I just don't want to feel like I'm in a race or something.

## User Stories

The following **required** functionality is completed:

* [x] User can **scroll through current popular photos** from Instagram
* [x] For each photo displayed, user can see the following details:
  * [x] Graphic, Caption, Username
  * [x] Relative timestamp, like count, user profile image

The following **optional** features are implemented:

* [x] Display each user profile image using a RoundedImageViewDisplay each user profile image using a [RoundedImageView](https://github.com/vinc3m1/RoundedImageView)
* [x] Display a nice default placeholder graphic for each image during loading
* [x] Improved the user interface through styling and coloring

The following **bonus** features are implemented:

* [x] Show last 2 comments for each photo

## Video Walkthrough 

Here's a walkthrough of implemented user stories:

<img src='http://tachyonlabs.com/miscimages/popular_photos4.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

OK, this one --
* [x] Display each photo with the same style and proportions as the real Instagram
-- confuses me. On one hand, I **think** so, because all the photos displayed look like perfectly fine Instagram photos, but on the other hand I can't make Instagram display the exact same photos for comparison. With photos that display with whitespace on the sides and/or top and bottom of the photo, this does seem to be part of the image the person uploaded rather than that I am displaying it incorrectly. Also, I guess that Instagram photos used to all be square, but this is no longer the case: [Thinking Outside the Square: Support for Landscape and Portrait Formats on Instagram](http://blog.instagram.com/post/127722429412/150827-portrait-and-landscape)

Also, this -- 
* [x] Show latest comments for each photo
-- is listed as an "optional" feature, whereas this --
* [x] Show last 2 comments for each photo
-- is listed as a "bonus" feature, and I'm not really sure what the difference is.

## Open-source libraries used

- [Android Async HTTP](https://github.com/loopj/android-async-http) - Simple asynchronous HTTP requests with JSON parsing
- [Picasso](http://square.github.io/picasso/) - Image loading and caching library for Android
- [RoundedImageView](https://github.com/vinc3m1/RoundedImageView) - Fast ImageView (and Drawable) that supports rounded corners (and ovals or circles)

## License

    Copyright 2016 Tané Tachyon

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
