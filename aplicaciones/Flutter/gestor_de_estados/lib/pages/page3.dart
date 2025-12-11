import 'package:flutter/material.dart';
import 'package:gestor_de_estados/provider/contador_provider.dart';
import 'package:provider/provider.dart';

class Pagina3 extends StatelessWidget {
  const Pagina3({super.key});

  @override
  Widget build(BuildContext context) {
   return Center(
        child: Column(
          children: [
            SizedBox(height: 20,),
            
            Text(
              context.watch<ContadorProvider>().contador.toString()
              , style: TextStyle(fontSize: 120,color: Colors.blueGrey),),
            ElevatedButton(onPressed: (){
                //vamos a aceder a provider y llamar a la funcion incrementar
                context.read<ContadorProvider>().decrementar();
        }, child: Text("Decrementar"),)]
          )
          );
  }
}