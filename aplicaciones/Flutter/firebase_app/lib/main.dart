import 'package:firebase_app/screens/HomeScreen.dart';
import 'package:firebase_app/screens/LoginScreen.dart';
import 'package:firebase_app/screens/RegisterScreen.dart';
import 'package:firebase_app/screens/SeleccionarScreen.dart';
import 'package:firebase_core/firebase_core.dart';
import 'package:firebase_crashlytics/firebase_crashlytics.dart';
import 'package:flutter/material.dart';
import 'package:firebase_analytics/firebase_analytics.dart';
import 'package:provider/provider.dart';
import 'firebase_options.dart';
void main() async { 

    WidgetsFlutterBinding.ensureInitialized();
    await Firebase.initializeApp(
      options: DefaultFirebaseOptions.currentPlatform,
    );

    //FirebaseCrashlytics.instance.crash();
    //FlutterError.onError = FirebaseCrashlytics.instance.recordFlutterFatalError;

    //FirebaseAnalytics analytics = FirebaseAnalytics.instance;

    runApp(const MyApp()); 
  }

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      initialRoute: '/',
      routes: {
        '/' : (context) => Loginscreen(),
        '/login' : (context) => Loginscreen(),
        '/register' : (context) => RegisterScreen(),
        '/home' : (context) => Homescreen(),
      },
    );
  }
}
