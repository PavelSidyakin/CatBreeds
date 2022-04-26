# Cat Breeds 

An example of Kotlin Multiplatform app.  
Supported targets: android, desktop (JVM).   
  
### Popular technologies/libraries used

- Common: Clean Architecture, Kotlin Multiplatform, MVI
- Asynchnonusity: Kotlin Coroutines
- Dependency Injection: Kodein
- UI layouts: Jetbrains Compose, Android Compose
- MVI: MVIKotlin
- Navigation: Decompose
- Local storage: SqlDelight
- HTTP client: Ktor
- Localization: Moko resources
- Unit testing: Kotest (framework, assertions), MockK

### Remote API

The app uses The Cat API. 

To run the app you need an API key. Get a free API Key here: https://thecatapi.com/signup

Put in your home `gradle.properties`:

```
catApiKey=YOUR_API_KEY
```


### Module structure

##### Notation

<img src="https://user-images.githubusercontent.com/50498272/164989026-c0932bb8-35eb-4db3-b01c-34bea756c284.png" height=14>  - Android module    

<img src="https://user-images.githubusercontent.com/50498272/164988981-12ca6b31-3d81-4dea-8590-fcacccd84486.png" height=14> - JVM module    

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> - Multiplatform module

##### Modules

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `app:common` - common application

<img src="https://user-images.githubusercontent.com/50498272/164989026-c0932bb8-35eb-4db3-b01c-34bea756c284.png" height=14> `app:android` - android application

<img src="https://user-images.githubusercontent.com/50498272/164988981-12ca6b31-3d81-4dea-8590-fcacccd84486.png" height=14> `app:desktop` - desktop (jvm) application

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `di` - contains reference to global DI (service locator) 

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `resources` - contains string resources

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `utils:presentation_utils` - various presentation utilities

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `widgets:compose` - compose widgets

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `data:remote` - remote API

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `data:local` - local database

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed:data` - local and remote repositories implementation

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed:domain` - uses data from remote and the local repositories

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed_list:breed_list_domain` - prepares data for breed list

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed_list:breed_list_ui` - UI for breed list (MVI and view)

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed_info:breed_info_domain` - prepares data for breed info

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:breed_info:breed_info_ui` - UI for breed info (MVI and view)

<br/>

<img src="https://user-images.githubusercontent.com/50498272/164987841-3283deb3-67f6-44ba-b03a-e6847ae52b22.png" height=12> `feature:root_screen:common` - root screen navigation logic

<img src="https://user-images.githubusercontent.com/50498272/164989026-c0932bb8-35eb-4db3-b01c-34bea756c284.png" height=14> `feature:root_screen:android` - contains MainActivity 

<img src="https://user-images.githubusercontent.com/50498272/164988981-12ca6b31-3d81-4dea-8590-fcacccd84486.png" height=14> `feature:root_screen:desktop` - contains  MainScreen composable (compose-jb)

