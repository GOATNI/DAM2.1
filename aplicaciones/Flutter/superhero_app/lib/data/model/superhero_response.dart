import 'package:superhero_app/data/model/superhero_detail_response.dart';

class SuperHeroResponse {

  final String response;
  final List<SuperheroDetailResponse> listaSuperHeroes;

  SuperHeroResponse({
    required this.response, 
    required this.listaSuperHeroes,
    });

  // Necesito devolver un objeto SuperHerpResponse con los campos que me interesan
  factory SuperHeroResponse.fromJson( Map<String, dynamic> json ){
    var lista = json['results'] as List;

    // El.map escomo un for que recorre la lista y la aplica a la funcion que pasamos
    List<SuperheroDetailResponse> listaSuperHeroes = lista
        .map( (heroe)=> SuperheroDetailResponse.fromJson( heroe))
        .toList();
    final response = json['response'];
    return SuperHeroResponse(
      response: response, 
      listaSuperHeroes: listaSuperHeroes,
      );
  }

}