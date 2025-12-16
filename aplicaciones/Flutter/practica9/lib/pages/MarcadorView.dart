// lib/views/marcador_view.dart
import 'package:flutter/material.dart';
import 'package:practica9/provider/scoreprovider.dart';
import 'package:provider/provider.dart';


class MarcadorView extends StatelessWidget {
  const MarcadorView({super.key});

  
  Widget _scoreButton(
      {required String text,
      required VoidCallback onPressed,
      required Color color}) {
    return SizedBox(
      width: 50,
      height: 50,
      child: OutlinedButton(
        style: OutlinedButton.styleFrom(
          backgroundColor: color,
          foregroundColor: Colors.white,
          shape: const CircleBorder(),
          padding: EdgeInsets.zero,
        ),
        onPressed: onPressed,
        child: Text(text, style: const TextStyle(fontSize: 16)),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    // Escucha los cambios en el provider para reconstruir los Text widgets
    final scoreProvider = context.watch<ScoreProvider>();
    // Obtenemos el provider para las acciones (no reconstruye)
    final actions = context.read<ScoreProvider>();

    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        children: <Widget>[
          // Puntuación Local
          const Text('Local', style: TextStyle(fontSize: 18)),
          const SizedBox(height: 8),
          Text(
            '${scoreProvider.localScore}',
            style: const TextStyle(fontSize: 80, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 16),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              _scoreButton(
                text: '-1',
                onPressed: () => actions.updateLocalScore(-1),
                color: Colors.red,
              ),
              _scoreButton(
                text: '+1',
                onPressed: () => actions.updateLocalScore(1),
                color: Colors.green,
              ),
              _scoreButton(
                text: '+2',
                onPressed: () => actions.updateLocalScore(2),
                color: Colors.blue,
              ),
            ],
          ),
          const Divider(height: 32),

          //restart
          Row(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              IconButton(
                icon: const Icon(Icons.restore, size: 30),
                onPressed: actions.resetScore,
              ),
              const SizedBox(width: 20),
              ],
          ),

          const Divider(height: 32),

          // Puntuación Visitante
          const Text('Visitante', style: TextStyle(fontSize: 18)),
          const SizedBox(height: 8),
          Text(
            '${scoreProvider.visitanteScore}',
            style: const TextStyle(fontSize: 80, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 16),
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceEvenly,
            children: <Widget>[
              _scoreButton(
                text: '-1',
                onPressed: () => actions.updateVisitanteScore(-1),
                color: Colors.red,
              ),
              _scoreButton(
                text: '+1',
                onPressed: () => actions.updateVisitanteScore(1),
                color: Colors.green,
              ),
              _scoreButton(
                text: '+2',
                onPressed: () => actions.updateVisitanteScore(2),
                color: Colors.blue,
              ),
            ],
          ),
        ],
      ),
    );
  }
}