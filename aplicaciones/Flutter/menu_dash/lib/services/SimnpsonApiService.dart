import 'package:dio/dio.dart';

class SimpsonsAPIService {
  static final Dio dio = Dio();
  static const String baseUrl = 'https://thesimpsonsapi.com/api/characters';

  // Obtener múltiples personajes (del 1 al 30)
  static Future<List<dynamic>> fetchCharacters() async {
    try {
      List<dynamic> allCharacters = [];

      // Obtener los primeros 30 personajes
      for (int i = 1; i <= 30; i++) {
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
