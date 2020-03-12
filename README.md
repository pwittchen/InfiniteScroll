# InfiniteScroll  [![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-InfiniteScroll-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/3237) [![Build Status](https://travis-ci.org/pwittchen/InfiniteScroll.svg?branch=master)](https://travis-ci.org/pwittchen/InfiniteScroll) ![Maven Central](https://img.shields.io/maven-central/v/com.github.pwittchen/infinitescroll.svg?style=flat)
Infinite Scroll (Endless Scrolling) for RecyclerView in Android

JavaDoc is available at: http://pwittchen.github.io/InfiniteScroll/

Contents
--------
- [Motivation](#motivation)
- [Examples](#examples)
- [Usage](#usage)
- [Download](#download)
- [Tests](#tests)
- [Code style](#code-style)
- [Static Code Analysis](#static-code-analysis)
- [Who is using this library?](#who-is-using-this-library)
- [License](#license)

Motivation
----------

For a long time I couldn't find the right implementation of the infinite scroll AKA endless scroll for Android. A few solutions I've found weren't production ready, weren't working correctly or had too many features. I wanted to have small, easy and flexible solution to implement infinite scroll for Android, which works with `RecyclerView` from the newest Android API. That's why this project was created.

Examples
--------

Sample app can be found in `app` directory.

Below, you can see an animation presenting, how sample application works.

Moreover, you can see examplary usage of this library in [SearchTwitter](https://github.com/pwittchen/SearchTwitter) app.

![Demo](https://raw.githubusercontent.com/pwittchen/InfiniteScroll/master/demo.gif)

Usage
-----

Create necessary fields in your `Activity`:

```java
public RecyclerView recyclerView;
private LinearLayoutManager layoutManager;
```

Create new `InfiniteScrollListener`:

```java
private InfiniteScrollListener createInfiniteScrollListener() {
  return new InfiniteScrollListener(maxItemsPerRequest, layoutManager) {
    @Override public void onScrolledToEnd(final int firstVisibleItemPosition) {
      // load your items here
      // logic of loading items will be different depending on your specific use case
      
      // when new items are loaded, combine old and new items, pass them to your adapter
      // and call refreshView(...) method from InfiniteScrollListener class to refresh RecyclerView
      refreshView(recyclerView, new MyAdapter(items), firstVisibleItemPosition);
    }
  }
}
```

Initialize `RecyclerView` and `LinearLayoutManager` in your `Activity`:

```java
@Override protected void onCreate(Bundle savedInstanceState) {
  recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

  layoutManager = new LinearLayoutManager(this);
  recyclerView.setHasFixedSize(true);
  recyclerView.setLayoutManager(layoutManager);
  
  // set your custom adapter
  recyclerView.setAdapter(new MyAdapter(items));
  
  // add InfiniteScrollListener as OnScrollListener
  recyclerView.addOnScrollListener(createInfiniteScrollListener());
}
```

If you want to display loading progress, you should add additional view for it, show it while loading starts and hide it when loading is finished. Check exemplary app in this repository to see concrete solution.

That's it!

Download
--------

Latest version: ![Maven Central](https://img.shields.io/maven-central/v/com.github.pwittchen/infinitescroll.svg?style=flat)

replace `x.y.z` with the latest version

You can depend on the library through Maven:

```xml
<dependency>
    <groupId>com.github.pwittchen</groupId>
    <artifactId>infinitescroll</artifactId>
    <version>x.y.z</version>
</dependency>
```

or through Gradle:

```groovy
dependencies {
  compile 'com.github.pwittchen:infinitescroll:x.y.z'
}
```

Tests
-----

To execute unit tests run:

```
./gradlew test
```

Code style
----------

Code style used in the project is called `SquareAndroid` from Java Code Styles repository by Square available at: https://github.com/square/java-code-styles.

Static Code Analysis
--------------------

To run Static Code Analysis, type:

```
./gradlew check
```

Reports from analysis are generated in `library/build/reports/` directory.

Who is using this library?
-------------------------

- [Noute](https://play.google.com/store/apps/details?id=com.github.pierry.noute)
- and more...

Are you using this library in your app and want to be listed here? Send me a Pull Request or an e-mail to piotr@wittchen.io

License
-------

    Copyright 2016 Piotr Wittchen

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.



