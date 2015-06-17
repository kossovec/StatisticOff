package ua.kossovec.dao;

import ua.kossovec.model.Ne;

import java.util.List;

public interface NesDao {
  Ne getNeByName(String neName);
  List<Ne> getAllNes();
  List<Ne> getAllAxeNes();
}
