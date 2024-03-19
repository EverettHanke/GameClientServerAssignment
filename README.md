A Simple Travel Game with Player to Server Access Protocol (Travel Game)
Author: Everett Hanke
Client Request	Server Response	Description
Reflect n
Ex:
BALANCE 8 (requests health and stamina for player account number 8)	n and the balance
Ex:
8 100 44
(response that player 8 has 100hp and 44 stamina)	Get the Player n health and current stamina allowing the players to make an educated guess on whether to heal or keep pushing forward.

Travel n d	N for player character.
D for distance explored.	Travel the player a select distance of the players choosing. Let them randomly find healing items or get hurt.
Heal n	n for player character. Creates random health pool to heal from.	Heal for random amount a into player n. also increases stamina.
Hurt n	n for player character targeted. Creates random damage number remove from health pool.	Creates a random damage amount the player has no control over. The damage amount reduces the health a from player n
QUIT	None	Quit the connection

Code for the client and server is under Week 6: BankClientServerExample.zip

For part 1 of this project, you will first design and submit a protocol of your choosing.
And then you also want to choose a data structure for the server as well… and include that in your protocol design document.

Data Structures
Game Instance class
Class GameAccount will have the following private fields:
●	UniqueClientnumber
●	Health
●	Stamina
And the following public methods:
●	GameCharacter(int UniqueClinetNumber);	// creates an player character with an unique client num, and a set health and stamina.
●	public void hurt(int amount); //take damage
●	public void heal(int amount); //regain health and stamina
●	public double reflect(); //allows the player to reflect on how they feel. Shows health and stamina. Think taking a breather.
●	Public void travel(int distance)//allows player to travel set distance of their choice. Stamina dependent if they travel more distance then their stamina, chances of getting hurt increases. Playing into the odds of biting off more than you can chew can cause your character to go below 0 health (die)
	
Player Lobby Collection
Array of PlayerAccounts (size 10) - account numbers are 0-9.


Point of the Game
To see if the players can get to their goal line in as little turns (highest risks) as possible. 



