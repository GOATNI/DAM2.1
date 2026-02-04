import 'package:firebase_app/screens/HomeScreen.dart';
import 'package:firebase_app/screens/LoginScreen.dart';
import 'package:firebase_app/services/auth_service.dart';
import 'package:firebase_auth/firebase_auth.dart';
import 'package:flutter/material.dart';

class Seleccionarscreen extends StatelessWidget {
  const Seleccionarscreen({super.key});

  @override
  Widget build(BuildContext context) {
    final _authService = authService();



    return StreamBuilder<User?>
    //escucha los cambios de estados
    (stream:_authService.authStateChanges, builder: (context, snapshot) {
      //mientras se esta verificando estado 
      if (snapshot.connectionState == ConnectionState.waiting) {

          return Scaffold(
            body: Center(
              child: CircularProgressIndicator.adaptive(),
            ),
          );
      }
      if(snapshot.hasError){
          return Scaffold(
            body: Center(
              child: Text("ha occurido un error"),
            ),
          );
        }
        // si se ha lougeado
      if(snapshot.hasData){
          return HomeScreen();
      }
      //si no hay usuario lougeado
      return Loginscreen();
      
    },
    );
  }
}