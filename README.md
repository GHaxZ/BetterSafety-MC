# BetterSafety-MC
## A Minecraft Paper Plugin, that includes simple moderation functionality.

_**Currently included features:**_
- `Verification system`

  > New players have to enter a verification code once, before being able to play.
  
<br>  

_Features can be disabled/enabled in the plugin data folder in the "config.yml" file._

<br>

_**Currently included commands:**_
- `/cc <global>`

  > Clears the chat for the player running the command. Players with OP rights can also add the optional global argument, to clear the chat server-wide.
- `/kickall`

  > Kicks all players from the server, except the player who ran the command. Can be run by players with op rights.
- `/safemode`

  > Stops new players from connecting to the server. Can be run by players with op rights.
- `/panic`

  > Combination of /kickall and /safemode. Can be run by players with op rights.
- `/togglecommands`

  > Disables all commands. To enable commands again, run /togglecommands again. Can be run by players with op rights.
- `/togglechat`

  > Disables the chat on the entire server. To enable the chat again, run /togglechat again. Can be run by players with op rights.

## _Feel free to contribute any new features, or just improve already existing code._
  
### _To edit the source code yourself, just download this repository and open it in your code editor. Also, Maven is required as a build tool._
