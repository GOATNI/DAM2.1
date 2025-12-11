import 'package:flutter/material.dart';
//este manejara el contador de la pagina 1 
//Chnge notificaion bnotifica a los listners cundo hay cambios en el  estado
class ContadorProvider extends ChangeNotifier{
  int _contador = 0;
  //cuando tenemos un estado o un provider hemos de poder hacer 2 cosas 
  //una poder leer el valor de ese estado y 
  //2 poder modificar el valor de ese estado 
  int get contador{
    return _contador;
  }
  void incrementar(){
    _contador++;
    notifyListeners();
  }
   void reset(){
    _contador=0;
    notifyListeners();
  }
  void decrementar(){
    _contador--;
    notifyListeners();
  }


}