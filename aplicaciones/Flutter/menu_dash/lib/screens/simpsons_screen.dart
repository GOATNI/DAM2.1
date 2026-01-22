import 'package:flutter/material.dart';

class SimpsonsScreen extends StatelessWidget {
   
  const SimpsonsScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Simpsons'),), 
      body: Center(
         child: Text('SimpsonsScreen'),
      ),
    );
  }
}