import 'package:flutter/material.dart';

class MenuItem {
  final String titulo;
  final String subtitulo;
  final String link;
  final IconData icon;

  MenuItem({required this.titulo, required this.subtitulo, required this.link, required this.icon});
  
}

final MenuItems = <MenuItem>[
  MenuItem(titulo: 'Botones', subtitulo: 'Un Simple ejemplo de botones', link: '/botones', icon: Icons.radio_button_checked),
  MenuItem(titulo: 'Listas', subtitulo: 'Un Simple ejemplo de listyas', link: '/listas', icon: Icons.list),
  MenuItem(titulo: 'Tarjetas', subtitulo: 'Un Simple ejemplo de tarjetas', link: '/tarjetas', icon: Icons.credit_card),
  MenuItem(titulo: 'Alertas', subtitulo: 'Un Simple ejemplo de alertas', link: '/alertas', icon: Icons.alarm_rounded),
];

