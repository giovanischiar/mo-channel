struct MainBuildings: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(MainBuildingsPathData(x: x, y: y))
            .stroke(width: 1)
            .fill(color: -"mainBuildingsColor")
            .stroke(color: "#000000")
            .strokeLine(cap: "butt")
            .strokeLine(join: "round")

        Path()
            .d(SalesForceTowerTopPathData(x: x-7.18, y: y-7))
            .stroke(width: 1)
            .fill(color: -"salesforceTowerTopColor")
            .stroke(color: "#000000")
            .strokeLine(cap: "butt")
            .strokeLine(join: "round")
    }
}
