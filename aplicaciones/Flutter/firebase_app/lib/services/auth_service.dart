import 'package:firebase_auth/firebase_auth.dart';

class authService{
  final FirebaseAuth _auth = FirebaseAuth.instance;
  //me creo un stream para que emita cambios en el estado de authenticación;
  Stream<User?> get authStateChanges => _auth.authStateChanges();
  //para obtener el usuario actual 
  User? get currentUser => _auth.currentUser;
  
  //Registro con email y contraseña
  Future<UserCredential?> registerEmailyContrasena({required String email,required String password,required  String nombre}) async{

    try {
      UserCredential userCredential = await _auth.createUserWithEmailAndPassword(email: email, password: password);
      return userCredential;
    }on FirebaseAuthException catch(e){
      //manejo de errores especificos de firebase 
      if (e.code == "email-alrady-in-use") {
        throw Exception("Este email ya esta registrado");
      }else if(e.code == "invalid-email"){
        throw Exception("Email no es valido");
      }
      throw Exception("Error al registrar el usuario:${e.message}");
    }
    
     catch (e) {
      throw Exception("ERROR INESPERADO: $Error");
      
    }
  }

  //iniciar session con email y contrasña

  Future<UserCredential?> iniciarsession({required String email,required String password}) async{

    try {
      UserCredential userCredential = await _auth.signInWithEmailAndPassword(email: email, password: password);
      return userCredential;
    }on FirebaseAuthException catch(e){
      //manejo de errores especificos de firebase 
      if (e.code == "user-notfound") {
        throw Exception("Ususario no encontrado");
      }else if(e.code == "invalid-email"){
        throw Exception("Email no es valido");
      }
      throw Exception("Error al lougear el usuario:${e.message}");
    }
    
     catch (e) {
      throw Exception("ERROR INESPERADO: $e");
      
    }
  }

  // cerrar session 

  //Future que devuelve un  void que diga 
  Future<void> cerrarsecion()async{
    try {
      await _auth.signOut(); 
    } catch (e) {
      throw Exception("Error al cerrar la session:; $Error");
    }
  }
  
}
