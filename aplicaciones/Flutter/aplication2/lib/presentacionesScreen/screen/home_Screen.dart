import 'package:aplication2/config/routes/menu_items.dart';
import 'package:aplication2/main.dart';
import 'package:flutter/material.dart';

void main() => runApp(const MyApp());
class Homescreen extends StatelessWidget {
  const Homescreen({super.key});

 @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: const Text('Home Screen')),
        body: Container(
          //color: Colors.green,
          child: ListView.builder(
            itemCount: MenuItems.length,
            itemBuilder: (context, index)
          { 
            final MenuItem = MenuItems[index];
            final colors = Theme.of(context).colorScheme;
            return ListTile(
              title: Text(MenuItem.titulo),
              subtitle: Text(MenuItem.subtitulo),
              leading: Icon(MenuItem.icon,color: colors.primary,),
              trailing: Icon(Icons.arrow_forward_ios),
              onTap: () {
                //Navigator.of(context).push(MaterialPageRoute(builder: (context) => BotonesScreen()),);
                Navigator.pushNamed(context, MenuItem.link);
              },
            );
             },

          ),
        )

      );
    
  }
}



