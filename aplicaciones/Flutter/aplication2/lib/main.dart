import 'package:aplication2/config/theme/app_theme.dart';

import 'package:flutter/material.dart';
import 'presentacionesScreen/screens.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: AppTheme(colorseleccionado: 1).obtenerTema(),
      title: 'Flutter Demo',
      // home: Homescreen(),
      initialRoute: '/',
      routes: {
        '/':(context) => Homescreen(),
        '/botones': (context) => BotonesScreen(),
        '/listas': (context) => ListasScreen(),
        '/tarjetas' : (context) => TarjetasScreen(),
        '/alertas' : (context) => AlertaScreen(),
      },
    );
  }
}