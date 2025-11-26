import 'package:flutter/material.dart';


class TarjetaPersonalisada1 extends StatelessWidget {
  const TarjetaPersonalisada1({
    super.key,
  });



  @override
  Widget build(BuildContext context) {
    final colors = Theme.of(context).colorScheme;
    return Card(
      child: Column(
        children: [
          ListTile(
            title: Text('Soy in titulo'),
            subtitle: Text('Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.'),
            leading: Icon(Icons.photo_album_outlined, color: colors.primary, ),
          ),
          Padding(
            padding: EdgeInsetsGeometry.only(right: 10),
            child: Row(
              mainAxisAlignment: MainAxisAlignment.end,
              spacing: 20,
              children: [
                TextButton(onPressed: () { }, child: Text('Cancelar'),style: TextButton.styleFrom(backgroundColor: colors.error),),
                TextButton(onPressed: () { }, child: Text('OK'),style: TextButton.styleFrom(backgroundColor: Colors.green),),
            
              ],
            ),
          )
        ],
      ),
    );
  }
}