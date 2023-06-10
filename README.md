# BetterSafety-MC
## A Minecraft Paper Server Plugin that includes simple moderation functionality.

_Currently included features:_
- Verification system
  New players have to enter a verification code once, before being able to play
  
Features can be disabled/enabled in the plugin data folder in the "config.yml" file.

_Currently included commands:_
- /cc <global>
  Clears the chat for the player running the command. Players with OP rights can also add the optional global argument to clear the chat server wide.
- /kickall
  Can be run by players with op rights. Kicks all players from the server excpet the player who ran the command.
- /safemode
  Can be run by players with op rights. Stops new players from connecting to the server.
- /panic
  Can be run by players with op rights. Combination of /kickall and /safemode
- /togglecommands
  Can be run by players with op rights. Disables all commands. To activate commands again run /togglecommands again.
- /togglechat
  Can be run by players with op rights. Disables the chat on the entire server. To the chat again run /togglechat again.
  
Feel free to contribute any new features or just improve already existing code.
  
To edit the source code yourself just download this repository and open it in your code editor.
 
# Downloads (currently only available for 1.19.4):

version 1.1 (10. June 2023): https://drive.google.com/uc?export=download&id=1MNc4kynfQ1WrFjYW7AuGayI3ZO0ud1HP
  
version 1.0 (9. June 2023): https://drive.google.com/uc?export=download&id=1KPmPMF7e3B-FMhGf6WorwJoVjIOoInk
