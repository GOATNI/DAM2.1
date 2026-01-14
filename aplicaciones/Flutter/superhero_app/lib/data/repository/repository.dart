import 'dart:convert';

import 'package:superhero_app/data/model/superhero_response.dart';
import 'package:http/http.dart' as http;


class Repository {

  Future<SuperHeroResponse?> getSuperHeroInfo(String superHeroName ) async {

    final response = await http.get(
      Uri.parse(
        'https://www.superheroapi.com/api.php/e5d378b243d82062549ed7a5194dc1bf/search/$superHeroName'
      ),
    );

    if( response.statusCode == 200 ){

      var decodedJson = jsonDecode( response.body );
      // Aqui ya tenemos el formato perfecto para poder usar nuestro constructor
      SuperHeroResponse superHeroResponse = SuperHeroResponse.fromJson(decodedJson);
      return superHeroResponse;

    } else {
      // Podemos hacer un par de cosas:
      // throw Exception('Failed to load superhero info');
      return null;
    }
  }
}