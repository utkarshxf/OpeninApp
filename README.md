<h1 align="center">Openin App(Android application)</h1>
<p align="center">  
  Openin App demonstrates modern Android development with Jetpack Compose, Hilt, Coroutines, Flow, Jetpack (Room, ViewModel), and Material Design based on MVVM architecture.
</p>
<img src="https://github.com/user-attachments/assets/a92052d2-713d-4aca-800e-3580021517be"/>

## Download

Go to the [Releases](https://drive.google.com/file/d/1wnJQ8slxsfr1dyBVl3XKIknSkcrEZtK9/view?usp=sharing) to download the latest APK.
<img src="previews/preview.gif" align="right" width="320"/>
Go to  [Screens](#Screens) Section to See the Screen.



## Video

<img src="https://github.com/user-attachments/assets/d87afd63-6d2a-400c-8357-a2f7203c583c" width="200"/>
https://github.com/user-attachments/assets/f92b85d9-cf3a-4250-93c5-03dddafc983a


## Tech stack & Open-source libraries

- [Kotlin](https://kotlinlang.org/) based, utilizing [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous operations.
- Jetpack Libraries:
  - Jetpack Compose: Android’s modern toolkit for declarative UI development.
  - ViewModel: Manages UI-related data and is lifecycle-aware, ensuring data survival through configuration changes.
  - Navigation: Facilitates screen navigation, complemented by [Compose Navigation]([https://developer.android.com/develop/ui/compose/navigation]) for Screen Navigation.
  - [Hilt](https://dagger.dev/hilt/): Facilitates dependency injection.
- Architecture:
  - MVVM Architecture (View - ViewModel - Model): Facilitates separation of concerns and promotes maintainability.
  - Repository Pattern: Acts as a mediator between different data sources and the application's business logic.
- [Retrofit2](https://github.com/square/retrofit): Constructs REST APIs and facilitates paging network data retrieval.

## Architecture
**Openin App** adheres to the MVVM architecture and implements the Repository pattern, aligning with [Google's official architecture guidance](https://developer.android.com/topic/architecture).
The architecture of **STOCKS App** is structured into three distinct layers: the UI layer, domain layer and data layer. Each layer fulfills specific roles and responsibilities, outlined as follows:
**Openin App** follows the principles outlined in the [Guide to app architecture](https://developer.android.com/topic/architecture), making it an exemplary demonstration of architectural concepts in practical application.

### Architecture Overview
- The data layer operates autonomously from other layers, maintaining purity without dependencies on external layers.
- This loosely coupled architecture enhances component reusability and app scalability, facilitating seamless development and maintenance.

```sql
project_root
├── data
│   ├── model
│   ├── network
│   └── repository
├── di
├── domain.repository
├── presentation
│   ├── common
│   ├── dashboard
│   │   ├── components
│   │   ├── DashboardScreen.kt
│   │   └── DashboardScreenViewModel
│   └── ui.theme
├── usecase
├── util
├── MainActivity
└── MyApplication
```
### UI Layer
The UI layer encompasses UI elements responsible for configuring screens for user interaction, alongside the [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), which manages app states and restores data during configuration changes.
- UI elements observe the data flow, ensuring synchronization with the underlying data layer.

### Domain Layer
The domain layer is responsible for encapsulating the core business logic of the application. This layer acts as an intermediary between the UI and data layers, ensuring that data is processed and business rules are enforced before being presented to the user.


### Data Layer
The data layer is composed of repositories that handle business logic tasks fetching remote data from a network. This layer is designed to prioritize online access, functioning primarily as an online-first repository of business logic. It adheres to the principle of "single source of truth," ensuring that all data operations are centralized and consistent.<br>

## Data API
API Integration: Employ the specified API for this assignment:
https://api.inopenapp.com/api/v1/dashboardNew

## Screens
<img src="https://github.com/user-attachments/assets/ea8503c0-65fe-4df0-8606-7c98e48a11b4" width="200"/>
<img src="https://github.com/user-attachments/assets/b1eb0e3d-6085-4e98-89be-598b68d0cb35" width="200"/>
<img src="https://github.com/user-attachments/assets/411f7a76-9bc1-4748-a4a9-5c168bb3e1d7" width="200"/>



