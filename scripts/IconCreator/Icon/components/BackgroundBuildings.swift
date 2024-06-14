struct BackgroundBuildings: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(BackgroundBuildingsPathData(x: x, y: y))
            .fill(color: -"backgroundBuildingsColor")
    }
}
