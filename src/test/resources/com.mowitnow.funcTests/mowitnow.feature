Feature: As a gardener, I want to run my mowers by giving them a conf file

Scenario Outline: Run initial test cases
    Given a lawn of <width> width and <height> height
    And a mower initially starting at <mowerStartingEasting>,<mowerStartingNorthing> and oriented <mowerStartingOrientation>
    When I run the mower with <instructions>
    Then the mower should finish its job at <mowerEndingEasting>,<mowerEndingNorthing> and oriented <mowerEndingOrientation>

Examples:
|width  |height |mowerStartingEasting   |mowerStartingNorthing  |mowerStartingOrientation   |instructions   |mowerEndingEasting     |mowerEndingNorthing|mowerEndingOrientation |
|5      |5      |1                      |2                      |N                          |GAGAGAGAA      |1                      |3                  |N                      |
|5      |5      |3                      |3                      |E                          |AADAADADDA     |5                      |1                  |E                      |
|3      |3      |1                      |1                      |N                          |AAAAAAAAA      |1                      |3                  |N                      |
|3      |3      |1                      |1                      |N                          |DAAAAAAAA      |3                      |1                  |E                      |
|3      |3      |1                      |1                      |N                          |GAA            |0                      |1                  |W                      |
|3      |3      |1                      |1                      |N                          |DDAAAAAAA      |1                      |0                  |S                      |
