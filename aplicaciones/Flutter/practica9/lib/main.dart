// lib/main.dart
import 'package:flutter/material.dart';
import 'package:practica9/pages/MarcadorView.dart';
import 'package:practica9/pages/Resultadosview.dart';
import 'package:practica9/provider/scoreprovider.dart';
import 'package:provider/provider.dart';


void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    // 1. Inyección del Provider en el Widget Tree
    return ChangeNotifierProvider(
      create: (context) => ScoreProvider(), // Crea una instancia de tu estado
      child: MaterialApp(
        title: 'Basketball Score',
        theme: ThemeData(
          primarySwatch: Colors.deepPurple,
          useMaterial3: true,
        ),
        home: const HomeScreen(),
      ),
    );
  }
}

class HomeScreen extends StatefulWidget {
  const HomeScreen({super.key});

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {
  int _selectedIndex = 0; // Índice de la vista actual

  // Lista de las vistas (todos son StatelessWidgets)
  static const List<Widget> _widgetOptions = <Widget>[
    MarcadorView(),
    ResultadoView(),
  ];

  void _onItemTapped(int index) {
    setState(() {
      _selectedIndex = index;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Basketball Score',
            style: TextStyle(color: Colors.white)),
        backgroundColor: Colors.deepPurple,
      ),
      body: Center(
        child: _widgetOptions.elementAt(_selectedIndex), // Muestra la vista seleccionada
      ),
      bottomNavigationBar: BottomNavigationBar(
        items: const <BottomNavigationBarItem>[
          BottomNavigationBarItem(
            icon: Icon(Icons.sports_basketball),
            label: 'Marcador',
          ),
          BottomNavigationBarItem(
            icon: Icon(Icons.score),
            label: 'Resultado',
          ),
        ],
        currentIndex: _selectedIndex,
        selectedItemColor: Colors.deepPurple,
        onTap: _onItemTapped,
        // El botón central que mencionas se puede simular como una pestaña más,
        // o si prefieres un diseño con un botón flotante, necesitarías un FloatingActionButton.
        // Aquí lo implemento como una pestaña más en el BottomNavigationBar.
      ),
    );
  }
}