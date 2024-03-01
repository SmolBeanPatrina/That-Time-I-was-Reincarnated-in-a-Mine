# Game Overview:
A mining game where players navigate through a maze-like map filled with challenges, rewards, and
enemies. The goal is to guide the main character, a lost geologist, through the mine to collect
resources while avoiding traps and adversaries.

## Overall Plan
1. **Main Character (Miner):**
   - Moves in cardinal directions, collecting rewards and avoiding obstacles.
   - Collects regular rewards to progress and bonus rewards for additional points.
   - Penalized for encountering punishments or enemies.
   - Game over if caught by a moving enemy or if the score turns negative.

2. **Enemies:**
   - **Moving Enemies:**
     - Move toward the miner's current position.
     - Cannot move through walls or barriers.
     - Losing the game if killed by moving enemies enemies.
   - **Punishments:**
     - Penalties placed on specific cells (eg. spikes on the ground).
     - Triggered when the miner moves to a cell with a punishment, leading to a score deduction.
     - Losing the game if the total score becomes negative due to punishments.

3. **Rewards:**
   - **Regular Rewards:**
     - Must be collected to progress and win the game.
     - Made up of 5 diamonds that can combine into a star shape
   - **Bonus Rewards:**
     - Randomly appear during gameplay, offering higher point values.
     - Disappear after a few ticks.
     - Optional for game completion but adds the final score.

4. **Barriers:**
   - Additional barriers create a maze-like structure, adding complexity to the map.
   - Individual barriers block movement for both the miner and enemies.

5. **Board:**
   - Loaded with an initial map displaying walls, barriers, rewards, punishments, and initial entity positions.
   - Miner must reach the end point after collecting all regular rewards to win.
   - screen shows the current score and the time that has passed since the start of the game. The
     final score and time are shown for a winning player
