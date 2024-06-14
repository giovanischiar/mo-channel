struct CirclePathData : PathDatable {
    var  commands: [PathDataCommand]
    
    init (x: Double = 0, y: Double = 0, received: Bool  = true) {
         commands = "m 18.110237 53.94507 l 0 0 c 0 -19.830067 16.075443 -35.90551 35.90551 -35.90551 l 0 0 c 9.522736 0 18.655441 3.7828903 25.38903 10.5164795 c 6.733589 6.733591 10.5164795 15.866297 10.5164795 25.389032 l 0 0 c 0 19.830063 -16.07544 35.90551 -35.90551 35.90551 l 0 0 c -19.830067 0 -35.90551 -16.075447 -35.90551 -35.90551 Z".commands
         commands.move(x: x, y: y)
    }
}
