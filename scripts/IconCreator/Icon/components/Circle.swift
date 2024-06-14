struct Circle: Tag {
    var x: Double
    var y: Double
    
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }
        
    var body: [any Tag] {
        Path()
            .d(CirclePathData(x: x, y: y))
            .stroke(width: 1)
            .strokeLine(cap: "butt")
            .strokeLine(join: "round")
            .stroke(color: "#000000")
    }
}
