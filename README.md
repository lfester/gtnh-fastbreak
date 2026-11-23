# GTNH Fast Break

A client-only Forge 1.7.10 mod that removes the delay between breaking blocks.
It does not change block hardness, tool speed, or durability. The server still
decides whether a block is actually broken. Check server rules before using it.

## Build

With JDK 17 installed, run `./gradlew build` (or `gradlew.bat build` on
Windows). The installable, self-contained jar is
`build/libs/gtnh-fastbreak-1.0.0.jar`. Do not install the `-plain` jar.

Put the jar in the client's `mods` folder and restart Minecraft. The mod is not
needed on the server. Disable or remove the jar to turn the effect off.

The mod uses Forge's client tick event and supports both runtime SRG and
development field names; it has no Minecraft mixin dependency.
