import 'package:contador/screens/counter_functions_screen.dart';
//import 'package:contador/screens/counter_screen.dart';
import 'package:flutter/material.dart';

void main() {
  runApp( MyApp() );
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      theme: ThemeData(
        useMaterial3: true,
        colorSchemeSeed: const Color.fromARGB(255, 25, 158, 129)
      ),
      home: CounterFunctionsScreen()
    );
  }
}