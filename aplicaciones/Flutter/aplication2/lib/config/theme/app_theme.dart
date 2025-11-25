import 'package:flutter/material.dart';

const listadeColores = <Color>[
  Colors.blue,
  Colors.indigo,
  Colors.deepPurple,
  Colors.pink,
  Colors.yellow,
  Colors.green
];

class AppTheme {
  final int colorseleccionado;

  AppTheme({this.colorseleccionado = 0});

  ThemeData obtenerTema() => ThemeData(



    useMaterial3: true,
    colorSchemeSeed: listadeColores[colorseleccionado],

    appBarTheme: AppBarTheme(
      backgroundColor: listadeColores[colorseleccionado],
      foregroundColor: Colors.red,
      centerTitle: true,
    ),

  );

  

}