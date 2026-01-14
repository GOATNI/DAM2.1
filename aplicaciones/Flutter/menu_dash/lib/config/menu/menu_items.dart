import 'package:flutter/material.dart';
import 'package:menu_dash/config/theme/app_theme.dart';
import 'package:menu_dash/widgets/option_menu_item.dart';

class MenuItems {

  List<OptionMenuItem> get listaOpcionesMenu => [
    OptionMenuItem(
      color: AppTheme.listaColores[0], 
      icono: Icons.sports_basketball, 
      texto: 'Marcador Basket', 
      screenName: 'baloncesto',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[1], 
      icono: Icons.pets, 
      texto: 'API Perretes', 
      screenName: 'practica10',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[3], 
      icono: Icons.supervised_user_circle, 
      texto: 'API JsonPlace', 
      screenName: 'api1',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[4], 
      icono: Icons.local_drink, 
      texto: 'API Simpsons', 
      screenName: 'simpsons',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[5], 
      icono: Icons.lan_sharp, 
      texto: 'Opcion 5', 
      screenName: 'opcion5',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[6], 
      icono: Icons.snooze_outlined, 
      texto: 'Opcion 6', 
      screenName: 'opcion6',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[7], 
      icono: Icons.snowmobile, 
      texto: 'Opcion 7', 
      screenName: 'opcion7',
      ),
    OptionMenuItem(
      color: AppTheme.listaColores[8], 
      icono: Icons.move_to_inbox_outlined, 
      texto: 'Opcion 8', 
      screenName: 'opcion8',
      ),
    

  ];
}