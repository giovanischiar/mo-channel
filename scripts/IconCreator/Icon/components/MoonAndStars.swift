struct MoonAndStars: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(MoonAndStarsPathData(x: x, y: y))
            .fill(color: -"moonColor")
    }
}
