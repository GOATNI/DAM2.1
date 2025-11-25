import 'package:flutter/material.dart';

class CounterFunctionsScreen extends StatefulWidget {
  const CounterFunctionsScreen({super.key});

  @override
  State<CounterFunctionsScreen> createState() => _CounterFunctionsScreenState();
}

class _CounterFunctionsScreenState extends State<CounterFunctionsScreen> {

  int contador = 0;

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        //leading: IconButton(onPressed: (){}, icon: Icon(Icons.ac_unit)),
        title: Text('Counter Functions Screen'),
        centerTitle: true,
        actions: [
          IconButton(onPressed: (){}, icon: Icon(Icons.settings)),
          IconButton(onPressed: (){}, icon: Icon(Icons.ac_unit)),
        ],
      ),
        body: Center(
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Text(' $contador ', style: TextStyle( fontSize: 160, fontWeight:FontWeight.w100 ),),
              Text('Click${ contador == 1 ? '' : 's'}', style: TextStyle(fontSize: 25),),
            ],
          ),
        ),
        floatingActionButton: Column(
          mainAxisAlignment: MainAxisAlignment.end,
          children: [
            BotonPersonalizado( 
              icono: Icons.refresh_rounded ,
              onPressed: () {
                setState(() {
                  contador = 0;
                });
              },
              ),
            SizedBox( height: 10,),
            BotonPersonalizado( 
              icono: Icons.plus_one,
              onPressed: () {
                setState(() {
                  contador++;
                });
              },),
            SizedBox( height: 10,),
            BotonPersonalizado( 
              icono: Icons.exposure_minus_1,
              onPressed: () {
                setState(() {
                  if ( contador == 0 ) return; 
                  contador --;
                });
              },),
            
          ],
        ),
      );
  }
}

class BotonPersonalizado extends StatelessWidget {

  final IconData icono;
  final VoidCallback? onPressed;

  const BotonPersonalizado({super.key, required this.icono, this.onPressed});
  
  @override
  Widget build(BuildContext context) {
    return FloatingActionButton(
      //backgroundColor: Colors.yellow,
      child: Icon( icono ),
        onPressed: onPressed
      );
  }
}