# Mein Taschenrechner Projekt
## Was der Rechner kann

- Normale Operationen: +, -, *, /
- Extra: % (Modulo) und ^ (Potenz mit Math.pow)
- Schützt vor Division durch 0 (und Modulo durch 0)
- **Einfache Klammern**: Wenn du eine Zahl eingibst, kannst du stattdessen (zahl operator zahl) schreiben, z.B. (20 - 10). Das wird zuerst gerechnet und das Ergebnis genommen.
- **History**: Speichert die letzten 10 Berechnungen. Mit "h" als Operator kannst du sie dir ansehen.
- Läuft in einer Schleife, bis du "q" eingibst.

## Wie du ihn startest

- Projekt in IntelliJ öffnen (Package calculator, Klasse Main)
- Einfach ausführen → alles läuft in der Konsole.

## So benutzt du ihn

1. Er fragt:  
   `Enter The Operator (+, -, *, /, %, ^)`

    - Gib + - * / % ^ ein
    - Oder "h" für History
    - Oder "q" zum Beenden

2. Dann:  
   `Enter the two numbers one by one (or bracket like (3 + 4))`

    - Gib normale Zahlen ein (z.B. 5 oder 3.14)
    - Oder Klammern: Immer mit Leerzeichen! z.B. (10 + 5) oder (8 / 2)
    - Er zeigt dann "Bracket result: 15.0" und nimmt das als Zahl.


3. Danach fragt er "Do you Want to make more Calculations?" – du gibst einfach den nächsten Operator ein.
