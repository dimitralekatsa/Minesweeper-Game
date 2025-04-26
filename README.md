# Minesweeper Game

## Project Overview
This project implements a version of the classic Minesweeper game as part of the "Multimedia Technology" course. The game is developed using JavaFX for the graphical user interface.

## Features Implemented

### Game Mechanics
- Random mine placement on game board
- Revealing cells with left-click
- Flagging potential mines with right-click
- Recursive revealing of adjacent empty cells
- Game timer with countdown functionality 
- Win/loss detection
- New game functionality

### User Interface
- Game board with clickable cells
- Mine counter display
- Timer display
- Restart game button

## Project Structure

The implementation is organized into several key classes:

- **Main.java** - Entry point for the application, sets up the primary stage and creates the game instance
- **MinesweeperGame.java** - Contains the main game logic, manages the game state and UI components
- **MinesweeperBoard.java** - Manages the board representation and cell interactions
- **MinesweeperSquare.java** - Represents individual cells on the board (partially implemented)

## How to Play
1. Launch the application
2. Click the "New Game" button to start a game
3. Left-click to reveal a cell
4. Right-click to place or remove a flag on a suspected mine
5. Try to uncover all non-mine cells before the timer runs out

## Controls
- **Left Mouse Button**: Reveal a cell
- **Right Mouse Button**: Flag/unflag a cell
- **New Game Button**: Start a new game

## Technical Details
The game is built using:
- Java
- JavaFX framework for the GUI
- Object-oriented programming principles

## Building and Running
To build and run the project:
1. Ensure you have Java and JavaFX installed
2. Compile the Java files
3. Run the Main class
