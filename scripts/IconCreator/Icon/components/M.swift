struct M: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(MPathData(x: x, y: y))
            .stroke(width: 3)
            .fill(color: -"mColor")
            .stroke(color: -"mColor")
            .strokeLine(cap: "butt")
            .strokeLine(join: "round")
    }
}
