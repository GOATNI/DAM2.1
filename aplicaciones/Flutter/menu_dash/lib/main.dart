import 'package:flutter/material.dart';
import 'package:menu_dash/screens/apijson_screen.dart';
import 'package:menu_dash/screens/baloncesto_screen_1.dart';
import 'package:menu_dash/screens/menu_screen.dart';
<<<<<<< HEAD
import 'package:menu_dash/screens/simpsons_screen.dart';
=======
import 'package:menu_dash/screens/perrosapi_scree.dart';
>>>>>>> 9b3ff556f9d4d2b29f2de02cbdd65c15a9d1fb4c

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      routes: {
        '/': (context) => MenuScreen(),
        'api1': (context) => ApiJsonPlaceUsersScreen(),
<<<<<<< HEAD
        'simpsons': (context) => SimpsonsScreen(),
=======
        'baloncesto': (context) => BaloncestoScreen1(),
        'practica10' : (context) => PerrosRazaView(),
>>>>>>> 9b3ff556f9d4d2b29f2de02cbdd65c15a9d1fb4c
      },
    );
  }
}