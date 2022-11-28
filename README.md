# Marvel Hero Test App

This is a simple Android application that queries a paginated list of heroes from the official [Marvel](https://developer.marvel.com/docs) API, and shows a very simplified view of any hero detail, with links redirecting to a website with more information.

## Project Characteristics

This project makes use of the following tools and solutions:

* 100% [Kotlin](https://kotlinlang.org/).
* MVVM design pattern.
* [Android Jetpack](https://developer.android.com/jetpack) components
* Single-activity architecture (similar to SPA in web), using [Android Navigation components](https://developer.android.com/guide/navigation/navigation-getting-started) to deal fragment transactions.
* Basic Unit testing
* Dependency Injection

## Technical Stack

This project was compiled with SDK 24 as a minimum and SDK 33 as target, as of the date of release. The tech stack used for this project includes:

* [Kotlin](https://kotlinlang.org/) as the main programming language
* [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) to perform background operations (API calls, UI refresh, etc)
* [Koin](https://insert-koin.io/) for dependency injection
* [Retrofit](https://square.github.io/retrofit/) for networking
* Some [Jetpack](https://developer.android.com/jetpack) components:
    * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) to handle in-app navigation between fragments
    * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to deal with data update in the UI
    * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform an action when lifecycle state changes
    * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way
    * [View Binding](https://developer.android.com/topic/libraries/view-binding) to ease the UI integration with the view-relate codebase.
    * [Paging](https://developer.android.com/topic/libraries/architecture/paging/v3-overview) to deal with paginated data lists
    * Due to time constraints, the use of [Room](https://developer.android.com/topic/libraries/architecture/room) for in-app data persistence was skipped.
* [Glide](https://bumptech.github.io/glide/l) for image loading
* [and more...](https://github.com/mayokunthefirst/CardInfoFinder/blob/master/buildSrc/src/main/kotlin/Dependencies.kt)

* Architecture:
    * MVVM as the main design pattern. It was decided to forgo the implementation CLEAN architecture due time constraints.
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)

* Testing:
    * [Unit Tests](https://en.wikipedia.org/wiki/Unit_testing) ([JUnit](https://junit.org/junit4/)) as base testing platform.
    * [Mockito](https://site.mockito.org/) as testing framework.
    * [Mockito-Kotlin](https://github.com/mockito/mockito-kotlin), a Mockito wrapper to simplify test writing.
* Gradle:
    * Migrated from Groovy syntaxt to [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) for better readability and to implement better dependency version-handling in the future.
    * Plugins like ([SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args)) to be used with the Navigation components, [Secrets](https://developers.google.com/maps/documentation/android-sdk/secrets-gradle-plugin) to use API keys and sensitive data in the `local.properties` file, and [ktlint-gradle](https://github.com/JLLeitschuh/ktlint-gradle) which is a ktlint wrapper for code check and formatting.

## Architecture
Since the application was developed with a single module, package-oriented architecture, the project itself is structured in the following manner:

* `data`: package containing the data models used in the application, resulting from the API calls. Since the Marvel API encapsulates the responses in common data wrappers, the container classes and the relevant data classes were stored in separated packages.
* `di`: package containing the dependency-injection modules and object for their initialization.
* `network`: package containing classes dealing with API requests with Retrofit and Interceptors for injecting the keys in the requests.
* `repository`: package containing the repository class used for this project, it handles and transforms the data obtained from the API. It also contains a Paging data source for paginated-lists.
* `ui`: it contains all the packages and classes related to the UI. It is sub-divided in:
    * `activity`: contains the application's `MainActivity`.
    * `adapter`: this project uses a generic `DynamicAdapter` in order to reduce the boilerplate that implementing the `RecyclerView` is known for. It uses `ItemModel` classes to encapsulate the data classes to be used in the lists, `TypeFactory` classes to select the corresponding `ViewHolder` and layout, and a base `DiffCallback` object.
        *  __NOTE__: given the nature of the `DynamicAdapter`, it wasn't possible to integrate or adapt it in the context of the native `PagingAdapter` needed for pagination, so the latter had to be implemented using the `HeroItemModel` directly instead of reflecting from the base `ItemModel` class.
    * `fragment`: package containing the relevant fragments and a `BaseFragment` with common methods.
    * `viewmodel`: package containing the viewmodel used in the application.

### Unit Testing
Due time constraints, only simple unit testing for the `CharacterRepository` class were made. A `BaseUTTest` class using the `KoinTest` was made in order to deal with the `MockWebServer` initialization, and also to leave the `@Before` and `@After` methods common for future test implementation. A `Constants.kt` file was also used to avoid over-usage of strings, and several JSON files with mock data were placed in the `resources` folder in order to be used in the tests.

## Installation
In order to install this project from Android Studio it is needed to provide a public and a private key obtained from the Marvel Developer website, and add them in the root `local.properties` file, following the same convention:
* PUBLIC_KEY="yout-public-key"
* PRIVATE_KEY="your-private-key"
  The APK file can also be downloaded from the "releases" page of this repository.

## LICENSE
```
MIT License

Copyright (c) 2022 Christopher Serrano

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```