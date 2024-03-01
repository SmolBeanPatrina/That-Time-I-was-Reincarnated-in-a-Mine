# CMPT276F23_group6 - Patrina Cheung, Scott Renegado, Ethan Price

**Group6 Presenting:**  
*MINE GAME: That Time I Was Transported into A Mine*  

You've suddenly been transported from your home town to an underground mine! The only way to escape, it seems, is to collected all 5 star fragments and exit out the locked passageway. Beware! There are slimes and magical traps laid down within the vicinity. Might as well pick up this world's currency, too. It'll take quick moves and a swift perservence to make it through!

## How to build
First, ``cd`` to ``minegame/``.  

To build and test the game, run ``mvn compile`` then ``mvn test``, or just run ``mvn package``. The game JAR file ``minegame-1.0-SNAPSHOT.jar`` will be in the ``target/`` folder.

Once the game JAR file is built, run the game using ``mvn exec:java``.  

To generate documentation, run ``mvn javadoc:javadoc``. All documentation are html files that will be in ``target/site/apidocs/``, for e.g. ``allclasses-index.html``.  

To generate a coverage test report, run ``mvn jacoco:prepare-agent test install jacoco:report`` to generate the report ``target/site/jacoco/index.html``.  

## How to play
Use WASD to move! Press ``ENTER`` to navigate through the menus. Collect all 5 star fragments to unlock the exitway and any bonus coins along the way. Colliding with a slime results in instant ``GAME OVER``! Magical traps stun you and deduct your score - a score below zero also results in a loss!

We created a **video demo** that can be watched [here](https://youtu.be/FxjaYHOHhbo). 

## Sprites
All sprites were created by Group 6.
