import 'package:flutter/material.dart';

Widget textContainer(String text, double width, BuildContext context, {Color? color}) {
  color ??= Theme.of(context).primaryColor;
  return Container(
    width: width,
    padding: const EdgeInsets.all(10),
    decoration: BoxDecoration(
      borderRadius: BorderRadius.circular(8),
      border: Border.all(),
      color: color,
    ),
    child: Center(
      child: Text(
        text,
        maxLines: 2,
        overflow: TextOverflow.ellipsis,
        textAlign: TextAlign.center,
        style: const TextStyle(
          fontSize: 20,
          // Other text styles
        ),
      ),
    ),
  );
}