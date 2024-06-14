struct TV: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(TVPathData(x: x, y: y))
            .stroke(width: 3)
            .stroke(color: "#000000")
            .strokeLine(cap: "butt")
            .strokeLine(join: "round")
    }
}
