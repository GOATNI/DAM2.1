import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:menu_dash/services/SimnpsonApiService.dart';

class CharacterDetailScreen extends StatelessWidget {
  final Map<String, dynamic> character;

  const CharacterDetailScreen({Key? key, required this.character})
    : super(key: key);

  String getImageUrl() {
    final id = character['id'];
    return 'https://cdn.thesimpsonsapi.com/500/character/$id.webp';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text(character['name'] ?? ''),
        backgroundColor: Colors.yellow[700],
      ),
      body: SingleChildScrollView(
        padding: const EdgeInsets.all(16),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: [
            // Imagen
            Center(
              child: ClipRRect(
                borderRadius: BorderRadius.circular(12),
                child: Image.network(
                  getImageUrl(),
                  height: 300,
                  fit: BoxFit.cover,
                  errorBuilder: (context, error, stackTrace) {
                    return const Icon(Icons.person, size: 100);
                  },
                ),
              ),
            ),
            const SizedBox(height: 20),

            // Nombre
            Text(
              character['name'] ?? '',
              style: const TextStyle(fontSize: 28, fontWeight: FontWeight.bold),
            ),
            const SizedBox(height: 10),

            // Edad
            if (character['age'] != null)
              Text(
                'Edad: ${character['age']} años',
                style: const TextStyle(fontSize: 16),
              ),
            const SizedBox(height: 5),

            // Ocupación
            if (character['occupation'] != null)
              Text(
                'Ocupación: ${character['occupation']}',
                style: const TextStyle(fontSize: 16),
              ),
            const SizedBox(height: 5),

            // Género
            if (character['gender'] != null)
              Text(
                'Género: ${character['gender']}',
                style: const TextStyle(fontSize: 16),
              ),
            const SizedBox(height: 5),

            // Estado
            if (character['status'] != null)
              Text(
                'Estado: ${character['status']}',
                style: const TextStyle(fontSize: 16),
              ),
            const SizedBox(height: 5),

            // Fecha de nacimiento
            if (character['birthdate'] != null)
              Text(
                'Nacimiento: ${character['birthdate']}',
                style: const TextStyle(fontSize: 16),
              ),

            const SizedBox(height: 20),

            // Frases
            if (character['phrases'] != null &&
                (character['phrases'] as List).isNotEmpty) ...[
              const Text(
                'Frases célebres:',
                style: TextStyle(fontSize: 20, fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 10),

              ...((character['phrases'] as List?) ?? []).map(
                (phrase) => Padding(
                  padding: const EdgeInsets.only(bottom: 8.0),
                  child: Row(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                      const Text(
                        '• ',
                        style: TextStyle(
                          fontSize: 16,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                      Expanded(
                        child: Text(
                          phrase,
                          style: const TextStyle(fontSize: 14),
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ],
        ),
      ),
    );
  }
}
