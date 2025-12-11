import 'package:flutter/material.dart';
import 'package:gestor_de_estados/pages/page1.dart';
import 'package:gestor_de_estados/pages/page2.dart';
import 'package:gestor_de_estados/pages/page3.dart';

class HomeScreen extends StatefulWidget {
   
  const HomeScreen({Key? key}) : super(key: key);

  @override
  State<HomeScreen> createState() => _HomeScreenState();
}

class _HomeScreenState extends State<HomeScreen> {

  List<Widget> _paginas = [ Pagina1(), Pagina2(), Pagina3()];
  int _currentIndex = 0;
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Provider App'),), 
      body: _paginas[_currentIndex],
      bottomNavigationBar: BottomNavigationBar(
        currentIndex: _currentIndex,
        items: [
          BottomNavigationBarItem(
            icon: Icon(Icons.home),
            label: 'Página 1'),
          BottomNavigationBarItem(
            icon: Icon(Icons.search),
            label: 'Página 2'),
          BottomNavigationBarItem(
            icon: Icon(Icons.person),
            label: 'Página 3'),
        ],
        onTap: ( index ) {
          setState(() {
            _currentIndex = index;
          });
        },
        ),
    );
  }
}