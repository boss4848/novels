## Project architecture
- Clean Architecture
- MVVM

## Dependencies
```kotlin
// Coil
implementation(libs.coil.compose)
implementation(libs.coil3.coil.network.okhttp)
// Gson
implementation(libs.gson)
// Retrofit2
implementation(libs.retrofit)
implementation(libs.converter.gson)
// Koin Core
implementation(libs.koin.core)
implementation(libs.koin.android)
implementation(libs.koin.androidx.compose)
implementation(libs.koin.core.viewmodel)
implementation(libs.koin.compose.viewmodel)
// Material3
implementation(libs.material3)
// Accompanist
implementation(libs.accompanist.pager)
implementation(libs.accompanist.pager.indicators)
```

## Initialization
Before running, please set up the secret value in `build.gradle.kts` and perform a clean build of the project.
```kotlin
buildConfigField(
    type = "String",
    name = "NOVEL_SECRET",
    value = ""
)
```
## Mobile preview
Uploading Screen Recording 2568-05-18 at 12.16.17.mov…

