# TAxE Game

**This is the repository for the code of the TAxE Game developed by the EEP team for the Software Engineering Project module at the Department of Computer Science, University of York.**

It is a train simulator game that can be played by two players over the Internet.

## Run the game

You can run the Game on virtually any PC with the Java Runtime Environment version 7 or higher. You can download the JRE for free at [java.com](http://java.com). The game has been tested to work on the Windows and Linux PCs at the Department of Computer Science, University of York.

1. Download [taxe-game.jar](https://raw.githubusercontent.com/SEPR-EEP/taxe-game/master/taxe-game.jar) and open it;
2. If a security prompt is shown, click "Allow" - we're trustworthy;
3. Click OK to choose default game server and run the game;
4. Play!

### Host your own server

By default, the Game tries to connect to an instance of the Game server that can be used for testing.

To host your own server, you'll need a PC running either Linux or Mac OS with NodeJS installed. [You can download NodeJS from here](http://nodejs.org/).

To run the server, type in a terminal:
```bash
git clone https://github.com/SEPR-EEP/taxe-server
cd taxe-server
node server.js
```

This will start a game server listening on port `8042`. Make sure this port is reachable from the Internet. You can then type `http://<your-ip>:8042` to connect to your server when starting the Game.

**Note**: In some Linux distributions such as Ubuntu and its derivatives, you may need to type `nodejs` instead of `node`.

