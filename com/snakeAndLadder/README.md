# Snake and Ladder Game

A classic Snake and Ladder board game implementation in Java with comprehensive test coverage.

## 🎮 Game Overview

This is a console-based Snake and Ladder game where players take turns rolling dice to move their pieces from position 0 to 100. The game features:

- Multiple players support
- Snakes that send players backward
- Ladders that help players climb forward
- Automatic win detection when a player reaches exactly 100

## 📁 Project Structure

```
snake-and-ladder-game/
├── pom.xml                          # Maven configuration
├── README.md                        # Project documentation
├── src/
│   ├── main/
│   │   └── java/
│   │       ├── Main.java            # Game entry point
│   │       ├── Game.java            # Game logic
│   │       └── Entities/
│   │           ├── Player.java      # Player interface
│   │           └── PlayerImp.java   # Player implementation
│   └── test/
│       └── java/
│           ├── PlayerImpTest.java   # Player unit tests
│           ├── GameTest.java        # Game unit tests
│           ├── GameIntegrationTest.java # Integration tests
│           ├── EdgeCaseTest.java    # Edge case tests
│           ├── TestUtils.java      # Test utilities
│           └── TestRunner.java     # Test suite runner
└── Problem_Statement.md             # Original problem statement
```

## 🚀 Getting Started

### Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

### Running the Game

1. **Clone the project**
   ```bash
   cd /path/to/snakeAndLadder
   ```

2. **Compile the project**
   ```bash
   mvn compile
   ```

3. **Run the game**
   ```bash
   mvn exec:java
   ```

### Running Tests

1. **Run all tests**
   ```bash
   mvn test
   ```

2. **Run specific test class**
   ```bash
   mvn test -Dtest=PlayerImpTest
   ```

3. **Run tests with coverage**
   ```bash
   mvn clean test jacoco:report
   ```

## 🎯 How to Play

1. **Start the game** by running the main class
2. **Enter the number of snakes** and their positions (start > end)
3. **Enter the number of ladders** and their positions (end > start)
4. **Enter the number of players** and their names
5. **Watch the game unfold** as players take turns rolling dice

### Game Rules

- Players start at position 0
- Each turn, a player rolls a dice (1-6) and moves forward
- Landing on a snake's head sends the player to the snake's tail
- Landing on a ladder's bottom moves the player to the ladder's top
- Players cannot move beyond position 100
- First player to reach exactly 100 wins

## 🧪 Test Coverage

The project includes comprehensive test coverage:

- **Unit Tests**: Individual component testing
- **Integration Tests**: Complete game flow testing
- **Edge Case Tests**: Boundary conditions and error scenarios
- **Test Utilities**: Helper methods for testing

### Test Statistics

- **Total Tests**: 34
- **Coverage**: Player creation, game logic, movement mechanics, winning conditions
- **Test Types**: Unit, Integration, Edge cases

## 🔧 Technical Details

### Core Classes

- **Main**: Entry point and game setup
- **Game**: Contains game logic and movement rules
- **Player**: Interface defining player behavior
- **PlayerImp**: Concrete player implementation

### Key Features

- Random dice rolling (1-6)
- Snake and ladder position mapping
- Multi-player turn management
- Win condition detection
- Input validation for snakes and ladders

## 📝 Input Format

```
<number_of_snakes>
<snake_start> <snake_end>
...
<number_of_ladders>
<ladder_start> <ladder_end>
...
<number_of_players>
<player_name_1>
<player_name_2>
...
```

### Example Input

```
2
99 78
95 75
3
2 38
7 14
2
Alice
Bob
```

## 🐛 Known Issues & Limitations

- Console-based interface (no GUI)
- Random dice rolls (no manual dice input)
- No game save/load functionality
- Limited input validation

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Run the test suite
6. Submit a pull request

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

## 🙏 Acknowledgments

- Classic Snake and Ladder game rules
- JUnit 5 testing framework
- Maven build system
