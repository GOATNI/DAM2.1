import 'package:cached_network_image/cached_network_image.dart';
import 'package:flutter/material.dart';
import 'package:superhero_app/data/model/superhero_response.dart';
import 'package:superhero_app/data/repository/repository.dart';

class SuperheroSearchScreen extends StatefulWidget {
  const SuperheroSearchScreen({super.key});

  @override
  State<SuperheroSearchScreen> createState() => _SuperheroSearchScreenState();
}

class _SuperheroSearchScreenState extends State<SuperheroSearchScreen> {

  Future<SuperHeroResponse?>? _superHeroInfo;
  Repository repository = Repository();


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('SuperHero Search'),),
      body: Padding(
        padding: const EdgeInsets.all(8.0),
        child: Column(
          children: [
            TextField(
              decoration: InputDecoration(
                hintText: 'Busca un SuperHéroe',
                prefixIcon: Icon(Icons.search),
                border: OutlineInputBorder()
              ),
              onChanged: ( nombreHeroe ) {
                setState(() {
                  _superHeroInfo = repository.getSuperHeroInfo( nombreHeroe );
                });
              },
            ),
            FutureBuilder(
              future: _superHeroInfo, 
              builder: (context, snapshot) {
                if (snapshot.connectionState == ConnectionState.waiting ){
                  return CircularProgressIndicator();
                } else if ( snapshot.hasError ){
                  return Text('Error al realizar la búsqueda');
                } else if ( !snapshot.hasData ){
                  return Text('No existen resultados');
                } else {
                  // Ahora aqui tenemos un listado de Superheroes
                  var listaSuperHeroes = snapshot.data?.listaSuperHeroes;
                  return Expanded(
                    child: ListView.builder(
                      itemCount: listaSuperHeroes?.length ?? 0,
                      itemBuilder: (context, index) {
                        //return Text(listaSuperHeroes![index].url);
                        /*
                        return ClipRRect(
                          borderRadius: BorderRadiusGeometry.circular(16),
                          child: Image.network(
                            'listaSuperHeroes![index].url',
                            
                          ),
                        );
                        */
                        
                        return Column(
                          children: [
                            ListTile(
                              title: Text(listaSuperHeroes![index].name),
                              subtitle: Text(listaSuperHeroes![index].id),
                              trailing: Icon(Icons.arrow_forward_ios),
                              onTap: () {
                                
                              },
                            ),
                            Divider(),
                          ],
                        );
                      },
                      ),
                  );
                }
              },)
          ],
        ),
      ),
    );
  }
}

