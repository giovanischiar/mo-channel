<h1 align="center">
  <img src="readme-res/ic_launcher.svg" width="100" height="100"><br>
  Mo Channel
</h1>

<p align="center">
  <strong>See your own channel with videos hosted in your computer!</strong><br>
  This Android TV application allows you to watch videos hosted on your own computer on your TV.
</p>

- [Usage](#usage)
- [Use Cases](#use-cases)
- [Technologies](#technologies)
- [Diagrams](#diagrams)
  - [Package `io.schiar.ruleofthree`](#package-ioschiarruleofthree)
  - [Package `view` with `viewmodel`](#package-view-with-viewmodel)
  - [Package `view.viewdata`](#package-viewviewdata)
  - [Package `viewmodel` with `view.viewdata`](#package-viewmodel-with-viewviewdata)
  - [Package `viewmodel` with `model.repository`](#package-viewmodel-with-modelrepository)
  - [Package `model`](#package-model)
  - [Package `model.repository` and `model`](#package-modelrepository-and-model)
  - [Package `model.repository` with `model.datasource` and `library`](#package-modelrepository-with-modeldatasource-and-library)
- [Future Tasks](#future-tasks)

## Usage
  This application requires downloading the [mo-channel-server](https://github.com/giovanischiar/mo-channel-server) executable on the machine where the videos are located in order to be available to the application.

## Use Cases
|||
|:-:|:-:|
||<img src="readme-res/screenshots/editting-server-url.gif" width="800" height="450">|
||This is the app when you open it for the first time. Since the Server URL is empty, it opens the settings to input the Server URL. If you are running the server on your computer, you'll need to input the IP number the server will tell you, along with the port number.|
||<img src="readme-res/screenshots/initial-screel-with-content.png" width="800" height="450">|
||Here is the screen with some content registered. In this example, you can store concerts you've recorded on your computer, run the [mo-channel-server](https://github.com/giovanischiar/mo-channel-server), and input the URL in the settings.|
||<img src="readme-res/screenshots/tv-show-screen.png" width="800" height="450">|
||This is the page when you press on one item. It shows the 'episodes' per 'season'. Episodes are the videos, while 'season' refers to the subfolder where you put the videos.|

# Technologies
|Technology|Purpose|
|:-:|:-:|
|<img src="https://3.bp.blogspot.com/-VVp3WvJvl84/X0Vu6EjYqDI/AAAAAAAAPjU/ZOMKiUlgfg8ok8DY8Hc-ocOvGdB0z86AgCLcBGAsYHQ/s1600/jetpack%2Bcompose%2Bicon_RGB.png" width="50" height="50"><br>[Jetpack Compose](https://developer.android.com/jetpack/compose)|Design UI|
|<img src="https://avatars.githubusercontent.com/u/1342004?s=48&v=4" width="50" height="50"><br>[Exoplayer](https://github.com/google/ExoPlayer)|The video player used by this application|
|<img src="https://4.bp.blogspot.com/-NnAkV5vpYuw/XNMYF4RtLvI/AAAAAAAAI70/kdgLm3cnTO4FB4rUC0v9smscN3zHJPlLgCLcBGAs/s1600/Jetpack_logo%2B%25282%2529.png" width="50" height="50"><br>[Room](https://developer.android.com/jetpack/androidx/releases/room)|Persist application data|
|<img src="https://github.githubassets.com/assets/GitHub-Mark-ea2971cee799.png" width="50" height="50"><br>[IconCreator](https://github.com/giovanischiar/icon-creator)|Generate application Icon (my own library)|

## Diagrams
  Please check <a href="https://github.com/giovanischiar/diagram-notation">this repository</a> to learn more about the notation I used to create the diagrams in this project.

### Package `io.schiar.ruleofthree`
  This diagram shows all the packages the application has, along with their structures. Some packages are simplified, while others are more detailed.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/io-schiar-mochannel-structure-diagram.dark.svg">
    <img alt="Package io.schiar.mochannel Diagram" src="./readme-res/diagrams/io-schiar-mochannel-structure-diagram.light.svg">
  </picture>

### Package `view` with `viewmodel`
  These diagrams illustrate the relationship between screens from `view` and `viewmodel` classes. The arrows from the View Models represent View Data objects (classes that hold all the necessary data for the view to display), primitives, or collections encapsulated by [State Flows](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/), which are classes that encapsulate data streams. Every update in the View Data triggers the State Flow to emit these new values to the `view`, and the view updates automatically. Typically, the methods called from screens in `view` to classes in `viewmodel` trigger these changes, as represented in the diagram below by arrows from the `view` screens to `viewmodel` classes.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/view-viewmodel-diagram.dark.svg">
    <img alt="Package view with viewmodel Diagram" src="./readme-res/diagrams/view-viewmodel-diagram.light.svg">
  </picture>

### Package `view.viewdata`
  View Datas are classes that hold all the data the `view` needs to present. They are created from `model` classes and served by View Models to the `view`. This diagram represents all the associations among the classes in the `view.viewdata`.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/viewdata-diagram.dark.svg">
    <img alt="Package viewdata Diagram" src="./readme-res/diagrams/viewdata-diagram.light.svg">
  </picture>

### Package `viewmodel` with `view.viewdata`
  View Models serve the `view` with objects made from `viewmodel.viewdata` classes, collections, or primitive objects encapsulated by State Flows. This diagram represents all the associations among the classes in `viewmodel` and `view.viewdata`.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/viewmodel-viewdata-diagram.dark.svg">
    <img alt="Package viewmodel with viewdata Diagram" src="./readme-res/diagrams/viewmodel-viewdata-diagram.light.svg">
  </picture>

### Package `viewmodel` with `model.repository`
  View Models also serve as a [façade](https://en.wikipedia.org/wiki/Facade_pattern), triggering methods in `model.repository` classes. This diagram shows that each View Model has its own Repository class and illustrates all methods each View Model calls, represented by arrows from View Models to Repository classes.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/viewmodel-repository-diagram.dark.svg">
   <img alt="Package viewmodel with repository Diagram" src="./readme-res/diagrams/viewmodel-repository-diagram.light.svg">
  </picture>

### Package `model`
  Model classes handle the logic of the application. This diagram represents all the associations among the classes in the `model`.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/model-diagram.dark.svg">
    <img alt="Package model" src="./readme-res/diagrams/model-diagram.light.svg">
  </picture>

### Package `model.repository` and `model`
  These diagrams represent all the associations among the classes in `model.repository` and `model`.

<picture>
  <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/repository-model-diagram.dark.svg">
  <img alt="Repository Model Diagram" src="./readme-res/diagrams/repository-model-diagram.light.svg">
</picture>

### Package `model.repository` with `model.datasource` and `library`
  Repository classes utilize the Data Source classes to perform the creation, retrieval, update, and deletion of the model objects. The Data Source classes use the database to persist these alterations and the requester to fetch the application data. In this case, the data can come from the database in the case of ServerURL or the API server in the case of TV shows. It can also be sourced from local sources, enabling testing of the application. This diagram represents all the associations among the classes in `model.repository`, `model.datasource`, and `library`. It also demonstrates the communications between each Repository through interfaces located at `model.repository.listener` package.

  <picture>
    <source media="(prefers-color-scheme: dark)" srcset="./readme-res/diagrams/dark/repository-datasource-library-diagram.dark.svg">
    <img alt="Package repository with data source and library" src="./readme-res/diagrams/repository-datasource-library-diagram.light.svg">
  </picture>

## Future Tasks
  - Create Tests.
  - Add [IconCreator](https://github.com/giovanischiar/icon-creator) (my own library).
  - Work with multiples servers at the same time.