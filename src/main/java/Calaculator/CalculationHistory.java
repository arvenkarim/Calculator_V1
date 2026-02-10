package Calaculator;

import java.util.ArrayList;
import java.util.List;

/**
 * Speichert die History der Berechnungen.
 */
public class CalculationHistory {

  private static final int MAX_SIZE = 10;
  private final List<String> entries = new ArrayList<>();

  /**
   * Fügt eine Berechnung zur History hinzu.
   * Es werden maximal die letzten 10 Einträge gespeichert.
   *
   * @param calculation Berechnung als String
   */
  public void add(String calculation) {
    entries.add(calculation);
    if (entries.size() > MAX_SIZE) {
      entries.removeFirst();
    }
  }

  /**
   * Gibt die History-Einträge zurück.
   *
   * @return unveränderliche Kopie der History
   */
  public List<String> getEntries() {
    return List.copyOf(entries);
  }

  /**
   * Prüft ob die History leer ist.
   *
   * @return true wenn keine Einträge vorhanden
   */
  public boolean isEmpty() {
    return entries.isEmpty();
  }
}
