struct MainBuildingsPathData : PathDatable {
    var  commands: [PathDataCommand]
    
    init(x: Double = 0, y: Double = 0, received: Bool  = true) {
        commands = "M 42.458397 76.54513 l 2.391819 -15.09874 l 2.3918152 15.09874Z M 47.24203 61.49722 l 4.7962914 0 l 0 15.0734215 l -4.7962914 0 Z M 52.03269 68.10608 l 4.7962914 0 l 0 8.438835 l -4.7962914 0 Z M 52.03252 68.039665 l 4.7962914 8.597099 M 52.03252 68.039665 l 4.7962914 8.597099 M 56.803764 68.039665 l -4.7962875 8.597099".commands
        commands.move(x: x, y: y)
    }
}



