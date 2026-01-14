import 'package:flutter/material.dart';
import 'package:menu_dash/widgets/option_menu_item.dart';

class MenuItem extends StatelessWidget {
  const MenuItem({
    super.key,
    required OptionMenuItem opcion,
  }) : _opcion = opcion;

  final OptionMenuItem _opcion;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => Navigator.pushNamed(context, _opcion.screenName),
      child: Card(
        elevation: 5,
        color: _opcion.color,
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(_opcion.icono, color: Colors.white, size: 65,),
            SizedBox(height: 15,),
            Text(_opcion.texto,
                style: TextStyle(
                  color: Colors.white,
                  fontSize: 20,
                  fontWeight: FontWeight.w500
                ),
            )
          ],
        )
      ),
    );
  }
}