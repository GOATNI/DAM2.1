import 'package:firebase_app/services/auth_service.dart';
import 'package:flutter/material.dart';

class Homescreen extends StatelessWidget {
  const Homescreen({super.key});

  @override
  Widget build(BuildContext context) {
    final _authService = authService();
    final user = _authService.currentUser;


    return Scaffold(
      appBar: AppBar(
        title: Text('Inicio'),
        actions: [
          IconButton(onPressed:() async{
            //mostrar dialogo de confirmacion
            final soutlogout = await showDialog<bool>(context: context,
             builder: (context) => AlertDialog.adaptive(title: Text('cerrar sesón'), 
             content: Text('Estas seguro que quieres cerrar la sesón'),
             actions: [
              TextButton(onPressed: () => Navigator.pop(context,false) 
              , 
              child: Text('Cancelar')),
              TextButton(onPressed: () => Navigator.pop(context,true) 
              , 
              child: Text('Aceptar')),
             ],),);

             if(soutlogout == true){
              await _authService.cerrarsecion();
             }
          }, 
          icon: Icon(Icons.logout))
        ],
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.check),
            SizedBox(height: 24,),
            Text("sesión iniciada correctamente",
            style: TextStyle(
              fontSize: 24,
            ),),
            SizedBox(height: 24,),
            Text("Email : ${user?.email}",style: TextStyle(
              fontSize: 16,
            ),),
            SizedBox(height: 24,),
            Text("id_user: ${user?.uid}",style: TextStyle(
              fontSize: 16,
              color: Colors.greenAccent
            ),)
          ],
        ),
      ),
    );
  }
}