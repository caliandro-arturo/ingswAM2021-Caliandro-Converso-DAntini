# Masters of Renaissance
## SoftEng project 2020-2021

### Functionality
| Name of the functionality    |Done |
|             :---             |:---:|
| CLI                          |  √  |
| GUI                          |  √  |
| Multiple matches             |  √  | 
| Persistence                  |  X  |
| Parameter editor             |  X  |
| Local match                  |  X  |
| Resilience to disconnections |  √  |

### Requirement
- JRE 16 or above

### Run instructions
- Server: type `java -jar server.jar [port]` (default port: 1234)
- Client: JavaFX 16 libraries must be added as modules. RunClient batch contains
the VM options to add them automatically.
  - Cmd.exe: run the bat or type `RunClient.bat [CLI-GUI]`;
  - Powershell: type `cmd.exe /c 'RunClient.bat' [CLI-GUI]`;
  - Linux: type `java -p JavaFX --add-modules ALL-MODULE-PATH -jar client.jar [CLI]`
    
GUI is only supported on Windows.

### Design and implementation choices

Each player, after setting his nickname, has to choose between:
- create a new game;
- join an already existing match.

This implementation allows joining players and matches into a map, that allows the server to lead a player, who was
previously disconnected, to the game he was part of (except when the game mode was single player).

It is assumed that each player has a nickname of his own, and that when a player leaves the game, whoever wants to
join the server using that nickname is the same player who was disconnected before, and therefore is eventually 
re-admitted to the game.

### Contributors:

[Arturo Caliandro (10610910)](https://github.com/caliandro-arturo)

[Vincenzo Converso (10625358)](https://github.com/ConversoVincenzo)

[Marco D'Antini (10603556)](https://github.com/DantiniMarco)

### License:

This software is licensed under MIT license. The name "Masters of Renaissance" and game assets belong to Cranio Creations srl and his authors.

[Masters of Renaissance official page](https://craniointernational.com/products/masters-of-renaissance/)
