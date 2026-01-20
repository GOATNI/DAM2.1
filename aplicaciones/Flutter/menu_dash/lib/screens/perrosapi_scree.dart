// lib/views/perros_raza_view.dart
import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import 'dart:convert';

class PerrosRazaView extends StatefulWidget {
  const PerrosRazaView({super.key});

  @override
  State<PerrosRazaView> createState() => _PerrosRazaViewState();
}

class _PerrosRazaViewState extends State<PerrosRazaView> {
  final TextEditingController _searchController = TextEditingController();
  List<String> _dogImages = [];
  bool _isLoading = false;

  Future<void> _searchDog() async {
    final breed = _searchController.text.toLowerCase().trim();
    if (breed.isEmpty) return;

    setState(() => _isLoading = true);

    final response = await http.get(
      Uri.parse('https://dog.ceo/api/breed/$breed/images'),
    );

    final data = json.decode(response.body);
    setState(() {
      _dogImages = List<String>.from(data['message'] ?? []);
      _isLoading = false;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Dog Search')),
      body: Column(
        children: [
          Padding(
            padding: const EdgeInsets.all(8.0),
            child: Row(
              children: [
                Expanded(
                  child: TextField(
                    controller: _searchController,
                    decoration: const InputDecoration(
                      hintText: 'Raza del perro',
                      border: OutlineInputBorder(),
                    ),
                  ),
                ),
                const SizedBox(width: 8),
                FilledButton(
                  onPressed: _searchDog,
                  child: const Text('buscar'),
                ),
              ],
            ),
          ),
          if (_isLoading) const CircularProgressIndicator(),
          Expanded(
            child: ListView.builder(
              itemCount: _dogImages.length,
              itemBuilder: (context, index) {
                return Padding(
                  padding: const EdgeInsets.all(4.0),
                  child: Image.network(_dogImages[index]),
                );
              },
            ),
          ),
        ],
      ),
    );
  }
}