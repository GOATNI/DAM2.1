import 'dart:math';

import 'package:flutter/material.dart';

import 'package:flutter/material.dart';

class AnimacionesScreen extends StatefulWidget {
   
  const AnimacionesScreen({Key? key}) : super(key: key);

  @override
  State<AnimacionesScreen> createState() => _AnimacionesScreenState();
}


class _AnimacionesScreenState extends State<AnimacionesScreen> {
double _width= 50;
double _height = 50;
Color _color = Colors.indigo;
BorderRadiusGeometry _borderradius= BorderRadiusGeometry.circular(8);

void changeShape(){
  setState(() {
    final random = Random();
    _width = random.nextInt(300).toDouble()+70;
    _height = random.nextInt(300).toDouble()+70;
    _color = Color.fromRGBO(random.nextInt(255), random.nextInt(255), random.nextInt(255), 1);
    _borderradius = BorderRadiusGeometry.circular(random.nextInt(100).toDouble());
  });
}
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Animaciones"),),
      body: Center(
         child: AnimatedContainer
         (height: _width,
         width: _height,
         duration: Duration(milliseconds: 2000),
         curve: Curves.elasticInOut,
         decoration: BoxDecoration(
         color:_color,
         
         borderRadius: _borderradius,
         
         

         ), 
         )
      ),
      floatingActionButton: FloatingActionButton(onPressed:changeShape,
      child: Icon(Icons.play_circle_outline,size: 20,),),
    );
  }
}