# Intro
The aim of this course is to teach fundamental concepts of RxJava and RxAndroid and take you from a novice to intermediate RxJava developer.

Head on to [https://www.androidhive.info/RxJava](https://www.androidhive.info/RxJava) for detailed explanation of RxJava modules.
# Basics
* [Example1Activity](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/basics/Example1Activity.java) - Basic `Observable`, `Observer` and Subscription example. Emitting list of animal names.
* [Example2Activity](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/basics/Example2Activity.java) - Introduced Disposable to dispose the subscription.
* [Example3Activity](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/basics/Example3Activity.java) - Introducing `filter()` operator to filter out the animal names starting with letter `b`.
* [Example4Activity](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/basics/Example4Activity.java) - Example of chaining of operators. `map()` and `filter()` operators are used together.
* [Example5Activity](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/basics/Example5Activity.java) - Introduced `CompositeDisposable` and `DisposableObserver`. Also custom data type `Note` is used in this example.

# Operators
Read the [Introduction to RxJava Operators](https://www.androidhive.info/RxJava/rxjava-operators-introduction/) to get started with RxJava operators.
* [JustOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/JustOperatorActivity.java) - Creating an Observable using `just` operator.
* [FromOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/FromOperatorActivity.java) - Creating an Observable using `from` operator.
* [RangeOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/RangeOperatorActivity.java) - Creating an Observable using `range` operator.
* [BufferOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/BufferOperatorActivity.java) - `Buffer` emits data into batches instead of emitting one at a time. Calculating number of taps in certain period is explained in the example.
* [DebounceOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/DebounceOperatorActivity.java) - `Debounce` operators emits items only when a specified timespan is passed. An example of taking search query is explained.
* [FilterOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/FilterOperatorActivity.java) - `filter` allows the Observable to emit the only values those passes a test.
* [RepeatOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/RepeatOperatorActivity.java) - Creates an Observable that emits an item or series of items repeatedly.
* [SkipOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/SkipOperatorActivity.java) - `skip(n)` operator skips the emission of first N items emitted by an Observable.
* [TakeOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/TakeOperatorActivity.java) - `take(n)` takes first N emissions of an Observable.
* [DistinctOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/DistinctOperatorActivity.java) - `Distinct` operator filters out items emitted by an Observable by avoiding duplicate items in the list.
* [CountOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/CountOperatorActivity.java) - Counts number of items emitted by an Observable and emits only the count value.
* [ReduceOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/ReduceOperatorActivity.java) - Example of `reduce` operator. Applies a function to first item, takes the result and feeds back to same function on second item. This process continuous until the last emission. Once all the items are over, it emits the final result.
* [MaxOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/MaxOperatorActivity.java) - Finds the `maximum` valued item in the Observable sequence and emits that value
* [MinOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/MinOperatorActivity.java) - Finds the `minimum` valued item in the Observable sequence and emits that value
* [SumOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/SumOperatorActivity.java) - Calculates the sum of all the items emitted by an Observable and emits only the Sum value.
* [AverageOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/AverageOperatorActivity.java) - Calculates the average of all the items emitted by an Observable and emits only the Average value.
* [ConcatOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/ConcatOperatorActivity.java) - `Concat` operator combines output of two or more Observables into a single Observable. Maintains the order of execution.
* [MergeOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/MergeOperatorActivity.java) - `Merge` also merges multiple Observables into a single Observable but it won’t maintain the sequential execution.
* [MapOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/MapOperatorActivity.java) - `Map` operator transform each item emitted by an Observable and emits the modified item.
* [FlatMapOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/FlatMapActivity.java) - Example of `FlatMap` operator.
* [ConcatMapOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/ConcatMapOperatorActivity.java) - Example of `ConcatMap` operator.
* [SwitchMapOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/SwitchMapOperatorActivity.java) - Example of `SwitchMap` operator.
* [ZipOperator](https://github.com/ravi8x/RxAndroidExamples/blob/master/app/src/main/java/info/androidhive/rxandroidexamples/operators/ZipOperatorActivity.java) - Example of `Zip` operator.

# Retrofit Networking
[Android RxJava Networking with Retrofit, Gson](https://www.androidhive.info/RxJava/android-rxjava-networking-with-retrofit-gson-notes-app/)
RxJava networking using Retrofit library. An example of live Notes App is explained using Retrofit networking.
[Demo](https://www.youtube.com/watch?v=tQBWeM2dFuU)
# Android Examples
* [Android RxJava Instant Search – Local, Remote Databases (Retrofit)](https://www.androidhive.info/RxJava/android-rxjava-instant-search-local-remote-databases/)
Android example of adding instant search to a Contacts app. You will see a use case of Debounce, SwitchMap, Observables and Retrofit when put together.

* [Android Flight Ticket App – RxJava FlatMap (Retrofit)](https://www.androidhive.info/RxJava/flatmap-concatmap-operators-flight-tickets-app/)
Android example of simple Flight Ticket price listing app. You will learn the use of FlatMap and ConcatMap operators when used with Retrofit networking calls.

# Roadmap
* RxBinding
* RxJava Subjects
* RxJava Event Bus
* Understanding Marble Diagrams
* Data Storage (SQLite, Room Persistence)
* Flowable Backpressure Example
* Hot vs Cold Observables
* Side Effect Operators
* Volley Networking
* Form Validation
* Rx Runtime Permissions
* Timers & Intervals
* Clean Architecture
* MVP, MVVM Architecture
* Complete RxJava Apps