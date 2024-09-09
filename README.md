# slicer
Resource pack migration tool for Minecraft: Java Edition

Three versions of the tool are available for migrating to subsequent pack formats:
* a 1.14 version for splitting particle, painting and entity effect textures
* a 1.20.2 version for splitting the remaining GUI sprites and moving other UI textures (mainly Realms)
* a 1.20.5 version for splitting map decorations

The migrations automated here are not exhaustive, so some manual work may still be required; see the release changelog for the relevant version for more information.

This tool is provided as reference code to help update existing resource packs for Minecraft: Java Edition, and as such we are not accepting contributions or actively maintaining it. However, we may update or revisit this in the future. Forking is welcome, and you are free to use the code as you see fit - for more information see the [provided license](LICENSE).

## Usage
Pre-built jars are available through the [Releases](https://github.com/Mojang/slicer/releases) page. An installation of Java 17 or higher is required.

`<input dir or zip> <output dir> [<leftover dir>]`
- `input dir or zip` is the root of your resource pack (directory or zip containing an `assets` directory)
- `output dir` will be filled with all processed texture files
- `leftover dir` is an optional location that will be filled with copies of source images with added highlights for areas that were migrated
  - The highlighted areas were processed by the tool and are used by the vanilla game, the rest is not required in the pack
