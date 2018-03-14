# Homework of JavaDec2017

## Homework 1

### Rectangle intersection area
Uses Vertex and Rectangle classes. Public method `Rectangle intersection(Rectangle rec)` returns rectangle on witch you can call `double area()`.

### HexGrid
Uses Grid and HexGrid classes. Constructor uses radius parameter. Coordinates start from center of Grid and are cubic. Implemented two methods `int straightDistance(Hex hex1, Hex hex2)` which return straight distance from one Hex to another and `List<Hex> straightPath(Hex hex1, Hex hex2)` which return list of Hexes from hex1 to hex2.

For both tasks where are tests (`gradle test`)

## Homework 2

### Find fake coin from 12 coins by 3 scaling
Uses Coin and Coins classes. Create Coins instance and add 12 Coin instances (one with different weigth). Method `Coin fake()` return fake coin.

### Print "spiral" matrix
Uses SquareMatrix class. Method `String toStringSpiral()` return String representation of matrix from center to last element by spiral.

For both tasks where are tests (`gradle test`)

## Homework 3

### Shell
Shell can be launched via `gradle --console plain runShell`, type help to get list of commands.

### Mp3Parser

Parses mp3 files from chrome and firefox. Can be launched via `grade parseMp3 -Dpath="c:\mp3"` or `gradle parseMp3`.

No test for these two.

## Hackerrank

Screenshot of hackerrank progress is located in the root of this repository.
Source code for "Ceacking the coding interview" is located in this repository: https://github.com/sixtead/cracking_the_coding_interview