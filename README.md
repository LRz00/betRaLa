# ⚽️🏀️🏈️⚾️ BetRALA .
### A SPORTS BETS MANAGING API. 
**Work in Progress**

---

## 🪛️ISSUES THAT NEED FIXING: 
- Attempt at implementing authentication/login did **not** work. For details check *faulty-security* branch for details.
- Current Bet methods don't check if user got the score correct, only the winner.
- Current Game methods don't account for draws.
- Code variables and methods names not standardized(variates between uses of portuguese and english)(change to be made so everything is in *english*)
- Code variables and methods still following pre-refactor pattern(Changes needed to any "Match" use to become "Game", "Value" to become "Amount", and "Result" to become "Score")
- Authentification not implemented.
- @ControllerAdvice for Exception handling not created.

## ✅️TO-DO LIST:
- **Fix implementation of authentification/login methods**
---
## 👷️SERVICE METHODS:
**USER SERVICE METHODS:**
- 🔓️*create(User usuario)*: creates a new user(email and CPF must be unique).
- 🔓️*getAll*: returns a list of all users.
- 🔓️*delete(User user)*: Deletes an user account after checking if theres any money in their account or if there's any debt.
- 🔓️*findByCpf(Long cpf)*: returns a User with the matching cpf.
- 🔓️*updateSaldo(Float value, Long cpf)*: adds or subtracts value to the account of a User with the matching Cpf.
- 🔓️*update(User user)*: saves a user to the repository.

**GAME SERVICE METHODS:**
- 🔓️*createMatch(Game game)*: creates and saves a new Game to the repository.
- 🔓️*definirResultado(Long jogoId, String resultado, String winner)*: To be used once the match is over, this method defines who won, the score, and saves it to the repo.
- 🔓️*isMatchOver(Long matchId)*: checks if a winner has been added to a match.
- 🔓️*getMatch(Long id)*

**BET SERVICE METHODS:**
- 🔓️*createNewBet(Long matchId, Long userID, String winner, float value)*: creates a new bet for the user of matching id. Verifies if the user has enough money in their account. Verify if chosen winner is playing the match of matching id.
- 🔓️*getBet(Long betId)*: returns the betch of matching id.
- 🔓️*isBetWon(Long betId)*: checks if the bet was won by comparing the selected winner to the match's actual winner.
- 🔓️*settleAllBets(Game match)*: gets all the bets made on the match, divides it into winners and losers and calls methods to handdle the money involved. 
- 🔒️*redistributeMoney(List winners, List losers)*: gets the total of money that was lost and adds a percentage of it to the winning user's balance.
- 🔒️*subtractFromLosers(List losers)*: removes the ammount of each bet lost from the user's balance
- 🔒️*calculatePercentageWon(double totalWon, double totalLost)*: calculates the % that will be given to the winning losers.


## 🔗️CONTROLLER/ENDPOINTS:
**User**: */user*
 - findByCpf: /{cpf} (GET)
 - create: /{cpf} (POST)
 - delete: /{cpf} (DELETE)
 - updateSaldo: /updateSaldo (PUT)
 - getAllUsers: /all (GET)

---

## 💻️VIEW:
- HTML/CSS/JS avaliable for account creation via web browser.
