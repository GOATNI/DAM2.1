import 'package:firebase_auth/firebase_auth.dart';
import 'package:google_sign_in/google_sign_in.dart';

class authService{
  final FirebaseAuth _auth = FirebaseAuth.instance;
  //me creo un stream para que emita cambios en el estado de authenticación;
  Stream<User?> get authStateChanges => _auth.authStateChanges();
  //para obtener el usuario actual 
  User? get currentUser => _auth.currentUser;
   // Instancia de GoogleSignIn
  final GoogleSignIn _googleSignIn = GoogleSignIn.instance;
  static bool isInitialize = false;  
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
  
  /////////////////////////////////////////////////
  ///  Iniciar Sesion con Google             //////
  /////////////////////////////////////////////////
  Future<void> initSignIn() async {
    if (!isInitialize) {
      await _googleSignIn.initialize(
        serverClientId:
            '583125018644-f785ej86us31aia05c1ffufj4kkag898.apps.googleusercontent.com',
      );
      isInitialize = true;
    }
  }

  // Iniciar sesion con Google 7.2.0
  Future<UserCredential?> loginConGoogle() async {
    try {
      // 1 - Iniciamos el servicio de Google Sign In
      // Esto configiura el client ID del servidor necesario para autenticarnos
      initSignIn();
      // 2 - Auntenticar el usuario con Google: Abre la ventanita para seleccionar la cuenta.
      final GoogleSignInAccount googleUser = await _googleSignIn.authenticate();

      // Si el usuario cancela esa ventana, se podria retornar nulo.
      if (googleUser == null) return null;

      // 3 - Obtener el idToken: es un token JWT que contiene la informacion del usuario
      final idToken = googleUser.authentication.idToken;
      // 4 - Obtenemos el cliente de autorizacion: Este cliente nos permite solicitar los permisos específicos
      final authorizationClient = googleUser.authorizationClient;

      // 5 -  Solicitamos autorizacion para los scopes email y profile
      GoogleSignInClientAuthorization? authorization = await authorizationClient
          .authorizationForScopes(['email', 'profile']);

      // 6 - Obtenemos el accessToken
      final accesToken = authorization?.accessToken;
      // 7 - Validamos el token
      if (accesToken == null) {
        final authorization2 = await authorizationClient.authorizationForScopes(
          ['email', 'profile'],
        );
        // Si tampoco funciona lanzamos un error
        if (authorization2?.accessToken != null) {
          throw FirebaseAuthException(code: 'ERROR CODIGO');
        }
        authorization = authorization2;
      }

      // 8 - Creamos las credenciales para Firebase
      final credential = GoogleAuthProvider.credential(
        idToken: idToken,
        accessToken: accesToken,
      );
      // 9 - Nos logueamos con Firebase
      final UserCredential userCredential = await _auth.signInWithCredential(
        credential,
      );
      // 10 - Obtenemos el objeto User de Firebase
      final User? user = userCredential.user;
      // 11 - Procesamos la informacion adicional del usuario
      if (user != null) {
        // Aqui podemosmeter informacion en una base de datos de Firebase
      }
      // 12 - Devolvemos las credenciales del usuario identificado
      return userCredential;

    } catch (e) {
      print('Error en Login con Google: $e');
    }

    // Si hubo algun error
    return null;
  }
}
  

