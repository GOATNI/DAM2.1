import 'dart:convert';
import 'package:http/http.dart' as http;

class JsonPlaceHolderAPIService {

  // Método para obtener lalista de usuarios mediante http
  static Future<List<dynamic>> fetchUsersWithHttp() async {

    final response = await http.get(
      Uri.parse('https://jsonplaceholder.typicode.com/users')
    );

    //Comprobamos la respuesta
    if (response.statusCode == 200 ) {
      return json.decode( response.body );
    } else {
      // Si la respuesta no es correcta lanzamos un error
      throw Exception("Error al cargar las lista de usuarios");
    }
  }

}