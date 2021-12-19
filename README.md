# News App

Overview
-----
- The app shows the top articles, users can select an article to view its detail.
- If there's no connection, the app shows the previously downloaded news.
- If there's error, the app simply shows shimmer effect.
- Support diff UIs for phone in landscape and portrait mode.

There are 2 activities
- NewsListActivity (launcher activity). Here is its happy path:
   1. Show a shimmer effect.
   2. Fetches the top articles from newsapi, then saves the articles into a database. Simultaneously, we observe and retrieve the articles
   from the database. This ensures we have a unidirectional flow of data.
   3. Show articles with RecyclerView and stop the shimmer.
   4. Tap on an article, send its ID to the NewsDetailActivity.
- NewsDetailActivity
   1. Simply get the article by the passed ID from the database. In reality, we might fetch the detail from an API.

 API
 -----
 - https://newsapi.org/docs/endpoints/top-headlines - It provides a single API for developers to get the top-headlines
 - No setup required for accessing this API.

 Tech stack
 -----
 - Kotlin
 - RxJava
 - Retrofit & Moshi
 - Room, LiveData, ViewModel, Lifecycle
 - MVVM & Data binding
 - Glide
 - Dagger2
 - JUnit
