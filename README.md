<h1 align="center">
  <img src="readme-res/banner.png" width="432" height="243"><br>
  Mo Channel
</h1>

<p align="center">
  <strong>See your own channel with videos hosted in your computer!</strong><br>
  This Android TV application allows you to watch videos hosted on your own computer on your TV.
</p>

- [Usage](#usage)
- [Use Cases](#use-cases)
- [Technologies](#technologies)
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

## Future Tasks
  - Create Tests.
  - Add [IconCreator](https://github.com/giovanischiar/icon-creator) (my own library).
  - Work with multiples servers at the same time.