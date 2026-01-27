import 'package:dio/dio.dart';
import 'package:flutter/material.dart';

class SimpsonsAPIService {
  static final Dio dio = Dio();
  static const String baseUrl = 'https://thesimpsonsapi.com/api/characters';

  // Obtener múltiples personajes (del 1 al 30)
  static Future<List<dynamic>> fetchCharacters() async {
    try {
      List<dynamic> allCharacters = [];

      // Obtener los primeros 30 personajes
      for (int i = 1; i <= 60; i++) {
        try {
          final response = await dio.get('$baseUrl/$i');
          allCharacters.add(response.data);
        } catch (e) {
          print('Error obteniendo personaje $i');
        }
      }

      return allCharacters;
    } catch (e) {
      throw e;
    }
  }
}

class SimpsonsScreen extends StatefulWidget {
  const SimpsonsScreen({Key? key}) : super(key: key);

  @override
  State<SimpsonsScreen> createState() => _SimpsonsScreenState();
}

class _SimpsonsScreenState extends State<SimpsonsScreen> {
  List<dynamic> characters = [];
  List<dynamic> filteredCharacters = [];
  bool isLoading = true;

  @override
  void initState() {
    super.initState();
    loadCharacters();
  }

  Future<void> loadCharacters() async {
    try {
      final data = await SimpsonsAPIService.fetchCharacters();
      setState(() {
        characters = data;
        filteredCharacters = data;
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
      });
    }
  }

  void filterCharacters(String query) {
    setState(() {
      if (query.isEmpty) {
        filteredCharacters = characters;
      } else {
        filteredCharacters = characters.where((character) {
          final name = character['name']?.toString().toLowerCase() ?? '';
          return name.contains(query.toLowerCase());
        }).toList();
      }
    });
  }

  String getImageUrl(Map<String, dynamic> character) {
    final id = character['id'];
    return 'https://cdn.thesimpsonsapi.com/500/character/$id.webp';
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Simpsons'),
        backgroundColor: Colors.yellow[700],
      ),
      body: Column(
        children: [
          // Barra de búsqueda
          Container(
            color: Colors.yellow[700],
            padding: const EdgeInsets.all(16.0),
            child: TextField(
              onChanged: filterCharacters,
              decoration: InputDecoration(
                hintText: 'Personaje',
                filled: true,
                fillColor: Colors.white,
                border: OutlineInputBorder(
                  borderRadius: BorderRadius.circular(8),
                  borderSide: BorderSide.none,
                ),
                prefixIcon: const Icon(Icons.search),
              ),
            ),
          ),

          // Grid de personajes
          Expanded(
            child: isLoading
                ? const Center(
                    child: Column(
                      mainAxisAlignment: MainAxisAlignment.center,
                      children: [
                        CircularProgressIndicator(),
                        SizedBox(height: 16),
                      ],
                    ),
                  )
                : filteredCharacters.isEmpty
                ? const Center(child: Text('No hay personajes'))
                : GridView.builder(
                    padding: const EdgeInsets.all(16),
                    gridDelegate:
                        const SliverGridDelegateWithFixedCrossAxisCount(
                          crossAxisCount: 3,
                          childAspectRatio: 0.75,
                          crossAxisSpacing: 10,
                          mainAxisSpacing: 10,
                        ),
                    itemCount: filteredCharacters.length,
                    itemBuilder: (context, index) {
                      final character = filteredCharacters[index];

                      return InkWell(
                        onTap: () {
                          Navigator.push(
                            context,
                            MaterialPageRoute(
                              builder: (context) =>
                                  CharacterDetailScreen(character: character),
                            ),
                          );
                        },
                        child: Card(
                          color: Colors.yellow[600],
                          child: Column(
                            children: [
                              Expanded(
                                child: Padding(
                                  padding: const EdgeInsets.all(8.0),
                                  child: ClipRRect(
                                    borderRadius: BorderRadius.circular(8),
                                    child: Image.network(
                                      getImageUrl(character),
                                      fit: BoxFit.cover,
                                      loadingBuilder:
                                          (context, child, loadingProgress) {
                                            if (loadingProgress == null)
                                              return child;
                                            return const Center(
                                              child: CircularProgressIndicator(
                                                strokeWidth: 2,
                                              ),
                                            );
                                          },
                                      errorBuilder:
                                          (context, error, stackTrace) {
                                            return const Icon(
                                              Icons.person,
                                              size: 50,
                                            );
                                          },
                                    ),
                                  ),
                                ),
                              ),
                              Padding(
                                padding: const EdgeInsets.all(8.0),
                                child: Text(
                                  character['name'] ?? 'Sin nombre',
                                  textAlign: TextAlign.center,
                                  maxLines: 2,
                                  overflow: TextOverflow.ellipsis,
                                  style: const TextStyle(
                                    fontWeight: FontWeight.bold,
                                    fontSize: 11,
                                  ),
                                ),
                              ),
                            ],
                          ),
                        ),
                      );
                    },
                  ),
          ),
        ],
      ),
    );
  }
}

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
