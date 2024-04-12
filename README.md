# ⚽️🏀️🏈️⚾️ BetRALA .
### A SPORTS BETS MANAGING API. 
**Work in Progress**

---

## 🪛️ISSUES THAT NEED FIXING: 
- Current Bet methods don't check if user got the score correct, only the winner.
- Current Game methods don't account for draws.
- Authentification not implemented.
- @ControllerAdvice for Exception handling not created.
- Currently using Java 8. Change to newer version might be advised. 

## ✅️TO-DO LIST:
- **Implement authentification/login methods**
---
## 👷️SERVICE METHODS:
**USER SERVICE METHODS:**
- 🔓️*create(User user)*: creates a new user(email and CPF must be unique).
- 🔓️*getAll*: returns a list of all users.
- 🔓️*delete(User user)*: Deletes an user account after checking if theres any money in their account or if there's any debt.
- 🔓️*findByCpf(Long cpf)*: returns a User with the matching cpf.
- 🔓️*updateBalance(Float amount, Long cpf)*: adds or subtracts amount to the account of a User with the matching Cpf.
- 🔓️*update(User user)*: saves a user to the repository.

**GAME SERVICE METHODS:**
- 🔓️*createMatch(Game game)*: creates and saves a new Game to the repository.
- 🔓️*definirResultado(Long jogoId, String score, String winner)*: To be used once the match is over, this method defines who won, the score, and saves it to the repo.
- 🔓️*isMatchOver(Long matchId)*: checks if a winner has been added to a match.
- 🔓️*getMatch(Long id)*

**BET SERVICE METHODS:**
- 🔓️*createNewBet(Long matchId, Long userID, String winner, float amount)*: creates a new bet for the user of matching id. Verifies if the user has enough money in their account. Verify if chosen winner is playing the match of matching id.
- 🔓️*getBet(Long betId)*: returns the betch of matching id.
- 🔓️*isBetWon(Long betId)*: checks if the bet was won by comparing the selected winner to the match's actual winner.
- 🔓️*settleAllBets(Game game)*: gets all the bets made on the match, divides it into winners and losers and calls methods to handdle the money involved. 
- 🔒️*redistributeMoney(List winners, List losers)*: gets the total of money that was lost and adds a percentage of it to the winning user's balance.
- 🔒️*subtractFromLosers(List losers)*: removes the ammount of each bet lost from the user's balance
- 🔒️*calculatePercentageWon(double totalWon, double totalLost)*: calculates the % that will be given to the winning losers.


## 🔗️CONTROLLER/ENDPOINTS:
**User**: */user*
 - findByCpf: /{cpf} (GET)
 - create: /{cpf} (POST)
 - delete: /{cpf} (DELETE)
 - updateBalance: /updateBalance (PUT)
 - getAllUsers: /all (GET)

---

## 💻️VIEW:
- HTML/CSS/JS avaliable for account creation via web browser.
