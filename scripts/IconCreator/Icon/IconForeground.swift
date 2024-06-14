struct IconForeground: Foregroundable {
    let dimensions = Traits.shared.dimensions
    var size: Double { dimensions.iconSize }

    var foreground: Foreground {
        Foreground(size: size) {
            M(x: -29.5, y: -53)
            MoonAndStars(x: -33.5, y: -48)
            BackgroundBuildings(x: -32, y: -53)
            MainBuildings(x: -46, y: -47)
            TV(x: -45, y: -39)
        }
    }
}
