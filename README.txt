#Valve Resource Editor
##Ayden Best
## SE330 Project

###Project Overview
This project will consist of a GUI, resource file decompiler, resource file compiler, and a vsnd-soundfile converter for Source2 audio resources
Valve originally created Source2 as a gaming and animation graphical engine, but provided no SDK or documentation on the engine. Because of this, there is no way to decompile projects on the Source2 engine.
With this tool, we will be able to disassemble and reassemble compiled Source2 resource files and modify some of the contents on them. I will demonstrate this by replacing audio files in a popular Valve game that runs on Source2

###Steps this tool automates:
- Decompile pak01_dir.vpk to view the file structure
- Decompile script files that point to a resource call event
- Create a file tree that replicates the file structure in pak01_dir.vpk
- Add new files to also include in the new pak01_dir.vpk file
- Open Valve's official scene editor and render all new files to convert to the proper content (ex. .wav -> .vsnd)
- Run 3rd party compiler to force rendered files into a new pak01_dir.vpk
- Lots and lots of compilcated file management and migration

###TODO:
- Create command to Decompile pak01_dir.vpk
- Investigate whether pak01_dir.vpk requires a .vsnd sound file to create a compiled sound
- Create fileManager class to handle directories and moving files around
- Investigate whether we can control CFGeditor to compile mp3s/WAVs