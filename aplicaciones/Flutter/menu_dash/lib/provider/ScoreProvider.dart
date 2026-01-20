// lib/score_provider.dart
import 'package:flutter/material.dart';

class ScoreProvider with ChangeNotifier {
  int _localScore = 0; 
  int _visitanteScore = 0;

  
  int get localScore => _localScore;
  int get visitanteScore => _visitanteScore;

  
  void updateLocalScore(int points) {
    _localScore += points;
    
    if (_localScore < 0) {
      _localScore = 0;
    }
    
    notifyListeners();
  }

  
  void updateVisitanteScore(int points) {
    _visitanteScore += points;
    if (_visitanteScore < 0) {
      _visitanteScore = 0;
    }
    notifyListeners();
  }

  
  void resetScore() {
    _localScore = 0;
    _visitanteScore = 0;
    notifyListeners();
  }

  
  String getMatchResult() {
    if (_localScore > _visitanteScore) {
      return '¡El equipo Local ganó por ${_localScore - _visitanteScore} puntos!';
    } else if (_visitanteScore > _localScore) {
      return '¡El equipo Visitante ganó por ${_visitanteScore - _localScore} puntos!';
    } else {
      return 'Fue un empate';
    }
  }
}