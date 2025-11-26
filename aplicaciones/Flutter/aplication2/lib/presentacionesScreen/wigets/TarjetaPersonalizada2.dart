import 'package:flutter/material.dart';
class Tarjetapersonalizada2 extends StatelessWidget {
  final String urlimagen;
  final String titulo;
  const Tarjetapersonalizada2({super.key, required this.urlimagen, required this.titulo});

  @override
  Widget build(BuildContext context) {
    return Card(
      clipBehavior: Clip.antiAlias,
      elevation: 10,
      child: Column(
        children: [
          FadeInImage(
          image: 
          NetworkImage(
            urlimagen
            ),
          fit: BoxFit.cover, placeholder: AssetImage('Assets/jar-loading.gif'),
         
          fadeInDuration: Duration(milliseconds: 400),
          ),
          Container(
          alignment: AlignmentDirectional.centerEnd,
          padding: EdgeInsets.only(right: 20,top: 10,bottom: 10),
            child: Text(titulo))
        ],
      ),
    );
  }
}