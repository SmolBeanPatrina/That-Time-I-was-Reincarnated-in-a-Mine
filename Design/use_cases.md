# Use cases

Creating use cases that describe the game's behaviour from the player's perspective.

## Preliminary

Listing actions made by specific actors.

**Actor**: Geologist (MC/main character/player)

Functions:
- Move into a wall/barrier
- Collect a regular reward
- Collect a bonus reward
- Land on a punishment
- Encounter a moving enemy (lose)
- Reach end cell with all regular rewards (win)


## Formal

Creating use cases.

Using this template:  
**Use case:**  
**Actor:**  
**Goal in context:**  
**Precondition:**  
**Trigger:**  
**Scenario:**  
**Exception:**  

**Use case: Collect a regular reward (diamond)**  
**Actor:** Geologist (MC)  
**Goal in context:** To eventually collect all regular rewards and win the game.  
**Precondition:** The MC has spawned at the mine entrance, and a regular reward has spawned onto the map.  
**Trigger:** The player decides to move.  
**Scenario:**  
1. MC moves onto the reward's cell.
2. The reward is claimed (removed from map).
3. The reward amount is added to MC's score.
4. The screen displayes the new score.

**Exceptions:**  
1. MC encounters a moving enemy before reaching a reward - see use case **Encounter a moving enemy**
2. MC lands on a punishment before reaching a reward - see use case **Land on a punishment**
3. MC is blocked by a wall or barrier - see use case **Move into a wall/barrier**

**Use case: Collect a bonus reward (coin)**  
**Actor:** Geologist (MC)  
**Goal in context:** To earn a higher score.  
**Precondition:** A bonus reward has randomly spawned onto the map.  
**Trigger:** The player decides to move.  
**Scenario:**  
1. MC moves onto the reward's cell.
2. The reward is claimed (removed from map).
3. The reward amount is added to MC's score.
4. The screen displayes the new score.  

**Exceptions:**  
1. Bonus reward disappears before reaching it.
2. MC encounters a moving enemy before reaching a reward - see use case **Encounter a moving enemy**
3. MC lands on a punishment before reaching a reward - see use case **Land on a punishment**
4. MC is blocked by a wall or barrier - see use case **Move into a wall/barrier**

**Use case: Encounter a moving enemy**  
**Actor:** Geologist (MC)  
**Goal in context:** To lose the game.  
**Precondition:** A moving enemy has spawned onto the map.  
**Trigger:** The player decides to move or the moving enemy begins to move.  
**Scenario:**  
1. MC moves onto the moving enemy's cell.
2. MC collides with enemy and "dies".
3. The game displays a lose screen with the final score and time (game is over).  

**Exceptions:**  
1. The moving enemy catches up to the MC instead of the MC catching up to the enemy - game is over.
2. MC lands on a punishment before encountering a moving enemy - see use case **Land on a punishment**
3. MC is blocked by a wall or barrier - see use case **Move into a wall/barrier**

**Use case: Land on a punishment (spike/trap/etc.)**  
**Actor:** Geologist (MC)  
**Goal in context:** To penalize score.  
**Precondition:** A punishment is spawned onto the map.  
**Trigger:** The player decides to move.  
**Scenario:**  
1. MC moves onto a punishment's cell.
2. MC "takes damage".
3. The punishment's penalty is taken from MC's score.
4. The screen displays the new score.  

**Exception:**  
1. The punishment's penalty causes MC's score to be negative - the MC "dies" and the game displays a lose screen (game is over)
2. MC lands on a punishment before encountering a moving enemy - see use case **Land on a punishment**
3. MC is blocked by a wall or barrier - see use case **Move into a wall/barrier**

**Use case: Move into a wall/barrier**  
**Actor:** Geologist (MC)  
**Goal in context:** To face structural obstacles.  
**Precondition:** The mine has spawned.  
**Trigger:** The player decides to move.  
**Scenario:**  
1. MC tries moving into a wall or barrier's cell.
2. MC is "blocked" (cannot move onto that cell).  

**Exception:**  
1. MC moves in a different direction away from the wall or barrier and becomes "unblocked".

**Use case: Reach exit with all regular rewards collected**  
**Actor:** Geologist (MC)  
**Goal in context:** To win the game.  
**Precondition:** Collected all regular rewards.  
**Trigger:** The player decides to move towards the end.  
**Scenario:**  
1. MC approaches barred exit.
2. MC "unlocks" the passage.
3. MC moves through the passage.
3. The screen displays the final score and time.  

**Exception:**  
1. The player chooses to collect more bonus rewards - see **Use case: Collect a bonus reward (coin)**
