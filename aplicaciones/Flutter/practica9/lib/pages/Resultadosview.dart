// lib/views/resultado_view.dart
import 'package:flutter/material.dart';
import 'package:practica9/provider/scoreprovider.dart';
import 'package:provider/provider.dart';


class ResultadoView extends StatelessWidget {
  const ResultadoView({super.key});

  @override
  Widget build(BuildContext context) {
    // Escucha los cambios en el provider para actualizar el marcador y el resultado
    final scoreProvider = context.watch<ScoreProvider>();

    return Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: <Widget>[
          // Marcador
          Text(
            '${scoreProvider.localScore} - ${scoreProvider.visitanteScore}',
            style: const TextStyle(fontSize: 80, fontWeight: FontWeight.w100),
          ),
          const SizedBox(height: 30),
          // Resultado
          Padding(
            padding: const EdgeInsets.symmetric(horizontal: 40.0),
            child: Text(
              scoreProvider.getMatchResult(),
              textAlign: TextAlign.center,
              style: const TextStyle(
                fontSize: 24,
                fontWeight: FontWeight.bold,
                color: Colors.purple,
              ),
            ),
          ),
          const SizedBox(height: 30),
          ],
      ),
    );
  }
}