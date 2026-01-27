import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:menu_dash/services/SimnpsonApiService.dart';
import 'package:menu_dash/screens/CharacterDetailScreen.dart';

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
        backgroundColor: const Color.fromARGB(255, 255, 255, 255),
      ),
      body: Column(
        children: [
          // Barra de búsqueda
          Container(
            color: const Color.fromARGB(255, 235, 234, 231),
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
                          color: const Color.fromARGB(255, 255, 248, 216),
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
