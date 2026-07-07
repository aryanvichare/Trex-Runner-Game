# TRex Runner Game 🦖

An object-oriented Java implementation of the classic Google Chrome "No Wi-Fi" offline T-Rex Runner game, built using the [Processing](https://processing.org/) graphics library.

---

## 🎮 Overview & Features

This project recreates the famous endless side-scrolling runner game with added twists and custom gameplay mechanics:
- **Endless Side-Scrolling Action**: Run across an infinite platform as game speed progressively increases.
- **Physics & Animations**: Gravity-based jumping and smooth sprite animation transitions for the T-Rex.
- **Custom Mechanics (Fireballs!)**: In addition to jumping over obstacles, your T-Rex can launch aerial fireballs (`f` key) to interact with obstacles!
- **Dynamic Atmosphere**: Includes animated clouds, looping ground tiles, and speed scaling over time.
- **Score Tracking & Game Over States**: Tracks elapsed time and displays visual Game Over feedback when collisions occur.
- **Audio Support**: Integrated sound playback utilities using the Java Sound API (`javax.sound.sampled`).

---

## 🏗️ Object-Oriented Architecture (Inheritance)

The codebase is structured around Object-Oriented Programming (OOP) principles, specifically demonstrating **inheritance** and **polymorphism**:

```
                  ┌────────────────────┐
                  │    GameObject      │
                  └─────────┬──────────┘
                            │
      ┌──────────┬──────────┼──────────┬──────────┐
      ▼          ▼          ▼          ▼          ▼
   ┌──────┐  ┌────────┐  ┌──────┐  ┌────────┐  ┌──────┐
   │ TRex │  │ Cactus │  │Ground│  │ Cloud  │  │Fireball│
   └──────┘  └────────┘  └──────┘  └────────┘  └──────┘
```

- **`GameObject` (Superclass)**: Serves as the base entity class. It encapsulates common properties such as 2D coordinates ($x, y$), dimensions ($width, height$), sprite textures (`PImage`), movement methods, screen boundary detection, and 2D axis-aligned bounding box collision detection (`isCollidingWithCactus`, `isOverlapping`).
- **Subclasses**:
  - **`TRex`**: Manages player jump physics, gravity calculations, animation frame toggling (running, ducking, dead states), and player life status.
  - **`Cactus`**: Ground obstacles that scroll left across the screen and dynamically reset their position when exiting the screen bounds.
  - **`Ground`**: Looping floor tiles that seamlessly reset to create the illusion of an infinite landscape.
  - **`Cloud`**: Scrolling background sky decorations with randomized horizontal velocities.
  - **`Fireball`**: Projectile entities launched by the player.
  - **`Dot`**: Auxiliary geometric entities rendered on screen.
- **Controllers & Utilities**:
  - **`App`**: Extends Processing's `PApplet`; serves as the main game engine loop, canvas controller ($1000 \times 400$), collision orchestrator, and keyboard input handler.
  - **`Music`**: Audio utility utilizing `javax.sound.sampled` for loading and streaming sound files.
  - **`Time`**: Elapsed time tracker using Java's `Timer` and `TimerTask`.

---

## 📂 Project Structure

```text
Trex-Runner-Game/
├── README.md
└── TRexRunner_Inheritence/
    ├── assets/          # Game sprites, backgrounds, and PNG textures
    ├── bin/             # Compiled Java bytecode (.class files)
    └── src/             # Java source code
        ├── App.java         # Main Processing application & game loop
        ├── GameObject.java  # Superclass for all game entities
        ├── TRex.java        # Player character physics & animation
        ├── Cactus.java      # Ground obstacle entity
        ├── Ground.java      # Scrolling floor tile entity
        ├── Cloud.java       # Background decoration entity
        ├── Fireball.java    # Aerial projectile entity
        ├── Dot.java         # Geometric helper entity
        ├── Music.java       # Audio playback utility (Java Sound API)
        └── Time.java        # Elapsed timer utility
```

---

## 🕹️ Controls

| Key / Input | Action |
| :--- | :--- |
| **Spacebar** (` `) | Jump over obstacles |
| **`f` Key** | Shoot a Fireball projectile |

---

## 🚀 How to Run the Program

### Prerequisites
1. **Java Development Kit (JDK 8 or higher)** installed on your machine.
2. **Processing Library**: The [Processing Java library](https://processing.org/download) (`core.jar`) is required to compile and run the application.

### Setup & Execution in an IDE (Eclipse / IntelliJ IDEA)
1. **Clone the repository**:
   ```bash
   git clone https://github.com/aryanvichare/Trex-Runner-Game.git
   ```
2. **Import the Project**:
   - Open your IDE and import `TRexRunner_Inheritence` as an existing Java project.
3. **Configure Dependencies**:
   - Add Processing's `core.jar` to your project's build path / classpath dependencies.
4. **Configure Asset Paths**:
   - *Note*: Some image file paths in `src/App.java` and `src/TRex.java` contain local absolute file paths by default. Before launching, update the path strings (e.g., `file_TRex`, `file_cactus`, `file_fireball`, etc.) to point to your workspace's `TRexRunner_Inheritence/assets/` directory or change them to relative paths.
5. **Run the Game**:
   - Locate `src/App.java` and execute it as a Java Application.
