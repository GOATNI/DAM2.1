import 'package:flutter/material.dart';

class Practica8Screen extends StatelessWidget {
  const Practica8Screen({super.key});

  @override
  Widget build(BuildContext context) {
    // SliverAppBar SIEMPRE debe ir dentro de un CustomScrollView
    return Scaffold(
      body: CustomScrollView(
        slivers: [
          // 1. El Widget Estrella: SliverAppBar
          SliverAppBar(
            backgroundColor: Colors.indigo,
            expandedHeight: 200.0, // Altura cuando está expandido
            floating: false, // ¿Aparece apenas haces scroll hacia arriba?
            pinned: true, // ¿Se queda pegado arriba al bajar del todo?
            flexibleSpace: FlexibleSpaceBar(
              title: const Text('Práctica 8: Slivers'),
              centerTitle: true,
              background: Image.network(
                'https://picsum.photos/800/400', // Imagen de ejemplo
                fit: BoxFit.cover,
              ),
            ),
          ),

          // 2. Una lista de elementos para poder hacer scroll y ver el efecto
          SliverList(
            delegate: SliverChildBuilderDelegate(
              (BuildContext context, int index) {
                return Card(
                  margin: const EdgeInsets.all(10),
                  child: ListTile(
                    leading: CircleAvatar(child: Text('${index + 1}')),
                    title: Text('Elemento de la lista $index'),
                    subtitle: const Text('Desliza para ver cómo reacciona el AppBar'),
                  ),
                );
              },
              childCount: 20, // Número de elementos
            ),
          ),
        ],
      ),
    );
  }
}