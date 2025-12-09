import 'package:flutter/material.dart';

class ProgresIndicatorScreen extends StatelessWidget {
   
  const ProgresIndicatorScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Snack bar"),),
      body: Center(
         child:Column(
          children: [
            SizedBox(height: 10,),
            CircularProgressIndicator(
              strokeWidth: 2,
              backgroundColor: Colors.black45,
              color: Colors.red,
            ),
            Text("Esto que elmento es "),
            SizedBox(height: 30,),
            LinearProgressIndicator(
              backgroundColor: Colors.blueAccent,
              

            ),
            Text("Progres indicator"),

            SizedBox(height: 30,),
            
                _CircularControlado(),
                

            SizedBox(height: 30,),
            
          ],
         )
      ),
    );
  }
}

class _LinearProgresControlado extends StatelessWidget {
  const _LinearProgresControlado({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return LinearProgressIndicator(
      backgroundColor: Colors.blueAccent,
      value: 0.6,
    
    );
  }
}

class _CircularControlado extends StatelessWidget {
  const _CircularControlado({
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        CircularProgressIndicator(
          value: 0.5,
          strokeWidth: 2,
          backgroundColor: Colors.black26,
        ),
        _LinearProgresControlado(),
      ],
    );
  }
}