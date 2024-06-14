struct SalesForceTowerTopPathData : PathDatable {
    var  commands: [PathDataCommand]
    
    init(x: Double = 0, y: Double = 0, received: Bool = true) {
        commands = "M 47.85612 57.872704 l 3.5681114 0 c 0.16119003 0 0.31577682 0.06402969 0.42975235 0.17800522 c 0.11397934 0.11397934 0.17800903 0.26856613 0.17800903 0.42975235 l 0 3.0387306 c 0 0.0000019073486 -0.0000019073486 0.0000038146973 -0.0000038146973 0.0000038146973 l -4.783596 -0.0000038146973 l 0 0 c -0.0000022888184 0 -0.0000038146973 -0.0000019073486 -0.0000038146973 -0.000004196167 l 0.0000038146973 -3.0386887 l 0 0 c 0 -0.3356552 0.27210236 -0.60775757 0.6077614 -0.60775757".commands
        commands.move(x: x, y: y)
    }
}
