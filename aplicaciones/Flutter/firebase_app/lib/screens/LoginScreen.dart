import 'package:flutter/material.dart';

class Loginscreen extends StatefulWidget {
  const Loginscreen({super.key});

  @override
  State<Loginscreen> createState() => _LoginscreenState();
}

class _LoginscreenState extends State<Loginscreen> {
  String email = '';
  String passwd = '';
  
  TextEditingController _emailController = TextEditingController();
   TextEditingController _passwdController = TextEditingController();
   final _formkey = GlobalKey<FormState>();
  @override
  Widget build(BuildContext context) {
   return Scaffold(
    backgroundColor: Colors.white,
    body: SingleChildScrollView(
      child: Column(
        children: [
          SizedBox(
            width: MediaQuery.of(context).size.width,
            child:  Image.asset(
              '/assets/car.PNG',fit: BoxFit.cover
              ,
              ),
          ),
          SizedBox(height: 30,),
          Padding(
      
            padding: const EdgeInsets.all(20.0),
            child: Form(
              key: _formkey,
              child: Column(
                children: [
                  Container(
                    padding: EdgeInsets.symmetric(vertical: 2.0,horizontal: 30.0),
                    decoration: BoxDecoration(
                      color: Colors.grey[200],
                      borderRadius: BorderRadius.circular(30.0)
                    ),
                    child: TextFormField(
                      controller:_emailController,
                      decoration: InputDecoration(
                        border: InputBorder.none,
                        hintText: 'Email',
                        hintStyle: TextStyle(
                          color: Colors.lightGreenAccent,
                          fontSize: 18,
                        )
                      ),
                    ),
                  ),
                  SizedBox(height: 30.0,),
                  Container(
                    padding: EdgeInsets.symmetric(vertical: 2.0,horizontal: 30.0),
                    decoration: BoxDecoration(
                      color: Colors.grey[200],
                      borderRadius: BorderRadius.circular(30.0)
                    ),
                    child: TextFormField(
                      obscureText: true,
                      controller:_passwdController,
                      decoration: InputDecoration(
                      
                        border: InputBorder.none,
                        hintText: 'Password',
                        hintStyle: TextStyle(
                          color: Colors.lightGreenAccent,
                          fontSize: 18,
                        )
                      ),
                    ),
                  ),
                  SizedBox(height: 30.0,),
                  GestureDetector(
                    child: Container(
                      width:MediaQuery.of(context).size.width,
                      padding: EdgeInsets.symmetric(vertical: 13.0,horizontal: 30.0),
                      decoration: BoxDecoration(
                        color: Colors.blue
                      ),
                      child: Center(
                        child: Text(
                          'Iniciar Secion'
                          ,style: TextStyle(
                            color: Colors.white,
                            fontSize: 22.0,
                            fontWeight: .w500,
                          ),),
                      ),
                    ),
                    onTap: () {
                      
                    },
                  ),
                  SizedBox(height: 20,),
                  GestureDetector(
                    child: Text('Contraseña olvidada'),
                    onTap: () {
                      
                    },
                    ),
                  SizedBox(height: 40,),
                  GestureDetector(
                    child: Text('o lougeate con'),
                    onTap: () {
                      
                    },
                    ),
                  SizedBox(height: 30,),
      
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      GestureDetector(
                        child: Image.asset('assets/google.png',
                        width: 45,
                        height: 45,
                        fit: BoxFit.cover,),
                        onTap: () {
                          
                        },
                      ),
                      SizedBox(width: 30,),
                      GestureDetector(
                        child: Image.asset('assets/apple1.png',
                        width: 50,
                        height: 50,
                        fit: BoxFit.cover,),
                        onTap: () {
                          
                        },
                      ),
                      SizedBox(width: 30,)
                    ],
                  ),
                  Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    children: [
                      Text("Aún no tienes cuenta"),
                      SizedBox(width: 5,),
                      GestureDetector(child: Text("Registrar Usuario"),
                      onTap: () {
                        
                      },
                      )
                    ],
                  )
                ],
              ),
            ),
          )
        ],
      ),
    ),
   );
  }
}

