# Slicer
Resource pack migration tool for Minecraft: Java Edition.
Two versions of the tool are available for migrations to both 1.20.2 & 1.20.5 pack formats. 
The migrations automated here are not exhaustive, so some manual work may still be required. See the release changelog for the relevant version for more information.

This tool is provided as reference code to help update existing resource packs for Minecraft: Java Edition, and as such we are not accepting contributions or actively maintaining it. However, we may update or revisit this in the future. Forking is welcome, and you are free to use the code as you see fit - for more information see the [provided license](LICENSE).

## Usage
Pre-built jars are available through the [Releases](https://github.com/satanicantichrist/slicer-gui/releases) page. An installation of Java 17 or higher is required.

Slicer GUI
- Open the `slicer.bat` file 
- Select input `dir or zip` is the root of your resource pack (directory or zip containing an `assets` directory)	- Select input `dir or zip`.
- Select output `dir` will be filled with all processed texture files.
- Select leftover `dir` is optional location that will be filled with copies of source images with added highlights for areas that were migrated.
- Press "Run" the tool will migrate the Resource pack.
The highlighted areas were processed by the tool and are used by the Vanilla game, the rest is not required in the pack.

or

Slicer CLI
`<input dir or zip> <output dir> [<leftover dir>]`
- `input dir or zip` is the root of your resource pack (directory or zip containing an `assets` directory)	- Select input `dir or zip`
- `output dir` will be filled with all processed texture files	- select output `dir`
- `leftover dir` is optional location that will be filled with copies of source images with added highlights for areas that were migrated	- select leftover `dir` is optional location that will be filled with copies of source images with added highlights for areas that were migrated
  - The highlighted areas were processed by the tool and are used by the Vanilla game, the rest is not required in the pack
 ---
![image](https://github.com/satanicantichrist/slicer-gui/assets/72868272/b14abfb1-da29-44fb-97d6-2cf6208d2823)

