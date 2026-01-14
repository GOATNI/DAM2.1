import 'package:flutter/material.dart';
import 'package:menu_dash/config/menu/menu_items.dart';
import 'package:menu_dash/widgets/menu_item.dart';
import 'package:menu_dash/widgets/option_menu_item.dart';

class MenuScreen extends StatelessWidget {
   
  const MenuScreen({Key? key}) : super(key: key);
  
  @override
  Widget build(BuildContext context) {

    final List<OptionMenuItem> _listaOpcionesMenu = MenuItems().listaOpcionesMenu;

    return Scaffold(
      //appBar: AppBar(title: Text('Menu'),), 
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: GridView.builder(
          itemCount: _listaOpcionesMenu.length,
          // Griddelegate sirve para personalizar el comportamiento del Grid
          gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
            crossAxisCount: 2,
            crossAxisSpacing: 8.0,
            mainAxisSpacing: 8.0
            ), 
          itemBuilder: ( context, index ){

            final OptionMenuItem _opcion = _listaOpcionesMenu[index];
            return MenuItem(opcion: _opcion);
          }
          ),
      )
    );
  }
}

