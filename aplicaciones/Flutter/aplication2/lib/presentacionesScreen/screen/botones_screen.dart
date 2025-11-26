import 'package:flutter/material.dart';

class BotonesScreen extends StatelessWidget {
  const BotonesScreen({super.key});
  


  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar( title: Text('Botones'),),
      body: _BotonesScreenView(),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          Navigator.pop(context);
      },
      child: Icon(Icons.arrow_back_ios),),
    );
  }
}

class _BotonesScreenView extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Container(
      width: double.infinity,
      child: Padding(
        padding: EdgeInsetsGeometry.symmetric(horizontal: 5,vertical: 10),
        child: Wrap(
          spacing: 20,
          alignment: WrapAlignment.center,
          children: [
            ElevatedButton(
              onPressed: () { }, 
              child: Text('Elevated Buton')),
            ElevatedButton.icon(
              iconAlignment: IconAlignment.end,
              onPressed: ( ) { }, 
              label: Text('ElevatedButton.icon'),
              icon:Icon(Icons.access_alarm_outlined)),
            FilledButton(onPressed: () {}, child: Text('Filled button')),
            FilledButton.icon(onPressed: () { }, label:Text('Filled button with icon'),icon: Icon(Icons.access_alarm)),
            OutlinedButton(onPressed: () {}, child: Text('Outliner button')),
            TextButton(
              onPressed: () { }, child: Text('Text Button')),
              IconButton(onPressed: (){}, icon: Icon(Icons.wallet),
              color: Colors.cyanAccent,)

        
          ],
        ),
      ),
    );
  }

}