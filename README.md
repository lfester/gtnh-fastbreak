# GTNH Fast Break

A client-only mod that removes the delay between breaking blocks.
It does not change block hardness, tool speed, or durability. The server still
decides whether a block is actually broken. Check server rules before using it.

## Compatibility

- **Modpack:** GT New Horizons 2.9.
- **Minecraft and Forge:** Minecraft 1.7.10 with Forge 10.13.4.1614.
- **Java:** Use the Java runtime recommended for your GTNH installation. The mod
  has been loaded with the LWJGL 3 pack on Java 25; it does not require Java 25
  on its own.
- **Dependencies:** No extra mods or Kotlin installation are needed.

## Download and install

Download [gtnh-fastbreak-1.0.0.jar](releases/gtnh-fastbreak-1.0.0.jar), put it
in the client's `mods` folder, and restart Minecraft. The mod is not needed on
the server. Disable or remove the jar to turn the effect off.

## Build from source

JDK 17 is needed only to build from source. Run `./gradlew build` (or
`gradlew.bat build` on Windows). The installable, self-contained jar is
`build/libs/gtnh-fastbreak-1.0.0.jar`. Do not install the `-plain` jar.
