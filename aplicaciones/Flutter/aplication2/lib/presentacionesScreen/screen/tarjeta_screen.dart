import 'package:aplication2/presentacionesScreen/wigets/TarjetaPersonalisada1.dart';
import 'package:aplication2/presentacionesScreen/wigets/TarjetaPersonalizada2.dart';

import 'package:flutter/material.dart';

class TarjetasScreen extends StatelessWidget {
  
  const TarjetasScreen({super.key});


  @override
  Widget build(BuildContext context) {
    final colors = Theme.of(context).colorScheme;
    return Scaffold(
      appBar: AppBar(title: Text('Tarjetas'),),
      body: ListView(
        padding: EdgeInsets.all(5),
        children: [
          TarjetaPersonalisada1(),
          Tarjetapersonalizada2( urlimagen: 'https://wallpapers.com/images/featured/fondos-de-paisajes-naturales-k9tfch0hpfjbaxel.jpg', titulo: 'First Image', ),
          SizedBox(height: 30,),
          
          

        ],
      ),
    );
  }
}

