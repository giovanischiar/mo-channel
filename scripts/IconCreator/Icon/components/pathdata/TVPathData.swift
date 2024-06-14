struct TVPathData : PathDatable {
    var  commands: [PathDataCommand]
    
    init(x: Double = 0, y: Double = 0, received: Bool = true) {
        commands = "M 47.60509 77.124825 l 12.692913 0 l 0 1.4488144 l -12.692913 0 Z M 29.431995 48.323475 l 49.13386 0 l 0 28.346455 l -49.13386 0 Z".commands
        //commands = "M 44.770447 75.41091 l 12.692913 0 l 0 1.448822 l -12.692913 0 Z M 26.597351 46.609566 l 49.133858 0 l 0 28.346458 l -49.133858 0 Z".commands
        commands.move(x: x, y: y)
    }
}
