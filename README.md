# Bug it Core (Network) Library

## Overview

The Core Network Library is a modular Android library designed to simplify network operations in Bug it Android applications. It provides a set of abstractions and implementations for handling network requests, making it easier to manage API interactions in a clean and efficient manner.

## Features

- Support for multiple data sources (e.g., Firebase, Google Sheets, Notion).
- Easy-to-use interfaces for defining network operations.
- Room integration for local data persistence.
- Hilt dependency injection for easy management of dependencies.

## Installation

### Gradle

To include the Core Network Library in your project, add the following dependency in your project's `build.gradle` or `settings.gradle` file:

```groovy
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        ...
        mavenCentral()
        maven {
            url = uri("https://maven.pkg.github.com/moatazhamada/bug-it-core")
        }
    }
}
```

add then the following dependency in your app's `build.gradle` file:

```groovy
dependencies {
    implementation 'com.moataz.core.network:presentation:1.0.1' // Replace with the appropriate version
}
```

#### Latest Version

You can always find the latest release of the library at the following link:  
[Latest Releases](https://github.com/moatazhamada/bug-it-core/releases)

### Selective Module Inclusion

You can also include specific modules from the Core Network Library:

- **Data Module**: If you only need data handling, add this dependency:

```groovy
dependencies {
    implementation 'com.moataz.core.network:data:1.0.1' // Replace with the appropriate version
}
```

- **Domain Module**: If you only need domain logic, add this dependency:

```groovy
dependencies {
    implementation 'com.moataz.core.network:domain:1.0.1' // Replace with the appropriate version
}
```

### Manual Installation

If you prefer to manually include the library:

1. Download the latest release from the [Releases Page](https://github.com/moatazhamada/bug-it-core/releases) or [Packages Page](https://github.com/moatazhamada?tab=packages&repo_name=bug-it-core).
2. Unzip the downloaded file (if downloaded from the release page) and place the library module into your Android project.
3. In your app's `build.gradle` file, for ex. include the module as local aar files.
4. Sync your project to ensure the dependencies are resolved correctly.
<!---
## Usage

### Initial Setup

To use the Core Network Library, you need to set up Hilt for dependency injection in your application. Add the following in your `Application` class:

```kotlin
@HiltAndroidApp
class MyApplication : Application() {
}
```

### Using the Library

1. **Define your data sources** by implementing the `BugsRemoteDataSource` interface for each provider (e.g., Google Sheets, Notion).

2. **Create a `BugRepository` instance** using Hilt:

```kotlin
@HiltViewModel
class BugViewModel @Inject constructor(
    private val bugRepository: BugRepository
) : ViewModel() {
    // Use the bugRepository for network operations
}
```

3. **Call the `getBugs()` method** to fetch bug data from the remote data sources:

```kotlin
viewModelScope.launch {
    val bugs = bugRepository.getBugs()
    // Handle the bugs list
}
```

## Contribution

Contributions are welcome! If you'd like to contribute, please follow these steps:

1. Fork the repository.
2. Create a new feature branch (`git checkout -b feature/YourFeature`).
3. Commit your changes (`git commit -m 'Add some feature'`).
4. Push to the branch (`git push origin feature/YourFeature`).
5. Create a new Pull Request.
-->

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Contact

For any inquiries or support, please contact [motazhamada@gmail.com](mailto:motazhamada@gmail.com).
