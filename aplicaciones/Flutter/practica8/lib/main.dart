import 'package:flutter/material.dart';

import 'package:practica/screens/practica_8.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Widgets App',
      theme: ThemeData(
        colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepPurple),
        useMaterial3: true,
      ),
      // La página de inicio será el índice de prácticas
      home: const ComponentesScreen(title: 'Práctica Flutter'),
    );
  }
}

// Este widget maneja la lista de opciones (el menú)
class ComponentesScreen extends StatefulWidget {
  const ComponentesScreen({super.key, required this.title});
  final String title;

  @override
  State<ComponentesScreen> createState() => _ComponentesScreenState();
}

class _ComponentesScreenState extends State<ComponentesScreen> {

   //las prácticas 
  final List<Map<String, dynamic>> _practicas = [
    // La Práctica 8 que contiene el SliverAppBar
    {'title': 'Práctica 8: SliverAppBar', 'screen': const Practica8Screen()},
    
  ];

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        backgroundColor: Theme.of(context).colorScheme.inversePrimary,
        title: Text(widget.title),
      ),
      body: ListView.builder(
        itemCount: _practicas.length,
        itemBuilder: (context, index) {
          final practica = _practicas[index];
          return ListTile(
            leading: CircleAvatar(child: Text('${_practicas.length - index}')), // Numera en orden descendente
            title: Text(practica['title']),
            trailing: const Icon(Icons.arrow_forward_ios),
            onTap: () {
              if (practica['screen'] != null) {
                // Navega a la pantalla de la práctica
                Navigator.push(
                  context,
                  MaterialPageRoute(builder: (context) => practica['screen']),
                );
              } else {
                // Mensaje para prácticas pendientes
                ScaffoldMessenger.of(context).showSnackBar(
                  SnackBar(content: Text('${practica['title']} aún no está implementada.')),
                );
              }
            },
          );
        },
      ),
    );
  }
}