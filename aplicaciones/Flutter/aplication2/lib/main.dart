import 'package:aplication2/config/theme/app_theme.dart';
import 'package:aplication2/home_Screen.dart';
import 'package:flutter/material.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme(colorseleccionado: 3).obtenerTema(),
      title: 'Flutter Demo',
      home: Homescreen(),
    );
  }
}