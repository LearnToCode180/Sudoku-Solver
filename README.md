# Sudoku-Solver
Almost all of us know how to play the famous game named **Sudoku**, but only few people knows how to make an automatic solver to this game. In this repository, you will find a Java program made by **Choko Solver** which resolve Sudoku game as a **constraint programming problem**.

# Solution
> ### Variables:
> - We will have 9*9 integer variables, so **81 variables**.
> ### Domains:
> - Each variable will be defined in this domain [1,9]
> ### Constraints:
> 1. Constraint for raw (in every raw, we shouldn't have two variable with the same value).
> 2. Contsraint for columns (in every column, we shouldn't have two variable with the same value).
> 3. Constraint for squares (in every square, we shouldn't have two variable with the same value).

