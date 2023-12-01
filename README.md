# BenchmarkSample

Macrobenchmark library is used testing larger use cases of your app, including app startup and complex UI manipulations, such as scrolling a RecyclerView or LazyList or running animations

## Metrics
The following metrics can be captured by using Macrobenchmark libary </br>
- **StartupTimeMetric:** captures app startup timing metrics with the following values:
   - **timeToInitialDisplayMs**: The amount of time from when the system receives a launch intent to when it renders the first frame of the destination Activity.
   - **timeToFullDisplayMs**: The amount of time from when the system receives a launch intent to when the app reports fully drawn using the reportFullyDrawn() method. The measurement stops at the completion of rendering the first frame after—or containing—the reportFullyDrawn() call. This measurement might not be available on Android 10 (API level 29) and earlier.
 
- **FrameTimingMetric:** captures timing information from frames produced by a benchmark, such as a scrolling or animation, and outputs the following values:
   - **frameOverrunMs**: the amount of time a given frame misses its deadline by. Positive numbers indicate a dropped frame and visible jank or stutter. Negative numbers indicate how much faster a frame is than the deadline. Note: This is available only on Android 12 (API level 31) and higher.
   - **frameDurationCpuMs**: the amount of time the frame takes to be produced on the CPU on both the UI thread and the RenderThread.
 
- **TraceSectionMetric:** captures the number of times a trace section matching the provided sectionName occurs and the amount of time it takes.
- **PowerMetric:** captures the change in power or energy over the duration of your test for the provided power categories. Categories include the following. **CPU**, **DISPLAY**, **GPU**, **GPS**, **MEMORY**, **MACHINE_LEARNING**, **NETWORK**, **UNCATEGORIZED**

## Impact of Unnecessary Recompositions
To see the performance differences between non-unnecessary and unnecessary recompositions, I have written scrolling test on LazyColumn. To create many recompositions, I read the state value from LazyListState and used composable that list parameter which 
marked as unstable. As you can see in the following, dropped frame and visible jank or stutter went lower by %50 and we can see that unnecessary recompositions when they are supposed skipped created performance issue.

| Unnecessary | Non-Unnecessary |
| ------ | ----- |
| ![Screen Shot 2023-10-31 at 00 09 50](https://github.com/Ahmetgulte/BenchmarkSample/assets/59209907/b7bfe03d-e7cb-4b77-9119-332c92dae4eb)| ![Screen Shot 2023-10-31 at 00 13 22](https://github.com/Ahmetgulte/BenchmarkSample/assets/59209907/8d7cc02a-bef2-4000-a315-87c1c08c0ee2)|

## Baseline Profiles
Baseline Profiles improve code execution speed by about 30% from the first launch by avoiding interpretation and just-in-time (JIT) compilation steps for included code paths.

Baseline Profiles make all user interactions—such as app startup, navigating between screens, or scrolling through content—smoother from the first time they run. By increasing the speed and responsiveness of an app, Baseline Profiles can lead to more daily active users and a higher average return visit rate.

Baseline Profiles help guide optimization beyond app startup by providing common user interactions that improve app runtime from the first launch

I have run the ```startup()``` with 2 different CompilationMode. ```CompilationMode.DEFAULT``` will pre-compile the application if a Baseline Profiles is included in the app. ```CompilationMode.None()``` will won't make the app pre-compiled so does not use any Baseline Profiles.
The results are in the following and we see that application start time got faster when Baseline Profiles is created in the app.

| CompilationMode.None() | CompilationMode.DEFAULT |
| ------ | ----- |
|  ![Screen Shot 2023-12-01 at 16 47 29](https://github.com/Ahmetgulte/BenchmarkSample/assets/59209907/b3e61c0e-6ff4-41a0-b0e1-4ca02e4fce99)| ![Screen Shot 2023-12-01 at 16 47 43](https://github.com/Ahmetgulte/BenchmarkSample/assets/59209907/f2fba0f9-6dfc-4892-b813-8cf3fa1ad57b)|
